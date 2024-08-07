package com.yo1000.toybox.maze;

import java.util.HashMap;
import java.util.stream.Collectors;

public class Maze extends HashMap<Point, Boolean> {
    private Direction initialDirection;
    private Point start;
    private Point goal;

    public Direction getInitialDirection() {
        return initialDirection;
    }

    public Point getStart() {
        return start;
    }

    public void setStart(int x, int y) {
        setStart(new Point(x, y));
    }

    public void setStart(int x, int y, Direction initialDirection) {
        setStart(new Point(x, y), initialDirection);
    }

    public void setStart(Point start) {
        setStart(start, Direction.RIGHT);
    }

    public void setStart(Point start, Direction initialDirection) {
        this.start = start;
        this.initialDirection = initialDirection;
        pass(start);
    }

    public Point getGoal() {
        return goal;
    }

    public void setGoal(int x, int y) {
        setGoal(new Point(x, y));
    }

    public void setGoal(Point goal) {
        this.goal = goal;
        pass(goal);
    }

    @Override
    public Boolean get(Object key) {
        return containsKey(key) && super.get(key);
    }

    public void wall(Point p) {
        put(p, true);
    }

    public void wall(int x, int y) {
        wall(new Point(x, y));
    }

    public void wall(Cell cell) {
        wall(cell.point());
    }

    public void pass(Point p) {
        put(p, false);
    }

    public void pass(int x, int y) {
        pass(new Point(x, y));
    }

    public void pass(Cell cell) {
        pass(cell.point());
    }

    public boolean check(Point p) {
        return get(p);
    }

    public boolean check(int x, int y) {
        return check(new Point(x, y));
    }

    public boolean check(Cell cell) {
        return check(cell.point());
    }

    public boolean check(Position position) {
        return check(position.point());
    }

    public Maze copy() {
        return entrySet().stream()
                .collect(Collectors.toMap(
                        Entry::getKey,
                        Entry::getValue,
                        (a, b) -> a,
                        Maze::new));
    }
}
