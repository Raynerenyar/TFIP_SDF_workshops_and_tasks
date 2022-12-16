import java.util.ArrayList;

public class App {
    public static void main(String[] args) {
        System.out.println("Array list demo");

        // create arraylist object
        ArrayList<String> myList = new ArrayList<String>();

        // add items to it
        myList.add("apple");
        myList.add("orange");

        // loop over and print the items
        for (String item : myList) {
            System.out.println(item);
        }

        // remove an element in list
        myList.remove("apple");

        // loop over and print the elements in ArrayList
        for (String item : myList) {
            System.out.println(item);
        }
    }
}
