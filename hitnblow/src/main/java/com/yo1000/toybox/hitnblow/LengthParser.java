package com.yo1000.toybox.hitnblow;

public class LengthParser {
    public static int parse(String lengthAsString) {
        return parse(lengthAsString, 4);
    }

    public static int parse(String lengthAsString, int initialLength) {
        int length = initialLength;

        if (lengthAsString.matches("^\\d{1}$")) {
            length = Integer.parseInt(lengthAsString);
        }

        if (length < 1) length = 1;
        if (length > 9) length = 9;

        return length;
    }
}
