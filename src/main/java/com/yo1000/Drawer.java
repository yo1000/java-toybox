package com.yo1000;

public class Drawer {
    private final boolean showNumbers;
    private final boolean withUnexplored;

    public Drawer(boolean showNumbers) {
        this(showNumbers, false);
    }

    public Drawer(boolean showNumbers, boolean withUnexplored) {
        this.showNumbers = showNumbers;
        this.withUnexplored = withUnexplored;
    }

    public void draw(Maze maze, Route leftHandRoute, Route rightHandRoute) {
        String WALL = "██";
        String PASSAGE = "  ";
        String ROUTE_RED = "\u001B[41m  \u001B[0m";
        String ROUTE_GREEN = "\u001B[42m  \u001B[0m";
        String ROUTE_YELLOW = "\u001B[43m  \u001B[0m";
        String ROUTE_BLUE = "\u001B[44m  \u001B[0m";
        String ROUTE_PURPLE = "\u001B[45m  \u001B[0m";
        String ROUTE_CYAN = "\u001B[46m  \u001B[0m";

        if (withUnexplored) {
            print(maze, null, null, WALL, ROUTE_PURPLE, ROUTE_BLUE, ROUTE_RED, PASSAGE);
            System.out.println();
        }

        print(maze, leftHandRoute, rightHandRoute, WALL, ROUTE_PURPLE, ROUTE_BLUE, ROUTE_RED, PASSAGE);
    }

    private void print(
            Maze maze, Route leftHandRoute, Route rightHandRoute,
            String WALL, String ROUTE_BOTH, String ROUTE_LEFT_ONLY, String ROUTE_RIGHT_ONLY, String PASSAGE) {
        if (showNumbers) {
            System.out.print("  ");
            for (int j = 0; j <= maze.getGoal().x(); j++) {
                System.out.print((j % 10) + " ");
            }
            System.out.println();
        }

        for (int i = 0; i <= maze.getGoal().y() + 1; i++) {
            for (int j = 0; j <= maze.getGoal().x(); j++) {
                if (showNumbers && j == 0) {
                    System.out.print((i % 10) + " ");
                }

                Point p = new Point(j, i);

                if (maze.check(p)) {
                    System.out.print(WALL);
                    continue;
                }

                boolean leftCheck = leftHandRoute != null && leftHandRoute.check(p);
                boolean rightCheck = rightHandRoute != null && rightHandRoute.check(p);

                if (leftCheck && rightCheck) {
                    System.out.print(ROUTE_BOTH);
                    continue;
                }

                if (leftCheck) {
                    System.out.print(ROUTE_LEFT_ONLY);
                    continue;
                }

                if (rightCheck) {
                    System.out.print(ROUTE_RIGHT_ONLY);
                    continue;
                }

                System.out.print(PASSAGE);
            }
            System.out.println();
        }
    }
}
