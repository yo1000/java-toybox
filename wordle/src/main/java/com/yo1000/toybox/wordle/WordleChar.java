package com.yo1000.toybox.wordle;

public record WordleChar(
        char c,
        WordleTypes type
) {
    @Override
    public String toString() {
        return type.getColor() + " " + c + " " + WordleTypes.NONE.getColor();
    }
}
