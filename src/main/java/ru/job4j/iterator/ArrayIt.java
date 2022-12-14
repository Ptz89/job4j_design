package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

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
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return date[point++];
    }
}
