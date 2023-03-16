package ru.job4j.io;

import java.io.FileOutputStream;
import java.io.IOException;


public class ResultFile {
    public static void main(String[] args) {
        try (final FileOutputStream out = new FileOutputStream("data/multiplication table.txt")) {
            for (int i = 1; i < 11; i++) {
                for (int j = 1; j < 11; j++) {
                    String str = Integer.toString(i * j);
                    out.write(str.getBytes());
                    out.write(" ".getBytes());
                }
                out.write(System.lineSeparator().getBytes());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}