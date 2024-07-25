package com.yo1000;

public enum Direction {
    LEFT(new Position(-1, 0)),
    RIGHT(new Position(1, 0)),
    TOP(new Position(0, -1)),
    BOTTOM(new Position(0, 1)),
    ;

    private final Position p;

    Direction(Position p) {
        this.p = p;
    }

    public int x() {
        return p.x();
    }

    public int y() {
        return p.y();
    }

    public Direction turnLeft() {
        if (this == RIGHT) {
            return TOP;
        } else if (this == TOP) {
            return LEFT;
        } else if (this == LEFT) {
            return BOTTOM;
        } else if (this == BOTTOM) {
            return RIGHT;
        }

        throw new IllegalStateException();
    }

    public Direction turnRight() {
        if (this == RIGHT) {
            return BOTTOM;
        } else if (this == BOTTOM) {
            return LEFT;
        } else if (this == LEFT) {
            return TOP;
        } else if (this == TOP) {
            return RIGHT;
        }

        throw new IllegalStateException();
    }
}
