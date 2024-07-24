package com.yo1000;

public class RouteBuilder {
    private final Maze maze;

    public RouteBuilder(Maze maze) {
        this.maze = maze;
    }

    public Route build() {
        Route journey = new Route();
        Route route = new Route();

        Position start = maze.getStart();
        Position goal = maze.getGoal();

        Position current = new Position(start.x(), start.y());
        Position direction = new Position(1, 0);
        Position next = new Position(current.x() + direction.x(), current.y() + direction.y());
        Position prev = new Position(current.x(), current.y());
        Position leftHand = new Position(current.x(), current.y() + 1);

        boolean returning = false;

        while (!current.equals(goal)) {
            if (!journey.check(current)) {
                returning = false;
            }

            if (returning) {
                route.cancel(current);
            } else {
                journey.visit(current);
                route.visit(current);
                route.visit(prev);
            }

            prev = current;

            if (direction.x() == 1) {
                leftHand = new Position(current.x(), current.y() - 1);
            } else if (direction.y() == 1) {
                leftHand = new Position(current.x() + 1, current.y());
            } else if (direction.x() == -1) {
                leftHand = new Position(current.x(), current.y() + 1);
            } else if (direction.y() == -1) {
                leftHand = new Position(current.x() - 1, current.y());
            }

            if (!maze.check(leftHand)) {
                direction = new Position(leftHand.x() - current.x(), leftHand.y() - current.y());
            }

            if (maze.check(next)) {
                returning = true;

                if (direction.x() == 1) {
                    direction = new Position(0, 1);
                } else if (direction.y() == 1) {
                    direction = new Position(-1, 0);
                } else if (direction.x() == -1) {
                    direction = new Position(0, -1);
                } else {
                    direction = new Position(1, 0);
                }
            }

            next = new Position(current.x() + direction.x(), current.y() + direction.y());

            if (!maze.check(next)) {
                current = next;
            }
        }

        journey.visit(current);
        route.visit(current);
        route.visit(prev);

        return route;
    }
}
