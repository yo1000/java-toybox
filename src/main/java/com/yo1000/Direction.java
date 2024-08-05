package com.yo1000;

import java.security.SecureRandom;

public enum Direction {
    LEFT(new Point(-1, 0)),
    RIGHT(new Point(1, 0)),
    TOP(new Point(0, -1)),
    BOTTOM(new Point(0, 1)),
    ;

    private static final SecureRandom r = new SecureRandom();

    private final Point p;

    Direction(Point p) {
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

    public Direction turnBack() {
        if (this == RIGHT) {
            return LEFT;
        } else if (this == BOTTOM) {
            return TOP;
        } else if (this == LEFT) {
            return RIGHT;
        } else if (this == TOP) {
            return BOTTOM;
        }

        throw new IllegalStateException();
    }

    public static Direction randomFromAll() {
        return ofIndex(r.nextInt(4));
    }

    public static Direction randomFromRightOrBottom() {
        return ofIndex(r.nextInt(2));
    }

    private static Direction ofIndex(int index) {
        return switch (index) {
            case 0 -> Direction.RIGHT;
            case 1 -> Direction.BOTTOM;
            case 2 -> Direction.LEFT;
            case 3 -> Direction.TOP;
            default -> throw new IllegalStateException();
        };
    }
}
