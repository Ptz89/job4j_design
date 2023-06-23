package ru.job4j.io.duplicates;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {
    private final Map<FileProperty, Set<String>> duplicate = new HashMap<>();

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        File f = file.toFile();
        FileProperty fileProperty = new FileProperty(f.length(), f.getName());
        duplicate.putIfAbsent(fileProperty, new HashSet<>());
        duplicate.get(fileProperty)
                        .add(f.getAbsolutePath());
        return FileVisitResult.CONTINUE;
    }

    public void printDuplicates() {
        duplicate.values().stream()
                .filter(s -> s.size() > 1)
                .flatMap(Collection::stream)
                .forEach(System.out::println);
    }
}