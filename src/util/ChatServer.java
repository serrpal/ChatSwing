package util;

import datos.User;
import gui.Chat;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class ChatServer extends Thread {
    private static final int PORT = 12345;
    private Set<ObjectOutputStream> outputStreams = new HashSet<>();

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

                ObjectOutputStream out = new ObjectOutputStream(clientSocket.getOutputStream());
                outputStreams.add(out); // Add output stream to the set

                new Thread(new ClientHandler(clientSocket)).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public synchronized void broadcastMessage(User user) {
        Chat c = new Chat(user.getNickname());
        for (ObjectOutputStream out : outputStreams) {
            try {
                out.writeObject(user);
                c.addMessage("<"+user.getNickname() + ">", user.getMessage());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private class ClientHandler implements Runnable {
        private Socket socket;

        public ClientHandler(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            try (ObjectInputStream in = new ObjectInputStream(socket.getInputStream())) {
                while (true) {
                    User user = (User) in.readObject();
                    broadcastMessage(user);
                }
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}