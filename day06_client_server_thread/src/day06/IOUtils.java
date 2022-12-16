package day06;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class IOUtils {

    public static void write(Socket socket, String message) throws Exception {

        // get output stream
        OutputStream os = socket.getOutputStream();
        BufferedOutputStream bos = new BufferedOutputStream(os);
        DataOutputStream dos = new DataOutputStream(bos);

        // write output
        System.out.printf("Writing to output... >>>> %s\n", message);
        dos.writeUTF(message);
        dos.flush();

        // A closed stream cannot perform output operations and cannot be reopened.
    }

    public static String read(Socket socket) throws Exception {

        // get input stream
        InputStream is = socket.getInputStream();
        BufferedInputStream bis = new BufferedInputStream(is);
        DataInputStream dis = new DataInputStream(bis);

        // read input
        String payload = dis.readUTF();
        System.out.printf("reading from input <<<< %s\n", payload);

        return payload;
    }
}
