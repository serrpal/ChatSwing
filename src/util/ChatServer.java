package util;

import datos.User;
import gui.Chat;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashSet;
import java.util.Set;

public class ChatServer extends Thread {
    private static final int PORT = 12345;
    private Set<ObjectInputStream> inputStreams = new HashSet<>();

    public static void main(String[] args) {
        ChatServer server = new ChatServer();
        server.start();
    }
    public void start() {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Server started on port " + PORT);

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("New client connected: " + clientSocket.getInetAddress().getHostAddress());

                ObjectInputStream ois = new ObjectInputStream(clientSocket.getInputStream());
                broadcastMessage((User) ois.readObject());
                inputStreams.add(ois);

            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public synchronized void broadcastMessage(User user) {
        Chat c = new Chat(user.getNickname());
        c.addMessage("<" + user.getNickname() + ">", user.getMessage());
    }
}