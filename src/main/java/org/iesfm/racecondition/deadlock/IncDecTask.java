package org.iesfm.racecondition.deadlock;

public class IncDecTask implements Runnable {

    private Accumulator incAcc;

    private Accumulator decAcc;

    private int times;

    public IncDecTask(Accumulator incAcc, Accumulator decAcc, int times) {
        this.incAcc = incAcc;
        this.decAcc = decAcc;
        this.times = times;
    }

    @Override
    public void run() {
        for (int i = 0; i < times; i++) {
            // 2. H2 tiene que esperar a que H1 libere incAcc
            // 6. H2 cierra incAcc
            synchronized (incAcc) {
                System.out.println("IncDecTask adquiere incAcc");
                synchronized (decAcc) {
                    System.out.println("IncDecTask adquiere decAcc");


                    // 7. H2 cierra decAcc

                    // Ejecuta la sescción crítica
                    incAcc.inc();
                    decAcc.dec();
                }
                System.out.println("IncDecTask libera incAcc");

            }
            System.out.println("IncDecTask libera decAcc");

        }
    }
}
