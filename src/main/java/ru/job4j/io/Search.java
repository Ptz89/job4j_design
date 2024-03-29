package ru.job4j.io;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Predicate;

public class Search {
    public static void main(String[] args) throws IOException {
        validateArgument(args);
        Path start = Paths.get(args[0]);
        search(start, p -> p.toFile().getName().endsWith(args[1])).forEach(System.out::println);
    }

    public static List<Path> search(Path root, Predicate<Path> condition) throws IOException {
        SearchFiles searcher = new SearchFiles(condition);
        Files.walkFileTree(root, searcher);
        return searcher.getPaths();
    }

    @SuppressWarnings("checkstyle:OperatorWrap")
    public static void validateArgument(String[] args) {
        if (args.length < 2) {
            throw new IllegalArgumentException("Программа должна принимать два параметра : начальная папка и "
                    + "расширение файлов, которые нужно искать");
        }

        File file = new File(args[0]);
        if (!file.exists()) {
            throw new IllegalArgumentException(String.format("Not exist %s", file.getAbsoluteFile()));
        }
        if (!file.isDirectory()) {
            throw new IllegalArgumentException(String.format("Not directory %s", file.getAbsoluteFile()));
        }
        if (args[1].matches("\\.+")) {
            throw new IllegalArgumentException(String.format("Неверно указан второй параметр", file.getAbsoluteFile()));

        }

    }
}