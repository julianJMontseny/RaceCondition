package org.iesfm.racecondition.increment;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedList;
import java.util.List;

public class Main {

    private static Logger log = LoggerFactory.getLogger(
            Main.class
    );

    public static void main(String[] args) {
        Accumulator acc = new Accumulator();
        List<Thread> threads = new LinkedList<>();
        for (int i = 0; i < 100; i++) {
            Thread t = new Thread(new IncrementTask(acc, 100000));
            t.start();
            threads.add(t);
        }

        for (Thread t : threads) {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        log.info("El resutlado es " + acc.getValue());

    }
}
