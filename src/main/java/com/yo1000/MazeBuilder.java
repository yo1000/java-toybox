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

        int prevDirection = -1;

        for (int i = 0; i <= height; i++) {
            for (int j = 0; j <= width; j++) {
                if (i == 0 || j == 0 || i == height || j == width) {
                    maze.wall(j, i);
                    continue;
                }

                if (i > 0 && j > 0 && i < height && j < width && i % 2 == 0 && j % 2 == 0) {
                    maze.wall(j, i);

                    Position p = new Position(j, i);

                    while (maze.check(p)) {
                        // Check that the new wall arrangement does not enclose the area on all sides.
                        int walled = 0;
                        if (maze.check(j - 2, i - 1)) walled++;
                        if (maze.check(j, i - 1)) walled++;
                        if (maze.check(j - 1, i - 2)) walled++;
                        if (maze.check(j - 1, i)) walled++;

                        // If all four sides may be enclosed, place walls on the right or bottom only.
                        int direction = r.nextInt(walled >= 3 ? 2 : 4);

                        while (direction == prevDirection) {
                            direction = r.nextInt(walled >= 3 ? 2 : 4);
                        }

                        prevDirection = direction;

                        p = switch (direction) {
                            case 0 -> new Position(j + 1, i);
                            case 1 -> new Position(j, i + 1);
                            case 2 -> new Position(j - 1, i);
                            case 3 -> new Position(j, i - 1);
                            default -> throw new IllegalStateException();
                        };
                    }

                    maze.wall(p);
                    continue;
                }

                Position p = new Position(j, i);

                if (!maze.containsKey(p)) {
                    maze.pass(p);
                }
            }
        }

        maze.setStart(0, 1);
        maze.setGoal(width, height - 1);

        return maze;
    }
}
