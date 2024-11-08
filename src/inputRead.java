//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

import java.util.Scanner;

public class inputRead implements Runnable {
    private static Scanner scanner;
    Snake snake;
    private static char input;

    public inputRead(Snake snake) {
        this.snake = snake;
    }

    public char getInput() {
        return input;
    }

    public void run() {
        while(!this.snake.getIsDeath()) {
            input = scanner.next().charAt(0);
        }

    }

    static {
        scanner = new Scanner(System.in);
    }
}
