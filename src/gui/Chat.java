package gui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import util.ChatUsers;

public class Chat extends JFrame{
    public JLabel nicknameLabel;
    private JButton sendButton;
    public  JTextArea chatBox;
    public JTextField messageTextField;
    private JPanel chatPanel;

    public Chat() {
        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ChatUsers cu = new ChatUsers();
                cu.getMessage(messageTextField.getText());
            }
        });
    }

    public static void main(String[] args) {
        Chat c = new Chat();

        c.chatBox.setEditable(false);
        c.setContentPane(c.chatPanel);
        c.setSize(400,500);
        c.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        c.setTitle("Chatt");
        c.setVisible(true);
    }
}
