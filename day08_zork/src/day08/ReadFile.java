package day08;

import java.io.BufferedReader;
import java.util.LinkedList;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Arrays;
import java.util.List;
import java.io.Reader;
import java.util.Map;

public class ReadFile {

    private static Map<String, Location> mapOfLocs = new HashMap<String, Location>();
    private static final String ROOM = "room";
    // private static final String NAME = "name";
    private static final String DESCRIPTION = "description";
    private static final String DIRECTION = "direction";
    private static final String START = "start";
    private static final String DELIMITER = "<break>";

    public static Map<String, Location> parseFile(String filename) {
        try {
            Reader r = new FileReader(filename);
            BufferedReader bfr = new BufferedReader(r);
            String line;
            List<String> textBlock = new LinkedList<>();
            // parse through file and look for specific block starting with "room" and
            // ending with "\n" in block
            while ((line = bfr.readLine()) != null) {
                if (line.contains(ROOM)) {
                    textBlock.add(line);
                    while ((line = bfr.readLine()) != null && !(line.equals("")))
                        // add lines to a list
                        textBlock.add(line);
                    // pass textBlock to be parsed
                    // and create location object and put into Map
                    Location loc = readAndStoreLocations(textBlock);
                    loc = mapOfLocs.put(loc.getNAME(), loc);
                    textBlock.removeAll(textBlock);
                } else if (line.contains(START)) {
                    // set starting point of character
                    mapOfLocs.get(line.split(":")[1].strip().toUpperCase()).setInRoom(true);
                }
            }
            bfr.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mapOfLocs;
    }

    /**
     * parse block of text starting from word "room" and ends when there is a
     * linebreak
     * 
     * @param List<String> text
     * @return
     */
    public static Location readAndStoreLocations(List<String> text) {
        List<String> descrip = new LinkedList<>();
        List<String> dir = new LinkedList<>();
        String name = "";
        Location loc;
        // method receive a wall of text. for each line get the first word,
        for (String string : text) {
            String[] splitText = string.split(":");
            switch (splitText[0].strip()) {
                case ROOM -> name = splitText[1].strip().toUpperCase();
                case DESCRIPTION -> {
                    descrip.addAll(Arrays.asList(splitText[1].strip().split(DELIMITER)));
                }
                case DIRECTION -> dir.add(splitText[1].strip());
            }
        }
        loc = new Location(name, descrip, dir);
        return loc;
    }
}
