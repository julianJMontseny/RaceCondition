package org.iesfm.concurrency.deadlock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DecrementAndIncrementTask implements Runnable {
    private final static Logger log = LoggerFactory.getLogger(DecrementAndIncrementTask.class);
    private Accumulator incAcc;
    private Accumulator decAcc;
    private int times;

    public DecrementAndIncrementTask(Accumulator incAcc, Accumulator decAcc, int times) {
        this.incAcc = incAcc;
        this.decAcc = decAcc;
        this.times = times;
    }

    @Override
    public void run() {
        for (int i = 0; i < times; i++) {

            synchronized (decAcc) {
                log.info("DecInc bloqueando decacc");
                synchronized (incAcc) {
                    log.info("DecInc bloqueando incacc");

                    decAcc.dec();
                    incAcc.inc();
                }
                log.info("DecInc liberando incacc");
            }
            log.info("DecInc liberando decacc");

        }
    }
}
