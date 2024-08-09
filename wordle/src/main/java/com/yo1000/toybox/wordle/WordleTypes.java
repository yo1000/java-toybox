package com.yo1000.toybox.wordle;

public enum WordleTypes {
    HIT("\u001B[42m\u001B[30m"),
    BLOW("\u001B[43m\u001B[30m"),
    NONE("\u001B[0m"),
    ;

    private final String color;

    WordleTypes(String color) {
        this.color = color;
    }

    public String getColor() {
        return color;
    }
}
