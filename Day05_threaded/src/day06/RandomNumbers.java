package day06;

import java.security.SecureRandom;
import java.util.Random;

public class RandomNumbers implements Runnable {

    // thread dies after run is finished
    @Override
    public void run() {
        // the body of the thread
        Random rand = new SecureRandom();
        for (int i = 0; i < 10; i++) {
            System.out.printf(">>> %d \n", rand.nextInt(100));
        }
    }

}
