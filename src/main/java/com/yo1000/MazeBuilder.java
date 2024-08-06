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

        for (int i = 0; i <= height; i++) {
            Direction prevDirection = null;

            for (int j = 0; j <= width; j++) {
                if (i == 0 || j == 0 || i == height || j == width) {
                    maze.wall(j, i);
                    continue;
                }

                if (i > 0 && j > 0 && i % 2 == 0 && j % 2 == 0) {
                    maze.wall(j, i);
                    Cell cell = new Cell(j, i);

                    boolean mayBeClosedArea = mayBeClosedArea(maze.copy(), j, i);

                    while (maze.check(cell)) {
                        // If there is a possibility of creating a closed area,
                        // place walls on the right or bottom only.
                        Direction direction = mayBeClosedArea
                                ? Direction.randomFromRightOrBottom()
                                : Direction.randomFromAll();

                        while (direction == prevDirection) {
                            direction = mayBeClosedArea
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

    private static boolean mayBeClosedArea(Maze maze, int j, int i) {
        if (maze.check(j - 1, i) && maze.check(j, i - 1)) {
            return false;
        }

        if (maze.check(j - 1, i)) {
            maze.setStart(j, i - 1, Direction.LEFT);
        } else {
            maze.setStart(j - 1, i, Direction.TOP);
        }

        return new ClosedAreaCheckRouteBuilder(maze).build().isClosed();
    }

    private static class ClosedAreaCheckRouteBuilder extends LeftHandRouteBuilder {
        public ClosedAreaCheckRouteBuilder(Maze maze) {
            super(maze);
        }

        @Override
        protected boolean isRoutingFinished(Position current, Direction direction) {
            if (!getMaze().containsKey(current.point()) || getMaze().get(current.point()) == null) {
                return true;
            }
            // When route returns to starting point in reverse direction to that,
            // closed areas has generated.
            if (current.point().equals(getMaze().getStart())
                    && direction == getMaze().getInitialDirection().turnBack()) {
                return true;
            }
            return super.isRoutingFinished(current, direction);
        }
    }
}
