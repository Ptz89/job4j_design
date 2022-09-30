package ru.job4j.iterator;

import java.util.Iterator;

public class ArrayIt implements Iterator<Integer> {
    private final int[] date;
    private int point = 0;

    public ArrayIt(int[] date) {
        this.date = date;
    }

    @Override
    public boolean hasNext() {
        return point < date.length;
    }

    @Override
    public Integer next() {
        return date[point++];
    }
}
