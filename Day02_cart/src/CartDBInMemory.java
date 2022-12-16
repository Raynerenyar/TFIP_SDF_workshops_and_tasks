import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;

public class CartDBInMemory {

    public HashMap<String, ArrayList<String>> userMap = new HashMap<String, ArrayList<String>>();

    // load from storage to memory
    public void loadDataFromDB(String baseFolder, String username) {
        Path filePath = Paths.get(baseFolder, String.format("%s.db", username));

        // if .db exist, load db into userMap
        if (Files.exists(filePath)) {
            // read each line of file and load into userMap
            try {
                File fobj = filePath.toFile();
                FileReader fr = new FileReader(fobj);
                BufferedReader bdf = new BufferedReader(fr);
                String line;
                ArrayList<String> tempArr = new ArrayList<String>();
                while ((line = bdf.readLine()) != null) {
                    tempArr.add(line);
                }
                userMap.put(username, tempArr);
                bdf.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            // create new .db if username.db does not exist
            File file = new File(String.format("%s/%s.db", baseFolder, username));
            try {
                file.createNewFile();
            } catch (Exception error) {
                System.out.println("Unable to create db:");
                error.printStackTrace();
            }
        }
    }

    public void saveDataToDB(String baseFolder, String username) {

        // append to DB
        String filename = String.format("%s/%s.db", baseFolder, username);
        try {

            // creating obj to write to db file
            FileWriter writeToFile = new FileWriter(filename, true);
            BufferedWriter writeToBuffer = new BufferedWriter(writeToFile);

            // creating obj to read existing db
            Path filePath = Paths.get(baseFolder, String.format("%s.db", username));
            File fobj = filePath.toFile();
            FileReader fr = new FileReader(fobj);
            BufferedReader bdf = new BufferedReader(fr);

            // copy items in storage to temporary array
            String line;
            ArrayList<String> tempArr = new ArrayList<String>();
            while ((line = bdf.readLine()) != null) {
                tempArr.add(line);
            }

            /*
             * loops through list of items in memory and loops through list of items in
             * storage (.db)
             * if newly added item is in memory but not in storage,
             * add to storage.
             */
            Integer i = 0;
            // loop through items in memory
            for (String itemInMem : userMap.get(username)) {

                // loop through items in storage
                for (String itemInStorage : tempArr) {
                    // item in memory is inside intem in storage break loop
                    if (itemInMem.equalsIgnoreCase(itemInStorage)) {
                        break;
                    }
                    // for each item checked against memory, i++
                    i++;
                }
                // when i == num of items in storage, item in mem has been checked against all
                // items in storage
                if (i == tempArr.size()) {
                    // since item in mem is not in storage, write to storage
                    writeToBuffer.write(itemInMem + "\n");
                }
                // resets counter after checking item in mem against all items in storage
                // OR item in mem is in storage
                i = 0;
            }
            writeToBuffer.close();
            bdf.close();
        } catch (IOException error) {
            error.printStackTrace();
        }
    }
}
