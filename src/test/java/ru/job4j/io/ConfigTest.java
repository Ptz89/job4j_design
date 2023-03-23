package ru.job4j.io;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

class ConfigTest {

    @Test
    void whenPairWithComment() {
        String path = "data/app.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("hibernate.connection.username")).isEqualTo("postgres");
    }

    @Test
    void whenPairWithEmptyLines() {
        String path = "data/file_with_empty_lines.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("surname")).isEqualTo("Ivanov");
    }

    @Test
    void whenPairWithTemplateViolation() {
        String path = "data/file_with_template_violation.properties";
        Config config = new Config(path);
        boolean error = false;
        try {
            config.load();
        } catch (IllegalArgumentException e) {
            error = true;
        }
        assertThat(error).isTrue();
    }
}