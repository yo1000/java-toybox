package com.yo1000;

import java.util.HashMap;

public class Route extends HashMap<Position, Boolean> {
    @Override
    public Boolean get(Object key) {
        return containsKey(key) && super.get(key);
    }

    public boolean check(Position p) {
        return get(p);
    }

    public boolean check(int x, int y) {
        return get(new Position(x, y));
    }

    public void visit(int x, int y) {
        put(new Position(x, y), true);
    }

    public void visit(Position p) {
        put(p, true);
    }

    public void cancel(int x, int y) {
        put(new Position(x, y), false);
    }

    public void cancel(Position p) {
        put(p, false);
    }
}
