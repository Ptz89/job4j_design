package ru.job4j.collection;

import java.util.*;

public class SimpleLinkedList<E> implements LinkedList<E> {
        private int size;
        private int modCount;
        private Node<E> first;
        private Node<E> last;

        @Override
        public void add(E value) {
                final Node<E> l = last;
                final Node<E> newNode = new Node<>(value);
                last = newNode;
                if (l == null) {
                        first = newNode;
                } else {
                        l.next = newNode;
                }
                size++;
                modCount++;
        }

        @Override
        public E get(int index) {
                Objects.checkIndex(index, size);
                int currentIndex = 0;
                Node<E> rsl = first;
                while (currentIndex != index) {
                        rsl = rsl.next;
                        currentIndex++;
                }
                return rsl.item;
        }

        @Override
        public Iterator<E> iterator() {
                return new Iterator<E>() {
                        private int expectedModCount = modCount;
                        private Node<E> value = first;

                        @Override
                        public boolean hasNext() {
                                if (modCount != expectedModCount) {
                                        throw new ConcurrentModificationException();
                                }
                                return value != null;
                        }

                        @Override
                        public E next() {
                                if (!hasNext()) {
                                        throw new NoSuchElementException();
                                }
                                E rsl = value.item;
                                value = value.next;
                                return rsl;
                        }
                };
        }

        private static class Node<E> {
                private E item;
                private Node<E> next;

                public Node(E item) {
                        this.item = item;
                }
        }
}