import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
    public static void main(String[] args) {
        int PORT = 12345;
        try {
            Socket socket = new Socket("localhost", PORT);

            DataOutputStream dos = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()));

            // read a file
            FileReader readingFile = new FileReader("input.txt");
            BufferedReader readingBuffer = new BufferedReader(readingFile);
            String line;

            while ((line = readingBuffer.readLine()) != null) {
                dos.writeUTF(line);
                dos.flush();

            }
            // dos.writeUTF("EOF");
            // dos.flush();
            socket.close();
            readingBuffer.close();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
