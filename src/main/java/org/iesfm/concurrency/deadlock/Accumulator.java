package org.iesfm.concurrency.deadlock;

/**
 * Esta clase es thread-safe
 */
public class Accumulator {
    private int value = 0;

    // Ahora el método inc solo se puede ejecutar por un proceso al mismo tiempo
    public void inc() {
        // Esta sentencia NO es atómica
        value = value + 1;
        // value = 0
        // 1 Calcula la suma
        // 2 asigna el resultado a value
        // H1   0 + 1 = 1 value=1
        // H2   0 + 1 = 1         value=1
        // value = 2
    }

    public void dec() {
        value = value - 1;

    }

    public int getValue() {
        return value;
    }
}
