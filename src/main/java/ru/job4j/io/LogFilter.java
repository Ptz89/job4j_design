package ru.job4j.io;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class LogFilter {
    public List<String> filter(String file) {
        List<String> strings = new ArrayList<>();
        try {
            BufferedReader in = new BufferedReader(new FileReader(file));
            for (String line = in.readLine(); line != null; line = in.readLine()) {
                String[] str = line.split(" ");
                if ("404".equals(str[str.length - 2])) {
                    strings.add(line);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return strings;
    }

    public static void save(List<String> log, String file) {
        try (PrintWriter out = new PrintWriter(new BufferedOutputStream(new FileOutputStream(file)))) {
            for (String str : log) {
                out.println(str);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        LogFilter logFilter = new LogFilter();
        List<String> log = logFilter.filter("data/log.txt");
        System.out.println(log);
        save(log, "data/404.txt");

    }
}