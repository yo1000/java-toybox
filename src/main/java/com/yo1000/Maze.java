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
        clear(start);
    }

    public Position getGoal() {
        return goal;
    }

    public void setGoal(int x, int y) {
        setGoal(new Position(x, y));
    }

    public void setGoal(Position goal) {
        this.goal = goal;
        clear(goal);
    }

    @Override
    public Boolean get(Object key) {
        return containsKey(key) && super.get(key);
    }

    public void fill(Position p) {
        put(p, true);
    }

    public void fill(int x, int y) {
        fill(new Position(x, y));
    }

    public void clear(Position p) {
        put(p, false);
    }

    public void clear(int x, int y) {
        clear(new Position(x, y));
    }

    public boolean check(Position p) {
        return get(p);
    }

    public boolean check(int x, int y) {
        return get(new Position(x, y));
    }
}
