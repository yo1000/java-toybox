package com.yo1000;

public class Drawer {
    private final boolean showNumbers;

    public Drawer(boolean showNumbers) {
        this.showNumbers = showNumbers;
    }

    public void draw(Maze maze, Route route) {
        String WALL = "██";
        String PASSAGE = "  ";
        String ROUTE = "\u001B[44m  \u001B[0m";

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
                } else if (route.check(p)) {
                    System.out.print(ROUTE);
                } else {
                    System.out.print(PASSAGE);
                }
            }
            System.out.println();
        }
    }
}
