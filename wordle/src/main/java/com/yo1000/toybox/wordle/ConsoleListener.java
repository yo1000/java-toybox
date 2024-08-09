package com.yo1000.toybox.wordle;

import java.util.Scanner;

public class ConsoleListener {
    public void listen(InputHandler onValidate, InputHandler onPlay) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("> ");
            String input = scanner.next();

            if (!onValidate.handle(input)) {
                continue;
            }

            if (!onPlay.handle(input)) {
                break;
            }

            System.out.println();
        }
    }

    public interface InputHandler {
        boolean handle(String input);
    }
}
