package com.yo1000.toybox.shiritori;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.security.SecureRandom;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ShiritoriDictionary extends HashMap<Character, List<ShiritoriWord>> {
    private static final String DEFAULT_DICTIONARY_FILE = "shiri-dict.txt";
    private static final SecureRandom r = new SecureRandom();

    private final Path dictionaryPath;
    private final List<ShiritoriWord> usedWords = new ArrayList<>();

    public ShiritoriDictionary() {
        this(DEFAULT_DICTIONARY_FILE);
    }

    public ShiritoriDictionary(String dictionaryFileName) {
        this(Paths.get(dictionaryFileName));
    }

    public ShiritoriDictionary(Path dictionaryPath) {
        this.dictionaryPath = dictionaryPath;

        if (Files.notExists(dictionaryPath)) {
            return;
        }

        try (Stream<String> lines = Files.lines(dictionaryPath, StandardCharsets.UTF_8)) {
            putAll(lines
                    .filter(s -> !s.isEmpty())
                    .filter(s -> s.length() >= 2)
                    .sorted()
                    .distinct()
                    .map(ShiritoriWord::new)
                    .collect(Collectors.groupingBy(ShiritoriWord::getHead))
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Path getDictionaryPath() {
        return dictionaryPath;
    }

    public void check(String word) throws UsedWordException, EndWordException {
        check(new ShiritoriWord(word));
    }

    public void check(ShiritoriWord word) throws UsedWordException, EndWordException {
        if (usedWords.contains(word)) {
            throw new UsedWordException(word);
        }

        if (word.isDeadEnd()) {
            throw new EndWordException(word);
        }

        usedWords.add(word);
    }

    public ShiritoriWord randomByUnused(Character initialCharacter) throws UsedWordException, NonVocabularyException {
        return Optional.ofNullable(get(initialCharacter))
                .map(list -> list.stream()
                        .filter(s -> !usedWords.contains(s))
                        .toList()
                )
                .filter(list -> !list.isEmpty())
                .map(list -> list.get(r.nextInt(list.size())))
                .orElseThrow(NonVocabularyException::new);
    }

    public void save() {
        try {
            Files.write(
                    dictionaryPath,
                    Stream.concat(values().stream().flatMap(Collection::stream), usedWords.stream())
                            .map(ShiritoriWord::getValue)
                            .sorted()
                            .distinct()
                            .filter(s -> !s.endsWith("ん"))
                            .toList(),
                    StandardOpenOption.CREATE,
                    StandardOpenOption.TRUNCATE_EXISTING,
                    StandardOpenOption.WRITE);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static ShiritoriDictionary load(String dictionaryFileName) {
        return new ShiritoriDictionary(dictionaryFileName);
    }

    public static class EndWordException extends Exception {
        public EndWordException(ShiritoriWord word) {
            this(word.getValue());
        }

        public EndWordException(String word) {
            super("\"" + word + "\" ends with 'ん'.");
        }
    }

    public static class UsedWordException extends Exception {
        public UsedWordException(ShiritoriWord word) {
            this(word.getValue());
        }

        public UsedWordException(String word) {
            super("\"" + word + "\" is already in use.");
        }
    }

    public static class NonVocabularyException extends Exception {
        public NonVocabularyException() {
            super("No vocabulary available.");
        }
    }
}
