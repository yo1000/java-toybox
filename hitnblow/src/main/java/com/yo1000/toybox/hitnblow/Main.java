package com.yo1000.toybox.hitnblow;

import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        int length = args.length >= 1
                ? LengthParser.parse(args[0], 4)
                : 4;

        String secret = SecretGenerator.generate(length);

        System.out.println("Secret code is " + length + "-digits.");
        System.out.println(secret
                .chars()
                .mapToObj(v -> "*")
                .collect(Collectors.joining())
                + System.lineSeparator()
        );

        HitAndBlowChallenger counter = new HitAndBlowChallenger(secret);

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
                    HitAndBlow hitAndBlow = counter.challenge(input);

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
