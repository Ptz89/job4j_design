package ru.job4j.question;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class Analize {

    public static Info diff(Set<User> previous, Set<User> current) {
        Info rsl = new Info();

        Map<Integer, String> currentMap = current.stream().collect(Collectors.toMap(User::getId, User::getName));
        for (User prevUser : previous) {
            String currUser = currentMap.get(prevUser.getId());
            if (currUser == null) {
                rsl.increaseDeleted();
            } else if (!currUser.equals(prevUser.getName())) {
                rsl.increaseChanged();
            }
        }
        rsl.setAdded(currentMap.size() - previous.size() + rsl.getDeleted());
        return rsl;
    }
}