package views;

import models.Paciente;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class CadastroPacienteView {

    // Lista de pacientes cadastrados
    private static ArrayList<Paciente> listaDePacientes = new ArrayList<>();

    public void exibirTelaCadastro() {
        JFrame frame = new JFrame("Cadastro de Paciente");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLocationRelativeTo(null); // Centraliza a janela

        JPanel panel = new JPanel();
        frame.add(panel);
        placeComponents(panel, frame);

        frame.setVisible(true);
    }

    // Adicionando os componentes na tela
    private void placeComponents(JPanel panel, JFrame frame) {
        panel.setLayout(null);

        // Rótulo de nome
        JLabel nameLabel = new JLabel("Nome do Paciente:");
        nameLabel.setBounds(10, 20, 150, 25);
        panel.add(nameLabel);

        // Campo de texto para nome
        JTextField nameText = new JTextField(20);
        nameText.setBounds(160, 20, 165, 25);
        panel.add(nameText);

        // Rótulo de idade
        JLabel ageLabel = new JLabel("Idade:");
        ageLabel.setBounds(10, 50, 150, 25);
        panel.add(ageLabel);

        // Campo de texto para idade
        JTextField ageText = new JTextField(20);
        ageText.setBounds(160, 50, 165, 25);
        panel.add(ageText);

        // Rótulo de prontuário
        JLabel prontuarioLabel = new JLabel("Prontuário:");
        prontuarioLabel.setBounds(10, 80, 150, 25);
        panel.add(prontuarioLabel);

        // Campo de texto para prontuário
        JTextField prontuarioText = new JTextField(20);
        prontuarioText.setBounds(160, 80, 165, 25);
        panel.add(prontuarioText);

        // Botão para cadastrar
        JButton submitButton = new JButton("Cadastrar");
        submitButton.setBounds(10, 110, 150, 25);
        panel.add(submitButton);

        // Botão para sair
        JButton logoutButton = new JButton("Sair");
        logoutButton.setBounds(160, 110, 150, 25);
        panel.add(logoutButton);

        // Ação do botão de cadastrar
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nome = nameText.getText();
                String idadeStr = ageText.getText();
                String prontuario = prontuarioText.getText();

                // Validação de campos vazios
                if (nome.isEmpty() || idadeStr.isEmpty() || prontuario.isEmpty()) {
                    JOptionPane.showMessageDialog(panel, "Todos os campos são obrigatórios.");
                    return;
                }

                // Validação do nome (deve ter pelo menos 3 letras)
                if (nome.length() < 3) {
                    JOptionPane.showMessageDialog(panel, "O nome do paciente deve ter pelo menos 3 letras.");
                    return;
                }

                // Validação de idade (deve ser numérica e com no máximo 3 dígitos)
                int idade;
                try {
                    idade = Integer.parseInt(idadeStr);
                    if (idade <= 0 || idade > 130) {
                        JOptionPane.showMessageDialog(panel, "Idade inválida. Por favor, insira uma idade válida (entre 1 e 130).");
                        return;
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(panel, "Idade deve ser um número válido.");
                    return;
                }

                // Verificação se o prontuário já foi cadastrado
                for (Paciente p : listaDePacientes) {
                    if (p.getProntuario().equals(prontuario)) {
                        JOptionPane.showMessageDialog(panel, "Prontuário já cadastrado. Por favor, insira outro.");
                        return;
                    }
                }

                // Cria um paciente e adiciona à lista
                Paciente paciente = new Paciente(nome, idade, prontuario);
                listaDePacientes.add(paciente);

                JOptionPane.showMessageDialog(panel, "Paciente " + nome + " cadastrado com sucesso!");
            }
        });

        // Ação do botão de sair
        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Fecha a tela de cadastro
                frame.dispose();

                // Exibe novamente a tela de login
                LoginView loginView = new LoginView();
                loginView.exibirTelaLogin();
            }
        });
    }

    // Método para acessar a lista de pacientes cadastrados
    public static ArrayList<Paciente> getListaDePacientes() {
        return listaDePacientes;
    }
}
