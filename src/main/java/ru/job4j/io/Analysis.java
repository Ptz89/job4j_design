package ru.job4j.io;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

public class Analysis {
    private List<String> list = new ArrayList<>();

    public void unavailable(String source, String target) {
        try (BufferedReader in =
                     new BufferedReader(new FileReader(source));
        PrintWriter out =
                new PrintWriter(new FileOutputStream(target))) {
            String line;
            while ((line = in.readLine()) != null) {

                if ((list.size() == 0 && (line.startsWith("400") || line.startsWith("500")))
                || (list.size() != 0 && (line.startsWith("200") || line.startsWith("300")))) {
                    String[] lines = line.split(" ");
                    list.add(lines[1]);
                }

                if (list.size() == 2) {
                    StringBuilder str = new StringBuilder();
                    list.forEach(s -> str.append(s).append(";"));
                    out.println(str);
                    list.clear();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Analysis analysis = new Analysis();
        analysis.unavailable("data/server.log", "data/target.csv");
    }
}