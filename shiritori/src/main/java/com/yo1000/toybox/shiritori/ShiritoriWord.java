package com.yo1000.toybox.shiritori;

import java.util.Map;
import java.util.Objects;

public class ShiritoriWord {
    private static final Map<Character, Character> TAIL_CHAR_MAP = Map.ofEntries(
            Map.entry('ぁ', 'あ'),
            Map.entry('ぃ', 'い'),
            Map.entry('ぅ', 'う'),
            Map.entry('ぇ', 'え'),
            Map.entry('ぉ', 'お'),
            Map.entry('っ', 'つ'),
            Map.entry('ゃ', 'や'),
            Map.entry('ゅ', 'ゆ'),
            Map.entry('ょ', 'よ')
    );

    private final String value;
    private transient Character cachedHead;
    private transient Character cachedTail;

    public ShiritoriWord(String value) {
        this.value = value;
    }

    public char getHead() {
        if (cachedHead != null) return cachedHead;

        if (value.isEmpty()) throw new RuntimeException("value is empty");

        return cachedHead = value.charAt(0);
    }

    public char getTail() {
        if (cachedTail != null) return cachedTail;

        if (value.isEmpty()) throw new RuntimeException("value is empty");

        String s = value;
        while (s.length() >= 2 && s.endsWith("ー")) {
            s = s.substring(0, s.length() - 1);
        }

        if (s.length() == 1) return cachedTail = s.charAt(0);

        char tailChar = s.charAt(s.length() - 1);

        return cachedTail = TAIL_CHAR_MAP.getOrDefault(tailChar, tailChar);
    }

    public boolean isEmpty() {
        return value == null || value.isBlank();
    }

    public boolean isDeadEnd() {
        return getTail() == 'ん';
    }

    public boolean isContinuableWord(String word) {
        return isContinuableWord(new ShiritoriWord(word));
    }

    public boolean isContinuableWord(ShiritoriWord word) {
        if (!word.getValue().matches("^(?![ぁぃぅぇぉっゃゅょんー])[ぁ-んー]{2,}$")
                || word.getValue().matches("[ゎゐゑを]*")) {
            return false;
        }

        return word.getValue().startsWith(String.valueOf(getTail()));
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ShiritoriWord that)) return false;
        return Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(value);
    }
}
