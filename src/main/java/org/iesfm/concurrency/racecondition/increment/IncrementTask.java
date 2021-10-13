package org.iesfm.concurrency.racecondition.increment;

import java.util.concurrent.Semaphore;

public class IncrementTask implements Runnable {
    private Semaphore semaphore;
    private Accumulator acc;
    private int times;

    public IncrementTask(Semaphore semaphore, Accumulator acc, int times) {
        this.semaphore = semaphore;
        this.acc = acc;
        this.times = times;
    }

    @Override
    public void run() {
        for (int i = 0; i < times; i++) {
            try {
                if (i % 2 == 0) {
                    semaphore.acquire();
                    acc.inc();
                    semaphore.release();
                } else {
                    semaphore.acquire();
                    acc.dec();
                    semaphore.release();
                }
            } catch (InterruptedException e) {

            }
        }
    }
}
