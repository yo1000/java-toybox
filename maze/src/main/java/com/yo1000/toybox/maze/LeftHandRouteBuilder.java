package com.yo1000.toybox.maze;

public class LeftHandRouteBuilder extends RouteBuilder {
    public LeftHandRouteBuilder(Maze maze) {
        super(maze);
    }

    @Override
    protected Direction updateDirectionByHandTouchingWall(Position current, Direction direction) {
        Direction workingDirection = direction;
        Position leftHand = current.toLeftHand(workingDirection);
        if (!getMaze().check(leftHand)) {
            workingDirection = workingDirection.turnLeft();
        }
        return workingDirection;
    }

    @Override
    protected Direction updateDirectionByDeadEnd(Position current, Direction direction) {
        Direction workingDirection = direction;
        Position forward = current.toForward(workingDirection);
        while (getMaze().check(forward)) {
            workingDirection = workingDirection.turnRight();
            forward = current.toForward(workingDirection);
        }
        return workingDirection;
    }
}
