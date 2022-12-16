package day07;

import java.util.Random;
import java.security.SecureRandom;
import java.util.List;
import java.util.LinkedList;

public class IntList {
    public static void main(String[] args) {
        // randomly generate a list of numbers
        Integer max = 100;
        Integer range = 100;
        Random rnd = new SecureRandom();

        List<Integer> numList = new LinkedList<>();

        for (int i = 0; i < max; i++) {
            numList.add(rnd.nextInt(range));
        }

        System.out.println(">>> numList: " + numList);

        // filter(numList);
        makeToString(numList);
    }

    public static void filter(List<Integer> numList) {
        System.out.println("FILTER");
        // filter
        List<Integer> resultList = new LinkedList<>();
        for (Integer num : numList) {
            if ((num % 3 != 0)) {
                continue;
            }
            resultList.add(num);
        }
        System.out.println(">>> iteration resultList " + resultList);
    }

    public static void makeToString(List<Integer> numList) {
        System.out.println(">>> original numList " + numList);

        List<Integer> resList = numList.stream()
                // map: String apply (Integer x)
                .map(x -> String.valueOf(x) + String.valueOf(x))
                // map: Integer apply (String x)
                .map(Integer::parseInt) // method reference
                .toList();

        System.out.println(">>> stream resultList " + resList);
    }

}
