package day09;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class HttpClientConnection implements Runnable {
    private Socket socket;
    private String directories;

    public HttpClientConnection(Socket socket, String directories) {
        this.socket = socket;
        this.directories = directories;
    }

    @Override
    public void run() {
        try {
            InputStream is = socket.getInputStream();
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader bfr = new BufferedReader(isr);
            String line = bfr.readLine();
            System.out.println(line);
            String[] splitLine = line.split(" ");
            // wrong method received // stop and exit program is this fails
            if (!splitLine[0].equals("GET")) {
                try {
                    OutputStream os = socket.getOutputStream();
                    HttpWriter hw = new HttpWriter(os);
                    // String fullMsg = "HTTP/1.1 405 " + returnMsg + "\r\n\r\n" + splitLine[0] + "
                    // not supported \r\n";
                    hw.writeString("HTTP/1.1 405 Method not allowed");
                    hw.writeString("");
                    hw.writeString("");
                    hw.writeString(splitLine[0] + " not supported");
                    hw.flush();
                    hw.close();
                    System.exit(1);
                } catch (Exception e) {
                }
            }

            // wrong resource received // stop and exit program is this fails
            if (!verifyFilePath(directories)) {
                try {
                    OutputStream os = socket.getOutputStream();
                    HttpWriter hw = new HttpWriter(os);
                    // String fullMsg = "HTTP/1.1 405 " + returnMsg + "\r\n\r\n" + splitLine[0] + "
                    // not supported \r\n";
                    hw.writeString("HTTP/1.1 404 Not Found");
                    hw.writeString("");
                    hw.writeString("");
                    hw.writeString(splitLine[0] + " not supported");
                    hw.flush();
                    hw.close();
                    System.exit(1);
                } catch (Exception e) {
                }
            }
            if (splitLine[0].equals("GET")) {
                OutputStream os = socket.getOutputStream();
                HttpWriter hw = new HttpWriter(os);
                if (splitLine[1].equals("/index.html")) {
                    try {
                        hw.writeString("HTTP/1.1 200 OK");
                        hw.writeString("Content-Type: text/html");
                        hw.writeString("");
                        // all files (html, image, css) can be send in bytes
                        hw.writeBytes(readByteFromFile(directories + "/index.html"));
                        // String indexString = readStringFromFile(directories + "/index.html");
                        // hw.writeString(indexString);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                if (splitLine[1].equals("/images/dynamic-duo.png")) {
                    try {
                        hw.writeString("HTTP/1.1 200 OK");
                        hw.writeString("Content-Type: image/png");
                        hw.writeString("");
                        hw.writeBytes(readByteFromFile(directories + "/images/dynamic-duo.png"));
                        // send images in bytes instead of string
                        // hw.writeString(readStringFromFile(directories + "/images/dynamic-duo.png"));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                if (splitLine[1].equals("/styles.css")) {
                    try {
                        hw.writeString("HTTP/1.1 200 OK");
                        // hw.writeString("Content-Type: styles/css");
                        hw.writeString("");
                        // css file can be send in bytes
                        hw.writeBytes(readByteFromFile(directories + "/styles.css"));
                        // String cssString = readStringFromFile(directories + "/styles.css");
                        // hw.writeString(cssString);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } // if
                try {
                    hw.flush();
                    hw.close();
                } catch (Exception e) {
                }

            } // if get
        } catch (IOException e) {
            e.printStackTrace();
        }
    } // run method

    public static Boolean verifyFilePath(String directories) {
        String[] directoryTerms = directories.split(":");
        for (String path : directoryTerms) {
            Path filePath = Paths.get(path);
            Boolean isReadable = Files.isReadable(filePath);
            File file = new File(path);
            Boolean isExist = file.exists();
            Boolean isDirectory = !file.isFile();
            if (!isExist || !isDirectory || !isReadable) {
                System.out.println("Exiting...");
                // System.exit(1);
                return false;
            }
        }
        return true;
    }

    public static byte[] readByteFromFile(String filePath)
            throws IOException {
        File file = new File(filePath);
        FileInputStream fl = new FileInputStream(file);
        byte[] arr = new byte[(int) file.length()];
        fl.close();
        return arr;
    }

    public static String readStringFromFile(String filePath)
            throws IOException {
        File file = new File(filePath);
        FileInputStream fl = new FileInputStream(file);
        InputStreamReader isr = new InputStreamReader(fl);
        BufferedReader bfr = new BufferedReader(isr);
        String line;
        String outputString = "";
        // List<String> list = new LinkedList<>();
        while ((line = bfr.readLine()) != null) {
            // list.add(line);
            outputString += line;
        }
        bfr.close();

        return outputString;
    }

}
