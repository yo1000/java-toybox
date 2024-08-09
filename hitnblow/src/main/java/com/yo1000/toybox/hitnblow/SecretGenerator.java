package com.yo1000.toybox.hitnblow;

import java.security.SecureRandom;

public class SecretGenerator {
    private static final SecureRandom r = new SecureRandom();

    public static String generate(int length) {
        return String.format(
                "%0" + length + "d",
                r.nextInt((int) Math.pow(10, length)));

    }
}
