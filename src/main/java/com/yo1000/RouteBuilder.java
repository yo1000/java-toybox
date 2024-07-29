package com.yo1000;

import java.util.Stack;

public abstract class RouteBuilder {
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

            direction = updateDirectionByHandTouchingWall(current, direction);
            direction = updateDirectionByDeadEnd(current, direction);

            current = current.toForward(direction);
        }

        workingRoute.push(current);

        return Route.of(workingRoute.stream().map(Position::point).toList());
    }

    protected Maze getMaze() {
        return maze;
    }

    protected abstract Direction updateDirectionByHandTouchingWall(Position current, Direction direction);

    protected abstract Direction updateDirectionByDeadEnd(Position current, Direction direction);
}
