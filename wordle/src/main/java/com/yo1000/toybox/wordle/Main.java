package com.yo1000.toybox.wordle;

import java.util.stream.Collectors;

public class Main {
    private static final String RESOURCE_PATH = "/valid-wordle-words.txt";

    public static void main(String[] args) {
        WordleDictionary dictionary = new WordleDictionary(RESOURCE_PATH);

        String secret = SecretGenerator.generate(dictionary);

        System.out.println("Secret word is follows.");
        System.out.println(secret
                .chars()
                .mapToObj(v -> "*")
                .collect(Collectors.joining())
                + System.lineSeparator()
        );

        WordleChallenger challenger = new WordleChallenger(secret);
        WordleHistory history = new WordleHistory();

        new ConsoleListener().listen(
                input -> {
                    if (dictionary.contains(input) && input.length() == secret.length()) {
                        return true;
                    } else {
                        System.out.println("(Please retry)");
                        return false;
                    }
                },
                input -> {
                    Wordle wordle = challenger.challenge(input);
                    history.mark(input);

                    System.out.println("turn | " + wordle.getTurn());
                    System.out.println("test |" + wordle);
                    System.out.println("used |" + history);

                    if (!wordle.isCorrect()) {
                        return true;
                    } else {
                        System.out.println(System.lineSeparator() + "Congrats!");
                        return false;
                    }
                });
    }
}
