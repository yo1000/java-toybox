package com.yo1000.toybox.wordle;

public class WordleChallenger {
    private final String secret;
    private int turn = 1;

    public WordleChallenger(String secret) {
        this.secret = secret;
    }

    public Wordle challenge(String input) {
        Wordle wordle = new Wordle(turn++);

        for (int i = 0; i < input.length(); i++) {
            char inputChar = input.charAt(i);
            char secretChar = secret.charAt(i);

            if (inputChar == secretChar) {
                wordle.append(inputChar, WordleTypes.HIT);
            } else if (secret.contains(String.valueOf(inputChar))) {
                wordle.append(inputChar, WordleTypes.BLOW);
            } else {
                wordle.append(inputChar, WordleTypes.NONE);
            }
        }

        return wordle;
    }
}
