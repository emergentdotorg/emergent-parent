package com.airship.example.java8;

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
        return Arrays.stream(multilineString.split("\n"))
            .map(String::trim)
            .filter(s -> !s.isEmpty())
            .collect(Collectors.toList());
    }

}
