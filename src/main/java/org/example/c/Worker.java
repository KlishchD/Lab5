package org.example.c;

import org.example.ExecutionState;
import java.util.List;
import java.util.Random;

public class Worker extends Thread {
    List<Integer> list;

    ExecutionState state;
    Barrier barrier;

    Random random;

    Worker(List<Integer> list, ExecutionState state, Barrier barrier, Random random) {
        this.list = list;
        this.state = state;
        this.barrier = barrier;
        this.random = random;
    }

    @Override
    public void run() {
        while (state.getState()) {
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
