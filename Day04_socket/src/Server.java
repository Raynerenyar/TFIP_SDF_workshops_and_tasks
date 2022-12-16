import java.io.DataInputStream;
import java.io.BufferedInputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {
        int PORT = 3000;
        try {
            // init server socket
            ServerSocket server = new ServerSocket(PORT);
            Socket socket = server.accept(); // accepts one client
            DataInputStream dis = new DataInputStream(new BufferedInputStream(socket.getInputStream()));

            // while connection is open, wait for message to be received
            while (true) {
                String line = dis.readUTF();
                if (line.equalsIgnoreCase("exit")) {
                    break;
                }
                System.out.println("Msg from client: " + line);
            }
            server.close();
        } catch (Exception e) {
            ;
        }
    }
}
