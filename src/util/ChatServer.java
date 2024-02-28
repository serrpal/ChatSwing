package util;

import gui.Chat;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class ChatServer implements Runnable{

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
}
