package com.yo1000;

import java.util.Stack;

public class RouteBuilder {
    private final Maze maze;

    public RouteBuilder(Maze maze) {
        this.maze = maze;
    }

    public Route build() {
        Stack<Position> journeyStack = new Stack<>();

        Position start = maze.getStart();
        Position goal = maze.getGoal();

        Direction direction = Direction.RIGHT;
        Position current = new Position(start.x(), start.y());

        while (!current.equals(goal)) {
            if (journeyStack.contains(current)) {
                Position surplus;
                do {
                    surplus = journeyStack.pop();
                } while (!surplus.equals(current));
            }

            journeyStack.push(current);

            Position leftHand = new Position(current.x() + direction.y(), current.y() - direction.x());
            if (!maze.check(leftHand)) {
                direction = direction.turnLeft();
            }

            Position next = new Position(current.x() + direction.x(), current.y() + direction.y());
            while (maze.check(next)) {
                direction = direction.turnRight();
                next = new Position(current.x() + direction.x(), current.y() + direction.y());
            }

            current = next;
        }

        journeyStack.push(current);

        return Route.of(journeyStack.stream().toList());
    }
}
