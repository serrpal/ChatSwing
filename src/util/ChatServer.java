package util;

import data.User;
import gui.Chat;
import gui.Login;

import javax.swing.*;
import java.io.IOException;
import java.net.*;

public class ChatServer extends Thread{
    public static void main(String[] args) {
        Chat c = new Chat();
        Login l = new Login();
        User u = new User();
        int puerto = 12345;
        try {
            DatagramSocket server = new DatagramSocket(puerto);
            byte[] buffer = new byte[256];
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
            server.receive(packet);

            while(!server.isClosed()) {
                DatagramPacket entrada = new DatagramPacket(buffer, buffer.length);

                String texto = new String(entrada.getData());

                JLabel jl = new JLabel();
                jl.setText(" <" + l.nicknameLabel.getText() + "> : " + texto);
                c.chatBox.add(jl);
            }

            server.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
