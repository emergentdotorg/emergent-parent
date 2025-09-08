package com.airship.example.java8;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    @Test
    void main() {
        List<String> lines = Main.getLines("Airship helps \n \n developers \n explore Java.");
        assertThat(lines).containsExactly("Airship helps", "developers", "explore Java.");
    }
}
