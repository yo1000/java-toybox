package com.yo1000;

public class Main {
    public static void main(String[] args) {
        int width = 20;
        int height = 10;
        boolean withUnexplored = false;

        if (args.length >= 1) {
            try {
                width = Integer.parseInt(args[0]);
            } catch (Exception e) {
                // NOP
            }
        }

        if (args.length >= 2) {
            try {
                height = Integer.parseInt(args[1]);
            } catch (Exception e) {
                // NOP
            }
        }

        if (args.length >= 3) {
            try {
                withUnexplored = !args[2].equalsIgnoreCase("false");
            } catch (Exception e) {
                // NOP
            }
        }

        System.out.println("Width : " + width);
        System.out.println("Height: " + height);
        System.out.println();

        Maze maze = new MazeBuilder(width, height).build();
        Route leftHandRoute = new LeftHandRouteBuilder(maze).build();
        Route rightHandRoute = new RightHandRouteBuilder(maze).build();

        Drawer drawer = new Drawer(true, withUnexplored);
        drawer.draw(maze, leftHandRoute, rightHandRoute);
    }
}
