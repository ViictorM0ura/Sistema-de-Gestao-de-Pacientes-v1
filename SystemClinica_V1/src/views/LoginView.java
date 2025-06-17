package views;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginView {

    // Declarar frame como variável de instância
    private JFrame frame;

    public void exibirTelaLogin() {
        frame = new JFrame("Tela de Login");  // Inicializando frame
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLocationRelativeTo(null); // Centraliza a janela

        JPanel panel = new JPanel();
        frame.add(panel);
        placeComponents(panel);

        frame.setVisible(true);
    }

    // Adicionando os componentes na tela
    private void placeComponents(JPanel panel) {
        panel.setLayout(null);

        // Rótulo de usuário
        JLabel userLabel = new JLabel("Usuário:");
        userLabel.setBounds(10, 20, 80, 25);
        panel.add(userLabel);

        // Campo de texto para usuário
        JTextField userText = new JTextField(20);
        userText.setBounds(100, 20, 165, 25);
        panel.add(userText);

        // Rótulo de senha
        JLabel passwordLabel = new JLabel("Senha:");
        passwordLabel.setBounds(10, 50, 80, 25);
        panel.add(passwordLabel);

        // Campo de texto para senha
        JPasswordField passwordText = new JPasswordField(20);
        passwordText.setBounds(100, 50, 165, 25);
        panel.add(passwordText);

        // Botão de login
        JButton loginButton = new JButton("Login");
        loginButton.setBounds(10, 80, 150, 25);
        panel.add(loginButton);

        // Ação do botão
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String usuario = userText.getText();  // Recebe o nome do usuário
                String senha = new String(passwordText.getPassword());  // Recebe a senha

                // Lógica de verificação de login
                if (usuario.equals("ate") && senha.equals("123")) {
                    JOptionPane.showMessageDialog(panel, "Login como Atendente realizado!");
                    frame.dispose();  // Fecha a tela de login
                    // Redireciona para a tela de cadastro de paciente
                    new CadastroPacienteView().exibirTelaCadastro();
                } else if (usuario.equals("med") && senha.equals("123")) {
                    JOptionPane.showMessageDialog(panel, "Login como Médico realizado!");
                    frame.dispose();  // Fecha a tela de login
                    // Redireciona para a tela de chamar paciente
                    new ChamarPacienteView().exibirTelaChamada();
                } else {
                    JOptionPane.showMessageDialog(panel, "Credenciais incorretas. Tente novamente.");
                }
            }
        });
    }
}
