package myapp.ver1;

import java.io.Console; // importing a FQCN so that compiler knows where to find this class

public class Hello {
    public static void main(String[] args) {
        // Get system console
        Console cons = System.console();

        String name = "";

        while (name.trim().length() == 0) {
            // Read from the console, the result is assign to a variable
            name = cons.readLine("What is your name? ");
        }

        name = name.trim(); // removes whitespaces at the front and back of the string

        if (name.equals("Fred")) {
            System.out.println("Yababadabadoo");

        } else if (name.equals("Barney")) {
            System.out.println("Hello Barney");

        } else if (name.length() == 0) {
            System.err.println("Please enter your name");

        } else {
            // Send a greeting to the name with a formatted string
            System.out.printf("Hello %s.\nPlease to meet you!", name.toUpperCase());
        }

    }
}
