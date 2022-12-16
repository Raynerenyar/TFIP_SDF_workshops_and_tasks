import java.io.DataInputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.io.BufferedInputStream;
import java.io.InputStream;

public class Server {
    public static void main(String[] args) {
        System.out.println("Server socket");
        int PORT = 12345;

        try {
            ServerSocket server = new ServerSocket(PORT);
            Socket socket = server.accept();

            InputStream is = socket.getInputStream();
            BufferedInputStream bis = new BufferedInputStream(is);
            DataInputStream dis = new DataInputStream(bis);

            String msgFromClient = dis.readUTF();

            while (!msgFromClient.equalsIgnoreCase("EOF") && msgFromClient != null) {
                System.out.println("got: " + msgFromClient);
                try {
                    msgFromClient = dis.readUTF();
                } catch (EOFException e) {
                    System.out.println("Reached end of file");
                    break;
                }
            }
            server.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
