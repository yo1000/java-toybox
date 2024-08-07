package com.yo1000.toybox.maze;

public record Cell(
        Point point
) {
    public Cell(int x, int y) {
        this(new Point(x, y));
    }
}
