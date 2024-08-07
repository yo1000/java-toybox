package com.yo1000.toybox.maze;

import java.util.Stack;

public abstract class RouteBuilder {
    private final Maze maze;

    public RouteBuilder(Maze maze) {
        this.maze = maze;
    }

    public Route build() {
        Stack<Position> workingRoute = new Stack<>();

        Point start = maze.getStart();
        Direction direction = maze.getInitialDirection();

        Position current = new Position(start.x(), start.y());

        while (!isRoutingFinished(current, direction)) {
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

        Route route = Route.of(workingRoute.stream().map(Position::point).toList());

        // When route returns to starting point in reverse direction to that,
        // closed areas has generated.
        route.setClosed(current.point().equals(getMaze().getStart())
                && direction == getMaze().getInitialDirection().turnBack());

        return route;
    }

    protected Maze getMaze() {
        return maze;
    }

    protected abstract Direction updateDirectionByHandTouchingWall(Position current, Direction direction);

    protected abstract Direction updateDirectionByDeadEnd(Position current, Direction direction);

    protected boolean isRoutingFinished(Position current, Direction direction) {
        return current.point().equals(maze.getGoal());
    }
}
