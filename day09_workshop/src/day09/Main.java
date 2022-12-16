package day09;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Integer argsLength = args.length;
        Integer PORT = 3000;
        String directory = "./static";
        List<String> list = new LinkedList<String>(Arrays.asList(args));
        // String[] directoryTerms;
        if (argsLength > 0) {
            if (list.contains("--docRoot") && list.contains("--port")) {
                if (argsLength == 4) {
                    PORT = Integer.parseInt(args[1]);
                    directory = args[3];
                }
            } else if (list.contains("--port")) {
                if (argsLength == 2) {
                    PORT = Integer.parseInt(args[1]);
                }
            } else if (list.contains("--docRoot")) {
                if (argsLength == 2) {
                    directory = args[1];
                }
            } // if
        } // if
        System.out.printf("Port: %d, Directories: %s\n", PORT, directory);
        HttpServer.startServer(PORT, directory);
    } // main method

}
// Main class
