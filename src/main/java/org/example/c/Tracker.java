package org.example.c;

import org.example.ExecutionState;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Tracker implements Runnable {
    ExecutionState state;
    List<List<Integer>> lists;

    Tracker(ExecutionState state, List<List<Integer>> lists) {
        this.state = state;
        this.lists = lists;
    }

    @Override
    public void run() {
        System.out.print("Sums: ");

        Set<Integer> sums = new HashSet<>();
        for (int i = 0; i < lists.size(); ++i) {
            int sum = 0;
            for (int j : lists.get(i)) {
                sum += j;
            }
            sums.add(sum);

            System.out.print(sum + " ");
        }

        System.out.println();

        if (sums.size() == 1) {
            state.setState(false);
        }

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
