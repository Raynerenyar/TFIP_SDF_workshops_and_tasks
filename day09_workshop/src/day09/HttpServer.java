package day09;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class HttpServer {
    public static void startServer(Integer PORT, String directories) {
        String[] directoryTerms = directories.split(":");
        if (directories.contains(":")) {
            directoryTerms = directories.split(":");
        }
        try {
            ExecutorService thrPool = Executors.newFixedThreadPool(3);
            ServerSocket server = new ServerSocket(PORT);
            System.out.printf("Server listening on: %d\n", PORT);
            while (true) {
                Socket socket = server.accept();
                HttpClientConnection client = new HttpClientConnection(socket, directories);
                thrPool.submit(client);
            }
            // server.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
