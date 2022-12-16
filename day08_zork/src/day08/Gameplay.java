package day08;

import java.io.IOException;
import java.util.Scanner;
import java.util.Map;

public class Gameplay {

    private static final String LOOK = "look";
    private static final String NORTH = "north";
    private static final String SOUTH = "south";
    private static final String EAST = "east";
    private static final String WEST = "west";
    private static final String EXIT = "exit";

    public static String prompt() {
        Scanner scn = new Scanner(System.in);
        System.out.printf("> ");
        String line = scn.next();
        scn.close();
        return line;
    }

    /**
     * Method prints out current location of character and the description of
     * location
     * 
     * @param currLocation
     * @param mapOfLocs
     */
    public static void readLocation(String currLocation, Map<String, Location> mapOfLocs) {
        System.out.printf("\nYou are now at:\n");
        System.out.println(mapOfLocs.get(currLocation).getNAME());
        for (String item : mapOfLocs.get(currLocation).getDESCRIPTION()) {
            System.out.println(item);
        }
    }

    /**
     * method check command against switch. if command is valid and is a direction,
     * proceeds to call updateLocation function, eventually returns true. If command
     * is "look" return true. Default returns true as well. "exit" will return false
     * which will end the game loop.
     * 
     * @param command      user's input
     * @param currLocation current location of character
     * @param mapOfLocs    Map<String,Location>
     * @return Boolean
     */
    public static Boolean verifyCommand(String command, String currLocation, Map<String, Location> mapOfLocs) {
        command = command.toLowerCase();
        switch (command) {
            case NORTH, SOUTH, EAST, WEST -> {
                if (updateLocation(command, currLocation, mapOfLocs)) {
                    readLocation(checkCurrLocation(mapOfLocs), mapOfLocs);
                    // returns true so that it continues loop
                    return true;
                } else {
                    try {
                        // clears terminal
                        new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
                    } catch (InterruptedException e) {
                    } catch (IOException e) {
                    }
                    System.out.printf("\n");
                    System.out.println("========================");
                    System.out.println("That move is unavailable");
                    System.out.println("    Please try again    ");
                    System.out.println("========================");
                }
            }
            case LOOK -> {
                readLocation(currLocation, mapOfLocs);
                return true; // continue while loop
            }
            case EXIT -> {
                return false; // breaks while loop
            }
            default -> {
                return true; // continue while loop
            }
        } // switch
        return true; // continue while loop
    }// verifyCommand()

    /**
     * updates location and returns true if the nexxt location is in the current
     * location's directory
     * 
     * @return Boolean
     */
    public static Boolean updateLocation(String command, String currLocation, Map<String, Location> mapOfLocs) {
        for (String direction : mapOfLocs.get(currLocation).getDIRECTIONS()) {
            String[] split = direction.split(" ");
            if (split[0].equalsIgnoreCase(command)) {
                // set next location to true
                Location loc = mapOfLocs.get(split[1].strip().toUpperCase());
                loc.setInRoom(true);
                mapOfLocs.put(split[1], loc);
                // set previous location to false
                loc = mapOfLocs.get(currLocation);
                loc.setInRoom(false);
                mapOfLocs.put(currLocation, loc);
                return true;
            }
        }
        return false;
    }

    /**
     * Method looks for Boolean in each location, returns if found true.
     * 
     * @param mapOfLocs Map<String,Location>
     * @return current location
     */
    public static String checkCurrLocation(Map<String, Location> mapOfLocs) {
        for (String location : mapOfLocs.keySet()) {
            if (mapOfLocs.get(location).getInRoom()) {
                return location;
            }
        }
        return null;
    }
}
