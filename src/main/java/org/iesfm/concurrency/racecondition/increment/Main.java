package org.iesfm.concurrency.racecondition.increment;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Semaphore;

public class Main {

    private static Logger log = LoggerFactory.getLogger(
            Main.class
    );

    public static void main(String[] args) {
        Accumulator acc = new Accumulator();
        Semaphore semaphore = new Semaphore(1);
        List<Thread> threads = new LinkedList<>();
        for (int i = 0; i < 100; i++) {
            Thread t = new Thread(new IncrementTask(semaphore, acc, 100000));
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
