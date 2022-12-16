import java.io.DataInputStream;
import java.io.EOFException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedInputStream;
import java.io.BufferedWriter;
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

            // init writer
            String filePath = "ToServer.txt";
            FileWriter fw = new FileWriter(filePath, false);
            BufferedWriter bfw = new BufferedWriter(fw);
            // while connection is open, wait for message to be received
            while (true) {
                String line = dis.readUTF();
                // dis.readUTF throws an error allowing catch to get error
                if (line.equalsIgnoreCase("EOF")) {
                    break;
                }
                bfw.write(line + "\n");
                bfw.flush();
                System.out.println("Msg from client: " + line);
            }
            bfw.close();
            server.close();
            // when client side close socket - server side gets EOF exception
        } catch (EOFException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("server side error");
        }
    }
}
