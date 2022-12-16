package day06;

import java.net.ServerSocket;
import java.net.Socket;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ListServer {
    public static void main(String[] args) {
        Integer port = Integer.parseInt(args[0]);
        try {
            ServerSocket server = new ServerSocket(port);
            System.out.printf("Listening on port %d\n", port);

            // accepts a connection from one client and get Socket
            Socket socket;
            while (true) {
                // waits for connection
                System.out.println("waiting for connections...");
                socket = server.accept();
                System.out.printf("New connection on port %s\n", socket.getPort());

                // read input
                String message = IOUtils.read(socket);
                String[] splitMessage = message.split(" ");
                Integer nTimes = Integer.parseInt(splitMessage[0]);
                Integer limit = Integer.parseInt(splitMessage[1]);
                System.out.printf("Message received: %s\n", message);

                // empty list to store random nums
                List<Integer> listOfNums = new ArrayList<>();

                // generate random n numbers
                int i = 0;
                Random rand = new SecureRandom();
                while (i < nTimes) {
                    Integer randomNum = rand.nextInt(limit);
                    listOfNums.add(randomNum);
                    i++;
                }

                // join list of integer as string separated by ","
                String payload = String.join(",", String.valueOf(listOfNums).replaceAll("[\\]\\[\s]+", ""));
                IOUtils.write(socket, payload);

                server.close();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.printf("Listening on port %d", port);
    }
}
