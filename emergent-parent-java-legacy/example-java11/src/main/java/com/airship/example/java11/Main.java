package com.airship.example.java11;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        String withNewlines = "Hello,\n World!" + Arrays.stream(args).collect(Collectors.joining("\n", " ", ""));
        List<String> lines = getLines(withNewlines);
        String sansNewlines = String.join(" ", lines);
        System.out.println(sansNewlines);
    }

    public static List<String> getLines(String multilineString) {
        return multilineString.lines()
            .filter(line -> !line.isBlank())
            .map(String::strip)
            .collect(Collectors.toList());
    }
}
