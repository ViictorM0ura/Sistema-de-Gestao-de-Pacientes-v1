package views;

import models.Paciente;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ChamarPacienteView {

    public void exibirTelaChamada() {
        JFrame frame = new JFrame("Chamar Paciente");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 400);
        frame.setLocationRelativeTo(null); // Centraliza a janela

        JPanel panel = new JPanel();
        frame.add(panel);
        placeComponents(panel, frame);

        frame.setVisible(true);
    }

    private void placeComponents(JPanel panel, JFrame frame) {
        panel.setLayout(null);

        JLabel patientLabel = new JLabel("Escolha o paciente:");
        patientLabel.setBounds(10, 20, 180, 25);
        panel.add(patientLabel);

        // Lista de pacientes cadastrados
        JList<String> pacienteList = new JList<>();
        DefaultListModel<String> listModel = new DefaultListModel<>();

        // Adiciona os pacientes à lista
        ArrayList<Paciente> listaPacientes = CadastroPacienteView.getListaDePacientes();
        for (Paciente paciente : listaPacientes) {
            listModel.addElement(paciente.toString());  // Exibe nome, prontuário, status e sala
        }
        pacienteList.setModel(listModel);
        pacienteList.setBounds(10, 50, 450, 150);
        panel.add(pacienteList);

        // Botões de ação
        JButton callButton = new JButton("Chamar Paciente");
        callButton.setBounds(10, 220, 150, 25);
        panel.add(callButton);

        JButton editButton = new JButton("Sala Paciente");
        editButton.setBounds(160, 220, 150, 25);
        panel.add(editButton);

        JButton deleteButton = new JButton("Excluir Paciente");
        deleteButton.setBounds(310, 220, 150, 25);
        panel.add(deleteButton);

        JButton statusButton = new JButton("Alterar Status");
        statusButton.setBounds(160, 250, 150, 25);
        panel.add(statusButton);

        // Botão de sair para voltar ao login
        JButton logoutButton = new JButton("Sair");
        logoutButton.setBounds(310, 250, 150, 25);
        panel.add(logoutButton);

        // Ação do botão de chamar paciente
        callButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String pacienteSelecionado = pacienteList.getSelectedValue();
                if (pacienteSelecionado != null) {
                    String[] pacienteInfo = pacienteSelecionado.split(" - ");
                    String nome = pacienteInfo[0];
                    String prontuario = pacienteInfo[1].replace("Prontuário: ", "");
                    String sala = pacienteInfo[3].replace("Sala: ", "");

                    // Marca o paciente como atendido
                    for (Paciente p : CadastroPacienteView.getListaDePacientes()) {
                        if (p.getNome().equals(nome) && p.getProntuario().equals(prontuario)) {
                            p.setAtendido(true);  // Marca como atendido
                            p.setSala(Integer.parseInt(sala));  // Atualiza a sala
                        }
                    }

                    // Exibe a tela de chamada simulando a sala de espera
                    exibirTelaSalaEspera(nome, prontuario, sala);
                } else {
                    JOptionPane.showMessageDialog(panel, "Nenhum paciente selecionado.");
                }
            }
        });

        // Ação do botão de editar paciente
        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String pacienteSelecionado = pacienteList.getSelectedValue();
                if (pacienteSelecionado != null) {
                    String[] pacienteInfo = pacienteSelecionado.split(" - ");
                    String nome = pacienteInfo[0];
                    String prontuario = pacienteInfo[1].replace("Prontuário: ", "");

                    // Atribui uma sala ao paciente usando um ComboBox
                    String salaSelecionada = (String) JOptionPane.showInputDialog(panel,
                            "Escolha a sala (1 a 10):", "Atribuir Sala",
                            JOptionPane.QUESTION_MESSAGE, null, new String[]{"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"}, "1");

                    if (salaSelecionada != null) {
                        for (Paciente p : CadastroPacienteView.getListaDePacientes()) {
                            if (p.getNome().equals(nome) && p.getProntuario().equals(prontuario)) {
                                p.setSala(Integer.parseInt(salaSelecionada));
                                JOptionPane.showMessageDialog(panel, "Sala " + salaSelecionada + " atribuída ao paciente.");
                            }
                        }

                        atualizarListaDePacientes(panel, pacienteList);
                    }
                } else {
                    JOptionPane.showMessageDialog(panel, "Nenhum paciente selecionado.");
                }
            }
        });

        // Ação do botão de excluir paciente
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String pacienteSelecionado = pacienteList.getSelectedValue();
                if (pacienteSelecionado != null) {
                    String[] pacienteInfo = pacienteSelecionado.split(" - ");
                    String nome = pacienteInfo[0];
                    String prontuario = pacienteInfo[1].replace("Prontuário: ", "");

                    // Exclui o paciente
                    CadastroPacienteView.getListaDePacientes().removeIf(p -> p.getNome().equals(nome) && p.getProntuario().equals(prontuario));

                    atualizarListaDePacientes(panel, pacienteList);

                    JOptionPane.showMessageDialog(panel, "Paciente excluído com sucesso!");
                } else {
                    JOptionPane.showMessageDialog(panel, "Nenhum paciente selecionado.");
                }
            }
        });

        // Ação do botão de alterar status
        statusButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String pacienteSelecionado = pacienteList.getSelectedValue();
                if (pacienteSelecionado != null) {
                    String[] pacienteInfo = pacienteSelecionado.split(" - ");
                    String nome = pacienteInfo[0];
                    String prontuario = pacienteInfo[1].replace("Prontuário: ", "");

                    String[] statuses = {"Atendido", "Não compareceu", "Aguardando atendimento"};
                    String statusSelecionado = (String) JOptionPane.showInputDialog(panel,
                            "Escolha o novo status:", "Alterar Status",
                            JOptionPane.QUESTION_MESSAGE, null, statuses, "Aguardando atendimento");

                    if (statusSelecionado != null) {
                        for (Paciente p : CadastroPacienteView.getListaDePacientes()) {
                            if (p.getNome().equals(nome) && p.getProntuario().equals(prontuario)) {
                                if (statusSelecionado.equals("Atendido")) {
                                    p.setAtendido(true);
                                } else {
                                    p.setAtendido(false);
                                }
                            }
                        }

                        atualizarListaDePacientes(panel, pacienteList);

                        JOptionPane.showMessageDialog(panel, "Status do paciente alterado com sucesso!");
                    }
                } else {
                    JOptionPane.showMessageDialog(panel, "Nenhum paciente selecionado.");
                }
            }
        });

        // Ação do botão de sair - volta para o login
        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Fecha a tela de chamada
                frame.dispose();

                // Exibe a tela de login
                LoginView loginView = new LoginView();
                loginView.exibirTelaLogin();
            }
        });
    }

    // Função para atualizar a lista de pacientes na interface
    private void atualizarListaDePacientes(JPanel panel, JList<String> pacienteList) {
        DefaultListModel<String> listModel = new DefaultListModel<>();
        ArrayList<Paciente> listaPacientes = CadastroPacienteView.getListaDePacientes();
        for (Paciente paciente : listaPacientes) {
            listModel.addElement(paciente.toString());
        }
        pacienteList.setModel(listModel);
    }

    // Função para exibir a tela simulando a chamada na sala de espera
    private void exibirTelaSalaEspera(String nome, String prontuario, String sala) {
        JFrame esperaFrame = new JFrame("Sala de Espera");
        esperaFrame.setSize(300, 150);
        esperaFrame.setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        esperaFrame.add(panel);
        JLabel chamadaLabel = new JLabel("Paciente " + nome + " - Prontuário: " + prontuario + " - Sala: " + sala);
        chamadaLabel.setFont(new Font("Serif", Font.BOLD, 16));
        panel.add(chamadaLabel);

        esperaFrame.setVisible(true);
    }
}
