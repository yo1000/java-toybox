package com.yo1000.toybox.hitnblow;

import java.security.SecureRandom;

public class SecretGenerator {
    public static String generate(int length) {
        SecureRandom r = new SecureRandom();
        return String.format(
                "%0" + length + "d",
                r.nextInt((int) Math.pow(10, length)));

    }
}
