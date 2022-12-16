package day07;

import java.lang.StackWalker.Option;
import java.security.SecureRandom;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class TerminalOps {
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
        joining(numList);
        reducing(numList);
        reducing2(numList);
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

    public static void joining(List<Integer> numList) {
        System.out.println("========Joining=======");

        String intList = numList.stream()
                .map(n -> String.valueOf(n) + String.valueOf(n))
                .collect(Collectors.joining("\n"));

        System.out.println(">>>> numList: " + intList);
    }

    public static void reducing(List<Integer> numList) {
        System.out.println("========reducing=======");

        Optional<Integer> opt = numList.stream()
                .map(n -> String.valueOf(n) + String.valueOf(n))
                .map(Integer::parseInt)
                .collect(Collectors.reducing((total, i) -> {
                    System.out.printf("Total: %d and i: %d\n", total, i);
                    return total + i; // reducing - reducing the list to a single value in this case is to get the
                                      // total hence `accumulator + i`
                }));
        // check if we have any answer
        if (opt.isPresent())
            System.out.println(">>>> total: " + opt.get());
        else
            System.out.println("reducing produces no result");
    }

    public static void joiningAsReducing() {

    }

    public static void reducing2(List<Integer> numList) {
        System.out.println("========reducing=======");

        Integer num = numList.stream()
                .map(n -> String.valueOf(n) + String.valueOf(n))
                .map(Integer::parseInt)
                .collect(Collectors.reducing(0, (total, i) -> {
                    System.out.printf("Total: %d and i: %d\n", total, i);
                    return total + i;
                }));
        System.out.printf("Total: %,d", num);
    }

}
