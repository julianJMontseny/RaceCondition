package org.iesfm.concurrency.racecondition.lista;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Main {
    private static Logger log = LoggerFactory.getLogger(
            org.iesfm.concurrency.racecondition.increment.Main.class
    );

    public static void main(String[] args) {
        // Ahora la lista es sincronizada, es decir, es thread safe
        // La ventaja es que es thread safe
        // La desventaje es que es más lenta
        List<Integer> lista = Collections.synchronizedList(new ArrayList<>());
        List<Thread> threads = new LinkedList<>();
        for (int i = 0; i < 100; i++) {
            Thread t = new Thread(new AddToListTask(lista, 100000));
            t.start();
            threads.add(t);
        }

        for (Thread t : threads) {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        log.info("Hay " + lista.size() + " elementos en la lista");
    }
}
