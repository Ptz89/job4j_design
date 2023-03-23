package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;

public class Config {

    private final String path;
    private final Map<String, String> values = new HashMap<String, String>();

    public Config(final String path) {
        this.path = path;
    }

    public void load() {
        try (BufferedReader in = new BufferedReader(
                new FileReader(this.path)
        )) {
            String file;
            while ((file = in.readLine()) != null) {
                if (!file.startsWith("#") && !file.isEmpty()) {
                    String[] lines = file.split("=");
                    if (lines.length == 1 || lines[0].isBlank() || lines[1].isBlank()) {
                        throw new IllegalArgumentException("File is not correct");
                    }
                    values.put(lines[0], lines[1]);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public String value(String key) {
        return values.get(key);
    }

    @Override
    public String toString() {
        StringJoiner out = new StringJoiner(System.lineSeparator());
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            read.lines().forEach(out::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return out.toString();
    }

    public static void main(String[] args) {
        System.out.println(new Config("data/app.properties"));
    }
}