package util;

import datos.User;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
public class ChatUsers {
    private static final String SERVER_HOST = "localhost";
    private static final int SERVER_PORT = 12345;

    public static void sendMessage(String nickname, String message) {
        try (Socket socket = new Socket(SERVER_HOST, SERVER_PORT);
             ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream())) {

            User user = new User(nickname, message);
            out.writeObject(user);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
