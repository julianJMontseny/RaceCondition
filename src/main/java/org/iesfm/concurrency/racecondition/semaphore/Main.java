package org.iesfm.concurrency.racecondition.semaphore;

import java.util.concurrent.Semaphore;

public class Main {

    public static void main(String[] args) {
        // 1. tiene dos permit libres
        Semaphore semaphore = new Semaphore(2);

        Thread holaThread = new Thread(new PrintMessageTask(semaphore, "Hola", 1000));
        Thread adiosThread = new Thread(new PrintMessageTask(semaphore, "Adios", 1000));
        // Si el semaforo tiene dos permit
        try {
            // 2. Se adquieren los dos permit, ya no hay permits libres
            holaThread.start();
            adiosThread.start();

            semaphore.acquire(2); // join
            System.out.println("FINALIZADO");
            semaphore.release(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
