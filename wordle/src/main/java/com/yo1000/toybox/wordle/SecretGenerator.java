package com.yo1000.toybox.wordle;

import java.security.SecureRandom;

public class SecretGenerator {
    private static final SecureRandom r = new SecureRandom();

    public static String generate(WordleDictionary dictionary) {
        return dictionary.get(r.nextInt(dictionary.size()));
    }
}
