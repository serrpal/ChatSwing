package gui;

import util.ChatServer;
import util.ChatUsers;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Chat extends JFrame {
    public JLabel nicknameLabel;
    private JButton sendButton;
    public JTextArea chatBox;
    public JTextField messageTextField;
    private JPanel chatPanel;
    private static String nickname;
    public Chat(String nickname) {
        this.nickname = nickname;

        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                nicknameLabel.setText(nickname);
                String message = messageTextField.getText();
                if (!message.isEmpty()) {
                    ChatServer cs = new ChatServer();
                    ChatUsers.sendMessage(nickname, message);
                    messageTextField.setText(""); // Clear the message text field after sending
                } else {
                    JOptionPane.showMessageDialog(null, "No puedes enviar un mensaje vacío");
                }
            }
        });
    }
        public void addMessage(String message, String nick) {
            chatBox.append("<" + nick + "> " + message + "\n");
        }

        public static void main(String[] args) {
            nickname = JOptionPane.showInputDialog("Introduce tu nombre: ");
            Chat c = new Chat(nickname);

            c.chatBox.setEditable(false);
            c.setContentPane(c.chatPanel);
            c.setSize(400, 500);
            c.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            c.setTitle("Chat");
            c.setVisible(true);
        }
    }