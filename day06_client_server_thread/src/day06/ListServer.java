package day06;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ListServer {
    public static void main(String[] args) {

        Integer port = Integer.parseInt(args[0]);

        try {
            ServerSocket server = new ServerSocket(port);
            while (true) {
                Socket socket = server.accept();
                // run threads
                Thread tsh = new ThreadHandler(port, socket);
                tsh.start();
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
}
