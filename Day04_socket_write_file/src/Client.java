import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.FileReader;
import java.io.Reader;
import java.net.Socket;

public class Client {
    public static void main(String[] args) {
        int PORT = 3000;
        try {
            // init connection with server side
            Socket socket = new Socket("localhost", PORT);
            DataOutputStream dos = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()));

            // init objs to read file
            String filepath = "FromClient.txt";
            Reader readerObj = new FileReader(filepath);
            BufferedReader bufferedReaderObj = new BufferedReader(readerObj);
            String line;

            // while connection is open, send any user input to server
            while (true) {
                // read each line in file and send to server
                line = bufferedReaderObj.readLine();
                if (line == null) {
                    dos.writeUTF("EOF");
                    break;
                }
                dos.writeUTF(line);
                dos.flush();
            }
            socket.close();
            bufferedReaderObj.close();

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("client side error");
        }
    }
}
