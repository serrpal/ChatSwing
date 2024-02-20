package gui;

import gui.Chat;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Login extends JFrame{
    private JTextField nicknameTextField;
    public JLabel nicknameLabel;
    private JButton acceptButton;
    private JPanel loginPanel;

    public Login() {
        acceptButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nickname = nicknameLabel.getText();
                if(!nickname.isEmpty()){
                    Chat chat = new Chat();
                    chat.nicknameLabel.setText(nickname);
                    loginPanel.setVisible(false);
                    chat.setVisible(true);

                }else{
                    JOptionPane.showMessageDialog(null, "No puedes dejar el nickname vacío");
                }
            }
        });
    }

    public static void main(String[] args) {
        Login login = new Login();

        login.setContentPane(login.loginPanel);
        login.setSize(400,300);
        login.setTitle("Inicio de Sesión");
        login.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        login.setVisible(true);
    }
}
