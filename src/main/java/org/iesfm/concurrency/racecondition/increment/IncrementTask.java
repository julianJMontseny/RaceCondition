package org.iesfm.concurrency.racecondition.increment;

public class IncrementTask implements Runnable {
    private Accumulator acc;
    private int times;

    public IncrementTask(Accumulator acc, int times) {
        this.acc = acc;
        this.times = times;
    }

    @Override
    public void run() {
        for (int i = 0; i < times; i++) {
            acc.inc();
        }
    }
}
