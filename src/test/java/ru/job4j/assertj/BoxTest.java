package ru.job4j.assertj;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class BoxTest {
    @Test
    void isThisSphere() {
        Box box = new Box(0, 10);
        String name = box.whatsThis();
        assertThat(name).isEqualTo("Sphere")
                        .isNotEmpty();
    }

    @Test
    void isThisTetrahedron() {
        Box box = new Box(4, 10);
        String name = box.whatsThis();
        assertThat(name).isEqualTo("Tetrahedron")
                        .isNotEmpty();
    }

    @Test
    void checkNumberPositive() {
        Box box = new Box(4, 8);
        int vertex = box.getNumberOfVertices();
        assertThat(vertex).isEqualTo(4)
                .isPositive();
    }

    @Test
    void checkNumberNegative() {
        Box box = new Box(4, -8);
        int vertex = box.getNumberOfVertices();
        assertThat(vertex).isEqualTo(-1)
                .isNegative();
    }
    @Test
    void checkFormYes() {
        Box box = new Box(4, 8);
        boolean form = box.isExist();
        assertThat(form).isTrue();
    }

    @Test
    void checkFormNo() {
        Box box = new Box(4, -8);
        boolean form = box.isExist();
        assertThat(form).isFalse();
    }

    @Test
    void checkAreaSphere() {
        Box box = new Box(0, 4);
        double area = box.getArea();
        assertThat(area).isGreaterThan(201)
                        .isLessThan(202);
    }

    @Test
    void checkAreaCube() {
        Box box = new Box(8, 2);
        double area = box.getArea();
        assertThat(area).isEqualTo(24)
                        .isPositive();
    }
}
