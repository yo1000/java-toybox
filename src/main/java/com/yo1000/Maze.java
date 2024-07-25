package com.yo1000;

import java.util.HashMap;

public class Maze extends HashMap<Position, Boolean> {
    private Position start;
    private Position goal;

    public Position getStart() {
        return start;
    }

    public void setStart(int x, int y) {
        setStart(new Position(x, y));
    }

    public void setStart(Position start) {
        this.start = start;
        pass(start);
    }

    public Position getGoal() {
        return goal;
    }

    public void setGoal(int x, int y) {
        setGoal(new Position(x, y));
    }

    public void setGoal(Position goal) {
        this.goal = goal;
        pass(goal);
    }

    @Override
    public Boolean get(Object key) {
        return containsKey(key) && super.get(key);
    }

    public void wall(Position p) {
        put(p, true);
    }

    public void wall(int x, int y) {
        wall(new Position(x, y));
    }

    public void pass(Position p) {
        put(p, false);
    }

    public void pass(int x, int y) {
        pass(new Position(x, y));
    }

    public boolean check(Position p) {
        return get(p);
    }

    public boolean check(int x, int y) {
        return get(new Position(x, y));
    }
}
