package org.example.c;

import java.util.concurrent.atomic.AtomicInteger;

public class Barrier {
    private final Runnable action;
    private final AtomicInteger counter;
    private final int count;

    public Barrier(int count, Runnable action) {
        this.action = action;
        this.counter = new AtomicInteger(0);
        this.count = count;
    }

    synchronized void await() throws InterruptedException {
        counter.incrementAndGet();
        if (counter.get() >= count) {
            action.run();
            this.notifyAll();
        } else {
            this.wait();
        }
    }
}
