
import java.util.ArrayList;
import java.util.HashMap;

public class HashMapExample {
    public static void main(String[] args) {
        HashMap<String, Integer> myMap = new HashMap<String, Integer>();
        myMap.put("Bala", 30);
        myMap.put("Ken", 40);

        String key = "Bala";
        System.out.printf("Value for Bala key is %d\n", myMap.get(key));

        // change value of "Bala" key
        myMap.put("Bala", 10);
        System.out.printf("Value for Bala key is %d\n", myMap.get(key));

        // check if key exist
        System.out.printf("check if Fred exits: %b\n", myMap.containsKey("Fred"));

        // init array
        ArrayList<String> arr1 = new ArrayList<String>();
        arr1.add("hi");
        ArrayList<String> arr2 = new ArrayList<String>();
        arr2.add("bye");

        // init hashmap
        HashMap<String, ArrayList<String>> anotherMap = new HashMap<String, ArrayList<String>>();
        // add key, value into hashmap
        anotherMap.put("Bala", arr1);
        anotherMap.put("Fred", arr2);

        System.out.println(anotherMap.get("Bala"));
    }
}