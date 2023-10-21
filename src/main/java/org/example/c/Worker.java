package org.example.c;

import java.util.List;
import java.util.Random;

public class Worker extends Thread {
    List<Integer> list;

    Boolean state;
    Barrier barrier;

    Random random = new Random();

    Worker(List<Integer> list, Boolean state, Barrier barrier) {
        this.list = list;
        this.state = state;
        this.barrier = barrier;
    }

    @Override
    public void run() {
        while (state) {
            try {
                int index = random.nextInt(0, list.size());
                int value = random.nextBoolean() ? 1 : -1;
                list.set(index, list.get(index) + value);
                barrier.await();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
