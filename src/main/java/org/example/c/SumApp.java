package org.example.c;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SumApp {
    public SumApp(int size) {
        Boolean state = true;

        Random random = new Random();
        List<List<Integer>> lists = new ArrayList<>();
        for (int i = 0; i < 3; ++i) {
            List<Integer> list = new ArrayList<>(size);
            for (int j = 0; j < size; ++j) {
                list.add(random.nextInt(-100, 200));
            }
            lists.add(list);
        }

        Tracker tracker = new Tracker(state, lists);

        Barrier barrier = new Barrier(3, tracker);

        for (int i = 0; i < 3; ++i) {
            Worker worker = new Worker(lists.get(i), state, barrier);
            worker.start();
        }
    }
}
