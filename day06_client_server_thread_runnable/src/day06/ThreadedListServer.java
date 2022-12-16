package day06;

import java.net.ServerSocket;
import java.net.Socket;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadedListServer {
    public static void main(String[] args) {
        Integer port = Integer.parseInt(args[0]);
        try {
            ServerSocket server = new ServerSocket(port);
            System.out.printf("Listening on port %d\n", port);

            ExecutorService thrPool = Executors.newFixedThreadPool(2);

            // accepts a connection from one client and get Socket
            Socket socket;
            while (true) {
                // waits for connection
                System.out.println("waiting for connections...");
                socket = server.accept();

                // Create handleClient to handle the Client socket
                HandleClient client = new HandleClient(socket);
                // do not do this, this is not a thread => Client.run();
                thrPool.submit(client);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.printf("Listening on port %d", port);
    }
}
