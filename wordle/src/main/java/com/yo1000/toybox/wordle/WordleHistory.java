package com.yo1000.toybox.wordle;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class WordleHistory {
    private final Set<Character> history = new HashSet<>();

    public WordleHistory mark(String s) {
        s.chars().forEach(i -> mark((char) i));
        return this;
    }

    public WordleHistory mark(char c) {
        history.add(c);
        return this;
    }

    @Override
    public String toString() {
        return IntStream.range('a', 'z' + 1)
                .mapToObj(i -> (char) i)
                .map(c -> (history.contains(c)
                        ? "\u001B[47m\u001B[30m " + c + " \u001B[0m"
                        : " " + c + " "))
                .collect(Collectors.joining());
    }
}
