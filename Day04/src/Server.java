import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static void main(String[] args) {
        System.out.println("Socket server: ");
        int PORT = 12345;

        // create serversocket
        try {
            ServerSocket server = new ServerSocket(PORT);
            // get socket object
            Socket socket = server.accept();
            // new connection from a client. (we just accepted it)

            InputStream is = socket.getInputStream();
            BufferedInputStream bis = new BufferedInputStream(is);
            DataInputStream dis = new DataInputStream(bis);

            String MsgFromClient = dis.readUTF();
            while (!MsgFromClient.equalsIgnoreCase("close") && MsgFromClient != null) {

                // process the msg
                System.out.println("received from client -> " + MsgFromClient);
                MsgFromClient = dis.readUTF();
            }
            // String message = dis.readUTF();
            // System.out.println("messaged received: " + message);
            // message = dis.readUTF();
            // System.out.println(message);

            // close socket
            // socket.close();
            server.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}