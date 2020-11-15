package ru.geekbrains.githubclient.mvp.model;

import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Model {
    private List<Integer> counters = new ArrayList<>(Arrays.asList(0, 0, 0));

    public int getCurrent(int index) {
        // TODO: входной контроль параметров
        return counters.get(index);
    }

    public void set(int index, int value) {
        counters.set(index, value);
    }
}




