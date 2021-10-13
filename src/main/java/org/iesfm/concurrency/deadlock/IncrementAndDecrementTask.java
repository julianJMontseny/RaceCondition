package org.iesfm.concurrency.deadlock;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class IncrementAndDecrementTask implements Runnable {
    private final static Logger log = LoggerFactory.getLogger(IncrementAndDecrementTask.class);

    private Accumulator incAcc;
    private Accumulator decAcc;
    private int times;

    public IncrementAndDecrementTask(Accumulator incAcc, Accumulator decAcc, int times) {
        this.incAcc = incAcc;
        this.decAcc = decAcc;
        this.times = times;
    }

    @Override
    public void run() {
        for (int i = 0; i < times; i++) {
            synchronized (incAcc) {
                log.info("IncDec bloqueando incacc");
                synchronized (decAcc) {
                    log.info("IncDec bloqueando decacc");
                    decAcc.dec();
                    incAcc.inc();
                }
                log.info("IncDec liberando decacc");
            }
            log.info("IncDec liberando incacc");
        }
    }
}
