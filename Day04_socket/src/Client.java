import java.io.BufferedOutputStream;
import java.io.Console;
import java.io.DataOutputStream;
import java.net.Socket;

public class Client {
    public static void main(String[] args) {
        int PORT = 3000;
        try {
            // init connection with server side
            Socket socket = new Socket("localhost", PORT);
            DataOutputStream dos = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()));

            Console cons = System.console();
            // while connection is open, send any user input to server
            while (true) {
                String input = cons.readLine();
                if (input.equalsIgnoreCase("exit")) {
                    break;
                }
                dos.writeUTF(input);
                dos.flush();
            }
            socket.close();

        } catch (Exception e) {
            ;
        }
    }
}
