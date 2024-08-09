package com.yo1000.toybox.wordle;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Wordle {
    private final List<WordleChar> wordleChars = new ArrayList<>();
    private final int turn;

    public Wordle(int turn) {
        this.turn = turn;
    }

    public Wordle append(char c, WordleTypes type) {
        append(new WordleChar(c, type));
        return this;
    }

    public Wordle append(WordleChar c) {
        wordleChars.add(c);
        return this;
    }

    public boolean isCorrect() {
        return wordleChars.stream()
                .allMatch(c -> c.type() == WordleTypes.HIT);
    }

    public int getTurn() {
        return turn;
    }

    @Override
    public String toString() {
        return wordleChars.stream()
                .map(WordleChar::toString)
                .collect(Collectors.joining());
    }
}
