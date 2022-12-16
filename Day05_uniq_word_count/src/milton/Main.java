package milton;

import java.util.StringTokenizer;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;

// open the file
// read the first 100 lines, print out each line
// close the file when done
// if the file is less than 100 lines, close when done
public class Main {
    public static final String HEADER = "WORD,COUNT\n";

    public static void main(String[] args) {

        String filename = args[0];
        String outputCSVfilename = args[1];
        Long lineCount = 0L;
        String line;
        Map<String, Integer> uniqueWords = new HashMap<>();
        Long wordCounter = 0L;

        System.out.printf("Processing %s\n", filename);
        // Path filePath = Paths.get(filename);
        try {
            // InputStream is = new FileInputStream(filePath.toFile());
            FileReader reader = new FileReader(filename);
            // FileReader reader = new FileReader(filePath.toFile());
            BufferedReader bfr = new BufferedReader(reader);

            while ((line = bfr.readLine()) != null) { // && lineCount <= 100
                lineCount++;
                StringTokenizer st = new StringTokenizer(line);
                if (st.hasMoreTokens()) { // && lineCount >= 91
                    String[] wordsInLine = line.trim().split("[,.\s:?!();:]+"); // [,.\s:?!]+
                    wordCounter += wordsInLine.length;
                    for (String word : wordsInLine) {
                        // exclude ""
                        if (!word.isBlank()) {
                            Integer i = uniqueWords.getOrDefault(word.toLowerCase(), 0);
                            i++;
                            uniqueWords.put(word.toLowerCase(), i);
                        }
                    } // for
                } // if
            } // while
            reader.close();
            bfr.close();

        } catch (Exception e) {
            e.printStackTrace();
        } // try-catch

        // print out each unique word and its word count
        Integer count = 0;
        for (String w : uniqueWords.keySet()) {
            count++;
            System.out.printf("> %s: %d\n", w, uniqueWords.get(w));
        }

        // print total num of words and unique word count
        System.out.printf("Word Count: %d\n", wordCounter);
        System.out.printf("Unique words count %d\n", uniqueWords.size());

        // write to csv file
        try {
            FileWriter fw = new FileWriter(outputCSVfilename);
            BufferedWriter bfw = new BufferedWriter(fw);
            bfw.write(HEADER);
            for (String word : uniqueWords.keySet()) {
                String newCSVLine = word + "," + Integer.toString(uniqueWords.get(word)) + "\n";
                bfw.write(newCSVLine);
            }
            // bfw.flush();
            bfw.close();
        } catch (Exception e) {
            e.printStackTrace();
        } // try-catch
    } // main
}
