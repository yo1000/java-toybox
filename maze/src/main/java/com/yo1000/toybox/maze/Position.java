package com.yo1000.toybox.maze;

public record Position(
        Point point
) {
    public Position(int x, int y) {
        this(new Point(x, y));
    }

    Position toForward(Direction direction) {
        return new Position(new Point(point().x() + direction.x(), point().y() + direction.y()));
    }

    Position toLeftHand(Direction direction) {
        return new Position(new Point(point().x() + direction.y(), point().y() - direction.x()));
    }

    Position toRightHand(Direction direction) {
        return new Position(new Point(point().x() - direction.y(), point().y() + direction.x()));
    }
}
