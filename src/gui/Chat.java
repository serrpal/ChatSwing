package gui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Chat extends JFrame {
    public static JLabel nicknameLabel;
    private JButton sendButton;
    public JTextArea chatBox;
    public JTextField messageTextField;
    private JPanel chatPanel;
    private static String nickname;
    private PrintWriter out;
    private Socket clientSocket;

    public Chat(String nickname) {
        Chat.nickname = nickname;
        nicknameLabel.setText(nickname);

        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String message = messageTextField.getText();
                if (!message.isEmpty()) {
                    //Send message to server
                    out.println(message);
                    messageTextField.setText("");
                } else {
                    JOptionPane.showMessageDialog(null, "No puedes enviar un mensaje vacío");
                }
            }
        });
        try {
            String host = "localhost";
            int port = 12345;
            clientSocket = new Socket(host, port);
            out = new PrintWriter(clientSocket.getOutputStream(), true);
            out.println(nickname);
            // Starts a new thread to  read messages continuouslyfrom the server
            Thread receiveThread = new Thread(this::addMessages);
            receiveThread.start();

        }catch (IOException e){

        }
    }
    private void addMessages() {
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            String receivedMessage;
            while ((receivedMessage = in.readLine()) != null) {
                // Append received message to the chat box
                chatBox.append(receivedMessage + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
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