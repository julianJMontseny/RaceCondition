package org.iesfm.concurrency.racecondition.semaphore;

import java.util.concurrent.Semaphore;

public class PrintMessageTask implements Runnable {
    private Semaphore semaphore;

    private String message;

    private int times;

    public PrintMessageTask(Semaphore semaphore, String message, int times) {
        this.semaphore = semaphore;
        this.message = message;
        this.times = times;
    }

    @Override
    public void run() {
        try {
            // 3. Como no hay ningún permit libre se bloquea h1
            // 4. Como no hay ningún permit libre se bloquea h2
            // 6. h1 adquiere un permit y continua, semaforo =  1 permit
            // 6. h2 adquiere un permit y continua, semaforo =  0 permit
            semaphore.acquire();

            for (int i = 0; i < times; i++) {
                System.out.println(message);
            }
            semaphore.release();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
