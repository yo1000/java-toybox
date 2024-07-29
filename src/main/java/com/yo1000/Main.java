package com.yo1000;

public class Main {
    public static void main(String[] args) {
        int w = 20;
        int h = 10;

        if (args.length >= 1) {
            try {
                w = Integer.parseInt(args[0]);
            } catch (Exception e) {
                // NOP
            }
        }

        if (args.length >= 2) {
            try {
                h = Integer.parseInt(args[1]);
            } catch (Exception e) {
                // NOP
            }
        }

        System.out.println("Width : " + w);
        System.out.println("Height: " + h);
        System.out.println();

        Maze maze = new MazeBuilder(w, h).build();
        Route leftHandRoute = new LeftHandRouteBuilder(maze).build();
        Route rightHandRoute = new RightHandRouteBuilder(maze).build();

        Drawer drawer = new Drawer(true);
        drawer.draw(maze, leftHandRoute, rightHandRoute);
    }
}
