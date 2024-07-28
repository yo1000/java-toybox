package com.yo1000;

import java.util.Stack;

public class RouteBuilder {
    private final Maze maze;

    public RouteBuilder(Maze maze) {
        this.maze = maze;
    }

    public Route build() {
        Stack<Position> workingRoute = new Stack<>();

        Point start = maze.getStart();
        Point goal = maze.getGoal();

        Direction direction = Direction.RIGHT;
        Position current = new Position(start.x(), start.y());

        while (!current.point().equals(goal)) {
            if (workingRoute.contains(current)) {
                Position surplus;
                do {
                    surplus = workingRoute.pop();
                } while (!surplus.equals(current));
            }

            workingRoute.push(current);

            Position leftHand = current.toLeftHand(direction);
            if (!maze.check(leftHand)) {
                direction = direction.turnLeft();
            }

            Position forward = current.toForward(direction);
            while (maze.check(forward)) {
                direction = direction.turnRight();
                forward = current.toForward(direction);
            }

            current = forward;
        }

        workingRoute.push(current);

        return Route.of(workingRoute.stream().map(Position::point).toList());
    }
}
