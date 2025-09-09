package com.yo1000.toybox.hitnblow;

import java.util.stream.Collectors;

public class HitAndBlowChallenger {
    private final String expectation;
    private int count = 1;

    public HitAndBlowChallenger(String expectation) {
        this.expectation = expectation;
    }

    public HitAndBlow challenge(String actuality) {
        int hit = 0;
        int blow = 0;

        String actuallyUnique = actuality.chars()
                .distinct()
                .mapToObj(Character::toString)
                .collect(Collectors.joining());

        for (int i = 0; i < expectation.length(); i++) {
            char c = expectation.charAt(i);

            if (c == actuality.charAt(i)) {
                hit++;
            }

            if (actuallyUnique.indexOf(c) >= 0) {
                blow++;
            }
        }

        return new HitAndBlow(hit, blow - hit, count++);
    }
}
