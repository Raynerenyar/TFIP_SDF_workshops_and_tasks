package day06;

import java.security.SecureRandom;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) {

        // Random rand = new SecureRandom();
        // for (int i = 0; i < 10; i++) {
        // System.out.printf(">>> %d \n", rand.nextInt(100));
        // }
        ExecutorService threadPool = Executors.newFixedThreadPool(1);
        for (int i = 0; i < 3; i++) { // three additional threads. Main is 1 thread.
            RandomNumbers thr = new RandomNumbers("thr-%d".formatted(i), 100);
        }
        System.out.println("All done");
    }

}