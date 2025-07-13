package com.airship.example.java11;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class MainTest {

    @Test
    public void testGetLines() throws Exception {
        List<String> lines = Main.getLines("Airship helps \n \n developers \n explore Java.");
        assertThat(lines).containsExactly("Airship helps", "developers", "explore Java.");
    }

}
