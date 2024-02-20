package util;

import gui.Chat;
import gui.Login;

import javax.swing.*;
import java.io.IOException;
import java.net.*;

public class ChatServer extends Thread{
    public static void main(String[] args) {
        Chat c = new Chat();
        Login l = new Login();
        int puerto = 12345;
        try {
            ServerSocket server = new ServerSocket(puerto);
            Socket client = server.accept();

            while(!client.isClosed()) {
                byte[] buffer = new byte[256];
                DatagramPacket entrada = new DatagramPacket(buffer, buffer.length);

                String texto = new String(entrada.getData());

                JLabel jl = new JLabel();
                jl.setText(" <" + l.nicknameLabel.getText() + "> : " + texto);
                Chat.chatBox.add(jl);
            }

            server.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
