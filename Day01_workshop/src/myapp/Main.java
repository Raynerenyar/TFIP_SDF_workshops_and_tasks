package myapp;

import java.io.Console;
import java.util.LinkedList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        // init empty list
        List<String> shoppingCart = new LinkedList<>();

        Console cons = System.console();

        System.out.println("Welcome to your shopping cart.");

        Boolean state = true;
        while (state) {

            // get user command
            String input = cons.readLine("> ").trim().toLowerCase();
            // add user commands into list
            String[] splitInput = input.split("[,\s]+");

            if (splitInput.length < 1) {
                // no user input, continue while loop to prompt again
                continue;
            } else {

                switch (splitInput[0]) {
                    case ("list") -> {

                        // if cart is empty
                        if (shoppingCart.size() < 1) {
                            System.out.println("Your cart is empty.");

                        } else {
                            for (String fruit : shoppingCart)
                                System.out.printf("%d. %s\n", shoppingCart.indexOf(fruit) + 1, fruit);
                        }
                    }
                    case ("add") -> {
                        if (splitInput.length <= 1) {
                            System.out.println("What do you want to add?");
                        } else {
                            System.out.printf("Added ");
                            // if add more than 1 item
                            for (int i = 1; i < splitInput.length; i++) {
                                shoppingCart.add(splitInput[i]);
                                // add into shoppingCart
                                if (i == splitInput.length - 1) {
                                    System.out.printf("%s", splitInput[i]);
                                } else {
                                    System.out.printf("%s, ", splitInput[i]);
                                }
                            }
                            System.out.printf(" to your shopping cart.\n");
                        }
                    }
                    case ("delete") -> {
                        if (shoppingCart.size() < 1) {
                            System.out.println("Your cart is empty.");
                        } else {
                            if (splitInput.length <= 1) {
                                System.out.println("delete ITEM_NUMBER");
                            } else {
                                try {
                                    // typecast string to int of the options to delete
                                    int index = Integer.parseInt(splitInput[1]);

                                    // delete using index and print
                                    System.out.printf("%s removed from your cart.\n", shoppingCart.remove(index - 1));
                                } catch (Exception e) {
                                    System.out.println("delete ITEM_NUMBER");
                                }
                            }
                        }
                    }
                    case "exit" -> {
                        state = false; // break statement here does not end loop
                    }
                    default -> {
                    }
                }
            }
        }
    }
}
