package ru.job4j.io;

import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
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


}
