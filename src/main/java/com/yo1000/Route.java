package com.yo1000;

import java.util.Collection;
import java.util.HashMap;
import java.util.stream.Collectors;

public class Route extends HashMap<Point, Boolean> {
    @Override
    public Boolean get(Object key) {
        return containsKey(key) && super.get(key);
    }

    public boolean check(Point p) {
        return get(p);
    }

    public boolean check(int x, int y) {
        return get(new Point(x, y));
    }

    public static Route of(Collection<Point> positions) {
        return positions.stream().collect(Collectors.toMap(p -> p, p -> true, (a, b) -> true, Route::new));
    }
}
