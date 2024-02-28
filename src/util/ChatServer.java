package util;

import gui.Chat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;


public class ChatServer implements Runnable{

    private ArrayList<Handler> clientsConnected;
    private ServerSocket server;
    private boolean exit;
    private int port = 12345;

    public ChatServer(){
        exit = false;
    }

    @Override
    public void run() {
        try {
            server = new ServerSocket(port);
            while (!exit){
                Socket client = server.accept();
            }
        }catch (IOException e){
            //TODO
        }
    }

    class Handler implements Runnable{

        private Socket client;
        private BufferedReader in;
        private PrintWriter out;
        private String username;

        public Handler(Socket client){
            this.client=client;
        }
        @Override
        public void run() {
            try {
                out = new PrintWriter(client.getOutputStream(), true);
                in = new BufferedReader(new InputStreamReader(client.getInputStream()));
                //Gets username from GUI
                username = in.readLine();
                //Shows nickname to all clients connected
                broadcastMessage(username + " se unió al chat");

            }catch (IOException e){
                //TODO : handle exception
            }
        }

        public void broadcastMessage(String message){
            for (Handler ch : clientsConnected){
                if (ch!=null){
                    //TODO
                }
            }
        }
    }
}
