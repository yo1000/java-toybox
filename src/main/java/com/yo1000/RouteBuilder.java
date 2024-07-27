package com.yo1000;

import java.util.Stack;

public class RouteBuilder {
    private final Maze maze;

    public RouteBuilder(Maze maze) {
        this.maze = maze;
    }

    public Route build() {
        Stack<Position> workingRoute = new Stack<>();

        Position start = maze.getStart();
        Position goal = maze.getGoal();

        Direction direction = Direction.RIGHT;
        Position current = new Position(start.x(), start.y());

        while (!current.equals(goal)) {
            if (workingRoute.contains(current)) {
                Position surplus;
                do {
                    surplus = workingRoute.pop();
                } while (!surplus.equals(current));
            }

            workingRoute.push(current);

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

        workingRoute.push(current);

        return Route.of(workingRoute.stream().toList());
    }
}
