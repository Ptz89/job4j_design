package ru.job4j.io;

import java.util.HashMap;
import java.util.Map;

public class ArgsName {

    private final Map<String, String> values = new HashMap<>();

    public String get(String key) {
        String value = values.get(key);
        if (value == null) {
            throw new IllegalArgumentException(String.format("This key: '%s' is missing", key));
        }
        return value;
    }

    private void parse(String[] args) {
        for (String arg : args) {
            if (arg.charAt(0) != '-') {
                throw new IllegalArgumentException(String.format(
                        "Error: This argument '%s' does not start with a '-' character", arg));
            }
            if (!arg.contains("=")) {
                throw new IllegalArgumentException(String.format(
                        "Error: This argument '%s' does not contain an equal sign", arg));
            }
            String[] str = arg.split("=", 2);
            if (str[0].substring(1).length() == 0) {
                throw new IllegalArgumentException(String.format(
                        "Error: This argument '%s' does not contain a key", arg));
            }
            if (str[1].length() == 0) {
                throw new IllegalArgumentException(String.format(
                        "Error: This argument '%s' does not contain a value", arg));
            }
            values.put(str[0].substring(1), str[1]);
        }
    }

    public static ArgsName of(String[] args) {
        ArgsName names = new ArgsName();
        if (args.length == 0) {
            throw new IllegalArgumentException(String.format("Arguments not passed to program"));
        }
        names.parse(args);
        return names;
    }

    public static void main(String[] args) {
        ArgsName jvm = ArgsName.of(new String[] {"-Xmx=512", "-encoding=UTF-8"});
        System.out.println(jvm.get("Xmx"));

        ArgsName zip = ArgsName.of(new String[] {"-out=project.zip", "-encoding=UTF-8"});
        System.out.println(zip.get("out"));
    }
}