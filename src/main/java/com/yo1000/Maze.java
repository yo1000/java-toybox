package com.yo1000;

import java.util.HashMap;

public class Maze extends HashMap<Point, Boolean> {
    private Point start;
    private Point goal;

    public Point getStart() {
        return start;
    }

    public void setStart(int x, int y) {
        setStart(new Point(x, y));
    }

    public void setStart(Point start) {
        this.start = start;
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

    public boolean beWalledOnAllSides(Cell cell) {
        return beWalledOnAllSides(cell.point());
    }

    public boolean beWalledOnAllSides(Point point) {
        return beWalledOnAllSides(point.x(), point.y());
    }

    public boolean beWalledOnAllSides(int x, int y) {
        // Check that the new wall arrangement does not enclose the area on all sides.
        int walled = 0;
        if (check(x - 2, y - 1)) walled++;
        if (check(x, y - 1)) walled++;
        if (check(x - 1, y - 2)) walled++;
        if (check(x - 1, y)) walled++;

        return walled >= 3;
    }
}
