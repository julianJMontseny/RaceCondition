package org.iesfm.racecondition.deadlock;

public class DecIncTask implements Runnable {


    private Accumulator decAcc;

    private Accumulator incAcc;

    private int times;

    public DecIncTask(Accumulator decAcc, Accumulator incAcc, int times) {
        this.decAcc = decAcc;
        this.incAcc = incAcc;
        this.times = times;
    }

    @Override
    public void run() {
        for (int i = 0; i < times; i++) {
            // 1. H1 cierra incAcc
            synchronized (decAcc) {
                System.out.println("DecIncTask adquiere decAcc");
                synchronized (incAcc) {
                    System.out.println("DecIncTask adquiere incAcc");

                    // 3. H1 cierra decAcc


                    // Se ejecuta la sección crítica
                    decAcc.dec();
                    incAcc.inc();
                }
                System.out.println("DecIncTask libera decAcc");

                //4 .H1 libera decAcc
            }
            System.out.println("DecIncTask libera incAcc");

            // 5. H1 libera incAcc
        }
    }
}
