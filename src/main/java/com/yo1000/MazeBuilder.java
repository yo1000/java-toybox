package com.yo1000;

import java.security.SecureRandom;

public class MazeBuilder {
    private static final SecureRandom r = new SecureRandom();

    private final int width;
    private final int height;

    public MazeBuilder(int width, int height) {
        this.width = (width + 1) / 2 * 2;
        this.height = (height + 1) / 2 * 2;
    }

    public Maze build() {
        Maze maze = new Maze();

        int prev = -1;

        for (int i = 0; i <= height; i++) {
            for (int j = 0; j <= width; j++) {
                if (i == 0 || j == 0 || i == height || j == width) {
                    maze.fill(j, i);
                    continue;
                }

                if (i > 0 && j > 0 && i < height && j < width && i % 2 == 0 && j % 2 == 0) {
                    maze.fill(j, i);

                    Position p = new Position(j, i);

                    while (maze.check(p)) {
                        // Check that the new wall arrangement does not enclose the area on all sides.
                        int filled = 0;
                        if (maze.check(j - 2, i - 1)) filled++;
                        if (maze.check(j, i - 1)) filled++;
                        if (maze.check(j - 1, i - 2)) filled++;
                        if (maze.check(j - 1, i)) filled++;

                        // If all four sides may be enclosed, place walls on the right or bottom only.
                        int d = r.nextInt(filled >= 3 ? 2 : 4);

                        while (d == prev) {
                            d = r.nextInt(filled >= 3 ? 2 : 4);
                        }

                        prev = d;

                        p = switch (d) {
                            case 0 -> new Position(j + 1, i);
                            case 1 -> new Position(j, i + 1);
                            case 2 -> new Position(j - 1, i);
                            case 3 -> new Position(j, i - 1);
                            default -> throw new IllegalStateException();
                        };
                    }

                    maze.put(p, true);
                    continue;
                }

                Position p = new Position(j, i);

                if (!maze.containsKey(p)) {
                    maze.clear(p);
                }
            }
        }

        maze.setStart(0, 1);
        maze.setGoal(width, height - 1);

        return maze;
    }
}
