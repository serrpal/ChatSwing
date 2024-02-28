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
    public ChatServer(){
        exit = false;
    }

    @Override
    public void run() {

    }
}
