package com.yo1000.toybox.shiritori;

import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

public class Main {
    public static void main(String[] args) {
        ShiritoriDictionary dict = Optional.of(args)
                .filter(arguments -> arguments.length >= 1)
                .map(arguments -> arguments[0])
                .map(arg -> arg.replaceAll("[^A-z0-9-_.]+", ""))
                .filter(s -> !s.isBlank())
                .map(ShiritoriDictionary::load)
                .orElse(new ShiritoriDictionary());

        System.out.println("Dictionary file path is "
                + dict.getDictionaryPath().toAbsolutePath()
                + System.lineSeparator());

        AtomicReference<ShiritoriWord> current = new AtomicReference<>();

        new ConsoleListener().listen(
                input -> {
                    if (Optional.ofNullable(current.get())
                            .map(word -> word.isContinuableWord(input))
                            .orElse(true)) {
                        return true;
                    } else {
                        System.out.println("(Please retry)");
                        return false;
                    }
                },
                input -> {
                    try {
                        ShiritoriWord word = new ShiritoriWord(input);

                        System.out.println("head | " + word.getHead());
                        System.out.println("tail | " + word.getTail());

                        dict.check(word);
                        current.set(word);
                    } catch (ShiritoriDictionary.UsedWordException
                             | ShiritoriDictionary.EndWordException e) {
                        System.out.println();
                        System.out.println(e.getMessage());
                        System.out.println("You lose.");
                        return false;
                    }

                    try {
                        ShiritoriWord word = dict.randomByUnused(current.get().getTail());

                        System.out.println();
                        System.out.println("< " + word);
                        System.out.println("head | " + word.getHead());
                        System.out.println("tail | " + word.getTail());

                        dict.check(word);
                        current.set(word);
                    } catch (ShiritoriDictionary.UsedWordException
                             | ShiritoriDictionary.EndWordException
                             | ShiritoriDictionary.NonVocabularyException e) {
                        System.out.println();
                        System.out.println(e.getMessage());
                        System.out.println("You win.");
                        return false;
                    }

                    return true;
                }
        );

        dict.save();
    }
}
