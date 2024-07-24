package com.yo1000;

public class Drawer {
    private final boolean showNumbers;

    public Drawer(boolean showNumbers) {
        this.showNumbers = showNumbers;
    }

    public void draw(Maze maze, Route route) {
        String BLOCKED_COLOR = "";
        String VISITED_COLOR = "\u001B[44m";
        String RESET_COLOR = "\u001B[0m";

        String SPACE = "  ";
        String BLOCKED = BLOCKED_COLOR + "██" + RESET_COLOR;
        String VISITED = VISITED_COLOR + "  " + RESET_COLOR;

        if (showNumbers) {
            System.out.print(SPACE);
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

                Position p = new Position(j, i);

                if (maze.check(p)) {
                    System.out.print(BLOCKED);
                } else if (route.check(p)) {
                    System.out.print(VISITED);
                } else {
                    System.out.print(SPACE);
                }
            }
            System.out.println();
        }
    }
}
