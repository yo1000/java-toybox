package com.yo1000.toybox.hitnblow;

public class HitAndBlowChallenger {
    private final String expectation;
    private int count = 1;

    public HitAndBlowChallenger(String expectation) {
        this.expectation = expectation;
    }

    public HitAndBlow challenge(String actuality) {
        int hit = 0;

        for (int i = 0; i < expectation.length(); i++) {
            if (actuality.charAt(i) == expectation.charAt(i)) {
                hit++;
            }
        }

        int blow = 0;

        for (int i : actuality.chars().distinct().toArray()) {
            blow += (int) expectation.chars().filter(v -> v == i).count();
        }

        return new HitAndBlow(hit, blow - hit, count++);
    }
}
