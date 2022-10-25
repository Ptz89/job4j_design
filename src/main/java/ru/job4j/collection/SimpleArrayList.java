package ru.job4j.collection;

import java.util.*;

public class SimpleArrayList<T> implements SimpleList<T> {
    private T[] container;

    private int size;

    private int modCount;

    public SimpleArrayList(int capacity) {
        this.container = (T[]) new Object[capacity];
    }

    @Override
    public void add(T value) {
        if (size == 0) {
            container = (T[]) new Object[1];
        } else if (size == container.length) {
            container = Arrays.copyOf(container, container.length * 2);
        }
        container[size] = value;
        modCount++;
        size++;
    }

    @Override
    public T set(int index, T newValue) {
        T oldContainer = get(index);
        container[index] = newValue;
        return oldContainer;
    }

    @Override
    public T remove(int index) {
        T oldContainer = get(index);
        System.arraycopy(
                container,
                index + 1,
                container,
                index,
                modCount - index - 1
        );
        container[size - 1] = null;
        modCount++;
        size--;
        return oldContainer;
    }

    @Override
    public T get(int index) {
        Objects.checkIndex(index, size);
        return container[index];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private int expectedModCount = modCount;
            private int index = 0;

            @Override
            public boolean hasNext() {
                return index < size;
            }

            @Override
            public T next() {
                if (modCount != expectedModCount) {
                    throw new ConcurrentModificationException("Во время обхода коллекции она была изменена");
                }
                if (!hasNext()) {
                    throw new NoSuchElementException("Элемента не существует");
                }

                return container[index++];
            }

        };
    }
}
