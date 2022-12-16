package myapp;

import java.io.Console;
import java.util.LinkedList;
import java.util.List;

public class TodoList {
    public static void main(String[] args) {
        // create a list of numbers
        List<Integer> listOfInt = new LinkedList<>();

        // get the console
        Console cons = System.console();

        String item = "";

        while (true) {
            item = cons.readLine("Please enter a number: ");
            item.trim();

            if (item.equalsIgnoreCase("stop")) {
                break;
            }

            // add the item to the list
            listOfInt.add(Integer.parseInt(item));
        }

        System.out.printf("Number of elements in the list: %d\n", listOfInt.size());

        for (Integer i = 0; i < listOfInt.size(); i++) {
            System.out.printf("[%d]: number: %d\n", i, listOfInt.get(i));
        }
    }
}
