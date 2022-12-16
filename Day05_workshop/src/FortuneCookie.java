import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class FortuneCookie {
    public static void main(String[] args) {

        if (args[0].equalsIgnoreCase("fc.Server")) {
            Integer PORT = Integer.parseInt(args[1]);
            String cookieTxtFile = args[2];
            // initialise server
            System.out.println("starting server");
            Server(PORT, cookieTxtFile);
        }

        if (args[0].equalsIgnoreCase("fc.Client")) {
            String nextTerm = args[1];
            String[] splitTerm = nextTerm.split(":");
            // init client side
            System.out.println("starting client");
            Client(splitTerm[0], Integer.parseInt(splitTerm[1]));
        }
    }

    public static void Server(Integer PORT, String cookieTxtFile) {
        try {
            // init server socket
            ServerSocket server = new ServerSocket(PORT);
            // awaiting request for cookie
            Socket socket = server.accept();
            DataInputStream dis = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
            DataOutputStream dos = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()));
            String inputLine;

            while (true) {
                inputLine = dis.readUTF();
                if (inputLine.equalsIgnoreCase("get-cookie")) {
                    // get random cookie from txt file
                    String cookie = Cookie.getRandomCookie(cookieTxtFile);
                    System.out.println("server sending: " + cookie);
                    dos.writeUTF("cookie-text " + cookie);
                    dos.flush();
                } else if (inputLine.equalsIgnoreCase("close")) {
                    break;
                }

            }
            server.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void Client(String HOST, Integer PORT) {
        try {
            // init connection with server side
            Socket socket = new Socket(HOST, PORT);
            DataOutputStream dos = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()));
            DataInputStream dis = new DataInputStream(new BufferedInputStream(socket.getInputStream()));

            System.out.println("requesting cookie");
            String[] splitMsg = { "", "" };
            Scanner scn = new Scanner(System.in);
            String command = "";
            Boolean state = true;
            command = scn.next();

            while (state) {

                switch (command) {
                    case "get-cookie" -> {
                        dos.writeUTF(command);
                        dos.flush();
                        command = dis.readUTF();
                    }
                    case "cookie-text" -> {
                        System.out.println(splitMsg[1]);
                        command = scn.next();
                    }
                    // closing connection
                    case "close" -> {
                        // sending close message to tell server to close
                        dos.writeUTF("close");
                        dos.flush();

                        scn.close();
                        socket.close();
                        // break while loop
                        state = false;
                    }
                    default -> command = scn.next();
                }

                // user to input command
                if (!command.equalsIgnoreCase("close")) {
                    splitMsg = command.split(" ");
                    command = splitMsg[0];
                }
            }

        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
