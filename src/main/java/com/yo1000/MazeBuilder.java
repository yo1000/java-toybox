package com.yo1000;

public class MazeBuilder {
    private final int width;
    private final int height;

    public MazeBuilder(int width, int height) {
        this.width = (width + 1) / 2 * 2;
        this.height = (height + 1) / 2 * 2;
    }

    public Maze build() {
        Maze maze = new Maze();

        Direction prevDirection = null;

        for (int i = 0; i <= height; i++) {
            for (int j = 0; j <= width; j++) {
                if (i == 0 || j == 0 || i == height || j == width) {
                    maze.wall(j, i);
                    continue;
                }

                if (i > 0 && j > 0 && i < height && j < width && i % 2 == 0 && j % 2 == 0) {
                    maze.wall(j, i);

                    Cell cell = new Cell(j, i);

                    while (maze.check(cell)) {
                        boolean walledOnAllSides = maze.beWalledOnAllSides(cell);

                        // If all four sides may be enclosed, place walls on the right or bottom only.
                        Direction direction = walledOnAllSides
                                ? Direction.randomFromRightOrBottom()
                                : Direction.randomFromAll();

                        while (direction == prevDirection) {
                            direction = walledOnAllSides
                                    ? Direction.randomFromRightOrBottom()
                                    : Direction.randomFromAll();
                        }

                        prevDirection = direction;

                        cell = new Cell(j + direction.x(), i + direction.y());
                    }

                    maze.wall(cell);
                    continue;
                }

                Cell cell = new Cell(j, i);

                if (!maze.check(cell)) {
                    maze.pass(cell);
                }
            }
        }

        maze.setStart(0, 1);
        maze.setGoal(width, height - 1);

        return maze;
    }
}
