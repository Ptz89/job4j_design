package ru.job4j.iterator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ListUtilsTest {

    private List<Integer> input1;
    private List<Integer> input2;

    @BeforeEach
    void setUp() {
        input1 = new ArrayList<>(Arrays.asList(1, 3));
        input2 = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8));
    }

    @Test
    void whenAddBefore() {
        ListUtils.addBefore(input1, 1, 2);
        assertThat(input1).hasSize(3).containsSequence(1, 2, 3);
    }

    @Test
    void whenAddBeforeWithInvalidIndex() {
        assertThatThrownBy(() -> ListUtils.addBefore(input1, 3, 2))
                .isInstanceOf(IndexOutOfBoundsException.class);
    }

    @Test
    void whenAddAfter() {
        ListUtils.addAfter(input1, 0, 2);
        assertThat(input1).hasSize(3).containsSequence(1, 2, 3);
    }

    @Test
    void whenAddAfterWithInvalidIndex() {
        assertThatThrownBy(() -> ListUtils.addAfter(input1, 3, 2))
                .isInstanceOf(IndexOutOfBoundsException.class);
    }

    @Test
    void whenRemoveIf() {
        ListUtils.removeIf(input2, x -> x % 2 == 0);
        Assertions.assertEquals(input2, Arrays.asList(1, 3, 5, 7));
    }

    @Test
    void whenReplaceIf() {
        ListUtils.replaceIf(input2, x -> x % 2 == 0, 0);
        Assertions.assertEquals(input2, Arrays.asList(1, 0, 3, 0, 5, 0, 7, 0));
    }

    @Test
    void whenRemoveAll() {
        List<Integer> el = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7));
        ListUtils.removeAll(input2, el);
        Assertions.assertEquals(input2, Arrays.asList(8));
    }
}