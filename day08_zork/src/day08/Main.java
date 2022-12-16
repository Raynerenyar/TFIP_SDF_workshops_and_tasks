package day08;

import java.util.HashMap;
import java.util.Map;

public class Main {

    public static void main(String[] args) {

        String filename = args[0];
        Map<String, Location> mapOfLocs = new HashMap<String, Location>();
        // read zork txt file
        // and store locations (descriptions and directions available) in Location
        // objects
        mapOfLocs = ReadFile.parseFile(filename);
        // start at location and print description
        String currLocation = Gameplay.checkCurrLocation(mapOfLocs);
        Gameplay.readLocation(currLocation, mapOfLocs);
        Boolean playing = true;
        while (playing) {
            currLocation = Gameplay.checkCurrLocation(mapOfLocs);
            // prompt user input
            String command = Gameplay.prompt();
            // verify user command and also execute some methods in Gameplay
            Boolean verified = Gameplay.verifyCommand(command, currLocation, mapOfLocs);
            if (!verified) {
                System.out.println("Exiting game");
                break;
            }

        }
    }
}
