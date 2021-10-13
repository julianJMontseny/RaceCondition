package org.iesfm.racecondition.deadlock;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        Accumulator incAcc = new Accumulator();
        Accumulator decAcc = new Accumulator();
        Thread incDec = new Thread(new IncDecTask(incAcc, decAcc, 10000));
        Thread decInc = new Thread(new DecIncTask(decAcc, incAcc, 10000));

        decInc.start();
        incDec.start();

        decInc.join();
        incDec.join();



    }
}
