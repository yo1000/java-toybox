package com.yo1000.toybox.hitnblow;

import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        int length = args.length >= 1
                ? LengthParser.parse(args[0], 4)
                : 4;

        String test = args.length >= 2 && args[1].matches("[0-9]+")
                ? args[1]
                : null;

        boolean testMode = test != null;

        String secret = testMode ? test : SecretGenerator.generate(length);

        if (testMode) System.out.println("[TEST]");
        System.out.println("Secret code is " + length + "-digits.");
        System.out.println((testMode ? test : "*".repeat(secret.length()))
                + System.lineSeparator());

        HitAndBlowChallenger challenger = new HitAndBlowChallenger(secret);

        new ConsoleListener().listen(
                input -> {
                    if (input.matches("^\\d{" + length + "}$")) {
                        return true;
                    } else {
                        System.out.println("(Please retry)");
                        return false;
                    }
                },
                input -> {
                    HitAndBlow hitAndBlow = challenger.challenge(input);

                    System.out.println("turn | " + hitAndBlow.turn());
                    System.out.println("hit  | " + hitAndBlow.hit());
                    System.out.println("blow | " + hitAndBlow.blow());

                    if (hitAndBlow.hit() != length) {
                        return true;
                    } else {
                        System.out.println(System.lineSeparator() + "Congrats!");
                        return false;
                    }
                });
    }
}
