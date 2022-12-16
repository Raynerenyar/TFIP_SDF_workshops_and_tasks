import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class Cookie {

    public static String getRandomCookie(String cookieFilePath) {

        List<String> cookieList = new LinkedList<String>();
        // read cookie file
        try {
            BufferedReader bufferedReaderObj = new BufferedReader(new FileReader(cookieFilePath));
            String line;
            while (true) {
                line = bufferedReaderObj.readLine();
                if (line == null) {
                    break;
                }
                cookieList.add(line);

            }
            bufferedReaderObj.close();
        } catch (FileNotFoundException e) {
            System.out.println("file not found");
        } catch (IOException e) {
            e.printStackTrace();
        }

        // randomly get line (cookie)
        Random rand = new Random();
        Integer randNum = rand.nextInt(cookieList.size());

        // returns cookie
        return cookieList.get(randNum);

    }

    public static void getCookie() {
        // return randomise cookie from text file
    }
}
