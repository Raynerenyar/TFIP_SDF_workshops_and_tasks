import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class SocketApp {
    public static void main(String[] args) {

        // System.out.println(args[0]);
        // System.out.println(args[1]);

        String usage = """
                usage: server
                <program> <server> <port> <cookie-file.txt>
                usage: client
                <program> <client> <host> <port>""";

        if (args.length != 3) {
            System.out.println("Incorrect inputs. please check the following usage.");
            System.out.println(usage);
            return;
        }

        String type = args[0];
        if (type.equalsIgnoreCase("server")) {
            int port = Integer.parseInt(args[1]);
            String filename = args[2];
            startServer(port, filename);
        } else if (type.equalsIgnoreCase("client")) {
            String hostName = args[1];
            // System.out.println(args[1]);
            int port = Integer.parseInt(args[2]);
            // System.out.println(args[2]);
            startClient(port, hostName);
        } else {
            System.out.println("Invalid argument on index 0");
        }

    }

    public static void startServer(int port, String filename) {
        ServerSocket server;
        try {
            server = new ServerSocket(port);
            Socket socket = server.accept(); // only for first available connection

            // input stream
            DataInputStream dis = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
            // output stream
            DataOutputStream dos = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()));

            while (true) {
                // message from client
                String msgFromClient = dis.readUTF();
                if (msgFromClient.equalsIgnoreCase("exit")) {
                    break;
                }
                System.out.println("Msg from client: " + msgFromClient);
                if (msgFromClient.equalsIgnoreCase("get-cookie")) {
                    dos.writeUTF("dummy cookie for client from server");
                    dos.flush();
                } else {
                    dos.writeUTF("From server: Invalid command");
                    dos.flush();
                }
            }
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void startClient(int port, String host) {
        try {
            Socket socket = new Socket(host, port);

            // input stream
            DataInputStream dis = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
            // output stream
            DataOutputStream dos = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()));

            Scanner sc = new Scanner(System.in);
            Boolean stop = false;

            while (!stop) {
                String line = sc.nextLine();
                if (line.equalsIgnoreCase("exit")) {
                    dos.writeUTF("exit");
                    dos.flush();
                    stop = true;
                    break;
                }

                // send a request to server for a cookie.
                dos.writeUTF("get-cookie");
                dos.flush();

                String fromServer = dis.readUTF();
                System.out.println("resp from server! " + fromServer);
            }
            socket.close();
            sc.close();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
