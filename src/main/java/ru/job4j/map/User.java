package ru.job4j.map;

import java.util.*;

public class User {
    private String name;
    private int children;
    private Calendar birthday;

    public User(String name, int children, Calendar birthday) {
        this.name = name;
        this.children = children;
        this.birthday = birthday;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, children, birthday);
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    public static void main(String[] args) {
        Map<User, Object> map = new HashMap<>();
        Calendar calendar = new GregorianCalendar(2001, 01, 01);
        User user1 = new User("Dima", 4, calendar);
        User user2 = new User("Dima", 4, calendar);
        map.put(user1, new Object());
        map.put(user2, new Object());
        map.forEach((k, v) -> System.out.println(k + " " + v));
    }
}
