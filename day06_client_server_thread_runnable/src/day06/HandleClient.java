package day06;

import java.io.IOException;
import java.net.Socket;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class HandleClient implements Runnable {

    private Socket socket;

    public HandleClient(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {

        System.out.printf("New connection on port %s\n", socket.getPort());

        try {
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

        } catch (Exception e) {
            e.getMessage();
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
