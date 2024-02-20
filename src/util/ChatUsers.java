package util;

import gui.Chat;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class ChatUsers extends Thread{
    public static void main(String[] args) {
    }
    public void getMessage(String ms){
        Chat chat = new Chat();
        int puerto = 12345;
        try {
            MulticastSocket socket=new MulticastSocket();
            byte[] mensaje=ms.getBytes();
            DatagramPacket dp=new DatagramPacket(mensaje,mensaje.length, InetAddress.getByName("225.0.0.1"),puerto);
            socket.send(dp);
            socket.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
