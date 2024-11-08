//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Snake {
    Scanner scan;
    private boolean isDeath;
    static Random rand = new Random();
    private static int lenghtSnake = 3;
    Score score;
    ArrayList<Integer> coordSnakePart;
    private static int sizeX;
    private static int sizeY;
    private static char[][] map;
    private char input;
    private int posXSnake;
    private int posYSnake;

    public Snake() {
        this.scan = new Scanner(System.in);
        this.score = new Score();
        this.coordSnakePart = new ArrayList();
    }

    public boolean getIsDeath() {
        return this.isDeath;
    }

    public void movable() throws InterruptedException {
        this.coordSnakePart.add(0, this.posYSnake);
        this.coordSnakePart.add(1, this.posXSnake);
        if (this.coordSnakePart.size() > lenghtSnake * 2) {
            this.coordSnakePart.subList(lenghtSnake * 2, this.coordSnakePart.size()).clear();
            map[(Integer)this.coordSnakePart.get(this.coordSnakePart.size() - 2)][(Integer)this.coordSnakePart.get(this.coordSnakePart.size() - 1)] = ' ';
        }

        switch (this.input) {
            case 'a':
                if (map[this.posYSnake][this.posXSnake - 1] != '#') {
                    if (map[this.posYSnake][this.posXSnake - 1] == 'o') {
                        this.isDeath = true;
                    } else {
                        this.coordSnakePart.add(this.posYSnake);
                        this.coordSnakePart.add(this.posXSnake);
                        --this.posXSnake;
                    }
                } else if (map[this.posYSnake][this.posXSnake - 1] == '#') {
                    this.coordSnakePart.add(this.posYSnake);
                    this.coordSnakePart.add(this.posXSnake);
                    this.posXSnake = sizeX - 2;
                }
                break;
            case 'd':
                if (map[this.posYSnake][this.posXSnake + 1] != '#') {
                    if (map[this.posYSnake][this.posXSnake + 1] == 'o') {
                        this.isDeath = true;
                    } else {
                        this.coordSnakePart.add(this.posYSnake);
                        this.coordSnakePart.add(this.posXSnake);
                        ++this.posXSnake;
                    }
                } else if (map[this.posYSnake][this.posXSnake + 1] == '#') {
                    this.coordSnakePart.add(this.posYSnake);
                    this.coordSnakePart.add(this.posXSnake);
                    this.posXSnake = 1;
                }
                break;
            case 's':
                if (map[this.posYSnake + 1][this.posXSnake] != '#') {
                    if (map[this.posYSnake + 1][this.posXSnake] == 'o') {
                        this.isDeath = true;
                    } else {
                        this.coordSnakePart.add(this.posYSnake);
                        this.coordSnakePart.add(this.posXSnake);
                        ++this.posYSnake;
                    }
                } else if (map[this.posYSnake + 1][this.posXSnake] == '#') {
                    this.coordSnakePart.add(this.posYSnake);
                    this.coordSnakePart.add(this.posXSnake);
                    this.posYSnake = 1;
                }
                break;
            case 'w':
                if (map[this.posYSnake - 1][this.posXSnake] != '#') {
                    if (map[this.posYSnake - 1][this.posXSnake] == 'o') {
                        this.isDeath = true;
                    } else {
                        this.coordSnakePart.add(this.posYSnake);
                        this.coordSnakePart.add(this.posXSnake);
                        --this.posYSnake;
                    }
                } else if (map[this.posYSnake - 1][this.posXSnake] == '#') {
                    this.coordSnakePart.add(this.posYSnake);
                    this.coordSnakePart.add(this.posXSnake);
                    this.posYSnake = sizeY - 2;
                }
        }

        this.score.checkUpScore();
        map[this.posYSnake][this.posXSnake] = 'o';
    }

    public void createMap(int sizeY, int sizeX) {
        Snake.sizeY = sizeY;
        Snake.sizeX = sizeX;
        map = new char[sizeY][sizeX];
        this.posXSnake = sizeX / 2;
        this.posYSnake = sizeY / 2;

        for(int i = 0; i < sizeY; ++i) {
            for(int j = 0; j < sizeX; ++j) {
                if (i != 0 && i != sizeY - 1 && j != 0 && j != sizeX - 1) {
                    map[i][j] = ' ';
                } else {
                    map[i][j] = '#';
                }
            }
        }

        map[this.posYSnake][this.posXSnake] = 'o';
    }

    public void printMap() {
        for(int i = 0; i < sizeY; ++i) {
            for(int j = 0; j < sizeX; ++j) {
                System.out.print(map[i][j]);
            }

            System.out.println();
        }

    }

    public static void main(String[] args) throws InterruptedException {
        Snake snake = new Snake();
        snake.createMap(10, 20);
        inputRead inputCore = new inputRead(snake);
        Thread thread1 = new Thread(inputCore);
        thread1.start();

        while(!snake.isDeath) {
            snake.input = inputCore.getInput();
            snake.movable();
            snake.score.spawnScore();
            Thread.sleep(500L);
            snake.printMap();
        }

        System.out.println("Вы проиграли сори ^_^");
    }

    class Score {
        private int score;
        private int posXScore;
        private int posYScore;

        Score() {
        }

        public void spawnScore() {
            boolean haveScore = false;

            for(int i = 0; i < Snake.sizeY; ++i) {
                for(int j = 0; j < Snake.sizeX; ++j) {
                    if (Snake.map[i][j] == '!') {
                        haveScore = true;
                        break;
                    }
                }
            }

            if (!haveScore) {
                this.posXScore = Snake.rand.nextInt(Snake.sizeX - 2) + 1;
                this.posYScore = Snake.rand.nextInt(Snake.sizeY - 2) + 1;
                Snake.map[this.posYScore][this.posXScore] = '!';
            }

        }

        public void checkUpScore() {
            if (Snake.this.posXSnake == this.posXScore && Snake.this.posYSnake == this.posYScore) {
                ++Snake.lenghtSnake;
                System.out.println("                                            lengthSnake-" + Snake.lenghtSnake);
                this.spawnScore();
            }

        }
    }
}
