package myapp;

import java.io.Console;

public class Calculate {
    public static void main(String[] args) {
        Console cons = System.console();

        Integer sumNum = 0;
        Integer count = 0;

        while (true) {

            String numString = "";
            Integer num = 0;
            try {
                // Get user input number
                numString = cons.readLine("Enter a number ").trim();
                numString = numString.trim();

                // stops receiving user input
                if (numString.equalsIgnoreCase("Stop")) {
                    break;

                } else {
                    // Typecast string to integer
                    num = Integer.parseInt(numString);
                }

                // if user input a non-integer, continue
            } catch (Exception e) {
                continue;
            }
            sumNum += num;
            count++;
        }
        System.out.printf("The total of %d numbers is %d", count, sumNum);
    }
}
