import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client {

    public static void main(String[] args) {
        int PORT = 12345;
        try {
            Socket socket = new Socket("localhost", PORT);

            // get I/O streams
            OutputStream os = socket.getOutputStream();
            BufferedOutputStream bos = new BufferedOutputStream(os);
            DataOutputStream dos = new DataOutputStream(bos);

            // dos.writeUTF("Hello world from client side");
            // dos.writeUTF("more messages");

            // dos.flush();
            // socket.close();
            // System.out.println("MEssage sent to server");

            Scanner inputSC = new Scanner(System.in);
            String line;
            while ((line = inputSC.nextLine()) != null) {
                dos.writeUTF(line);
                dos.flush(); // flushes output stream
                System.out.println("Msg sent o client: " + line);
                if (line.equalsIgnoreCase("close")) {
                    System.out.println("Exit from shell");
                    break;
                }
            }
            socket.close();
            inputSC.close();

        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}