package org.iesfm.concurrency.racecondition.lista;

import java.util.List;

public class AddToListTask implements Runnable {
    private List<Integer> lista;
    private int n;

    public AddToListTask(List<Integer> lista, int n) {
        this.lista = lista;
        this.n = n;
    }

    @Override
    public void run() {

        // H1  syncrhonized(lista), adquiere(lista)  for { add ... add }, libera(lista)
        // H2      syncrhonized(lista) _________________________________________________ adquiere(lista)
        for (int i = 0; i < n; i++) {
            lista.add(i);
        }
    }
}
