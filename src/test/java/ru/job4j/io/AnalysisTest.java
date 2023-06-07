package ru.job4j.io;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.*;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

class AnalysisTest {

    @Test
    void checkCode() {
        String source = "data/server.log";
        String target = "data/target.csv";
        Analysis analysis = new Analysis();
        analysis.unavailable(source, target);
        List<String> expected = List.of("10:57:01;10:59:01;", "11:01:02;11:02:02;");
        List<String> rsl = new ArrayList<>();
        try (BufferedReader in = new BufferedReader(new FileReader(target))) {
            in.lines().forEach(rsl::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        assertThat(rsl).isEqualTo(expected);
    }

    @Test
    void drop(@TempDir Path tempDir) throws IOException {
        Analysis analysis = new Analysis();
        File source = tempDir.resolve("serv.log").toFile();
        try (PrintWriter out = new PrintWriter(source)) {
            out.println("200 10:56:01");
            out.println("500 10:57:01");
            out.println("400 10:58:01");
            out.println("300 10:59:01");
            out.println("500 11:01:02");
            out.println("200 11:02:02");
        }
        File target = tempDir.resolve("tgt.csv").toFile();
        analysis.unavailable(source.getAbsolutePath(), target.getAbsolutePath());
        List<String> expected = List.of("10:57:01;10:59:01;", "11:01:02;11:02:02;");
        List<String> rsl = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(target))) {
            reader.lines().forEach(rsl::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        assertThat(rsl).isEqualTo(expected);
    }
}
