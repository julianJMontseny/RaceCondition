package org.iesfm.concurrency.deadlock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {
    private final static Logger log = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) throws InterruptedException {
        Accumulator incAcc = new Accumulator();
        Accumulator decAcc = new Accumulator();
        Thread incDecT = new Thread(new IncrementAndDecrementTask(incAcc, decAcc, 100000));
        Thread decIncT = new Thread(new DecrementAndIncrementTask(incAcc, decAcc, 100000));
        incDecT.start();
        decIncT.start();

        incDecT.join();
        decIncT.join();

        log.info("Increased " + incAcc.getValue() + " times");
        log.info("Decreased " + decAcc.getValue() + " times");

    }
}
