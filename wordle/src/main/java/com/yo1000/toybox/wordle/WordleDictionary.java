package com.yo1000.toybox.wordle;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;
import java.util.stream.Collectors;

public class WordleDictionary extends ArrayList<String> {
    public WordleDictionary(String resourcePath) {
        this(WordleDictionary.load(resourcePath));
    }

    protected WordleDictionary(Collection<? extends String> c) {
        super(c);
    }

    public static WordleDictionary load(String resourcePath) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(
                Objects.requireNonNull(Main.class.getResourceAsStream(resourcePath))))) {
            return new WordleDictionary(reader.lines().collect(Collectors.toSet()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
