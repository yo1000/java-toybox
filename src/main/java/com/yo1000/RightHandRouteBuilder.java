package com.yo1000;

public class RightHandRouteBuilder extends RouteBuilder {
    public RightHandRouteBuilder(Maze maze) {
        super(maze);
    }

    @Override
    protected Direction updateDirectionByHandTouchingWall(Position current, Direction direction) {
        Direction workingDirection = direction;
        Position rightHand = current.toRightHand(workingDirection);
        if (!getMaze().check(rightHand)) {
            workingDirection = workingDirection.turnRight();
        }
        return workingDirection;
    }

    @Override
    protected Direction updateDirectionByDeadEnd(Position current, Direction direction) {
        Direction workingDirection = direction;
        Position forward = current.toForward(workingDirection);
        while (getMaze().check(forward)) {
            workingDirection = workingDirection.turnLeft();
            forward = current.toForward(workingDirection);
        }
        return workingDirection;
    }
}
