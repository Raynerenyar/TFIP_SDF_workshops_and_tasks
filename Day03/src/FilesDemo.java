import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FilesDemo {
    public static void main(String[] args) {
        String filePath = "src/demo.txt";
        readFile(filePath);
        String writeFilePath = "src/output.txt";
        writeFile(writeFilePath);
    }

    public static void readFile(String fileName) {
        // Path object
        Path pth = Paths.get(fileName);
        File fobj = pth.toFile();
        if (fobj.exists()) {
            System.out.println("file exist");
        } else {
            System.out.println("File not found");
        }
        // reader object
        try {

            // opens and read file
            FileReader fr = new FileReader(fobj);
            BufferedReader bdf = new BufferedReader(fr);

            String line;
            while ((line = bdf.readLine()) != null) {
                System.out.println("line in file => " + line);
            }
            // close file
            bdf.close();

        } catch (FileNotFoundException e) {
            System.out.println("File not found. Please check input file" + e.getMessage());
        } catch (IOException e) {
            System.out.println("Unable to read line" + e.getMessage());
        }

        // consume the content
    }

    public static void writeFile(String fileName) {
        // File fobj = Paths.get(fileName).toFile();
        try {
            // FileReader fr = new FileReader(fobj);
            FileWriter bfr = new FileWriter(fileName, false);
            BufferedWriter bfw = new BufferedWriter(bfr);
            bfw.write("apple\n");
            bfw.write("orange\n");
            bfw.write("durian\n");
            bfw.flush(); // what is flush()?
            bfw.close();
        } catch (IOException e) {
            System.out.println("Unable to read line" + e.getMessage());
        }
    }
}
