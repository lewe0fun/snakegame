package ru.gb.jcore.snakegame.data;

import ru.gb.jcore.snakegame.GameSnake;

import java.awt.*;
import java.util.Random;

public class Food extends Cell {
    private Random random;
    private Snake snake;

    public Food(Snake snake) {
        this(snake, GameSnake.getFoodColor());
    }

    public Food(Snake snake, Color color) {
        super(-1, -1, GameSnake.getCellSize(), color);
        random = new Random();
        this.snake = snake;
    }

    public void appear() {
        int x, y;
        do {
            x = random.nextInt(GameSnake.getCanvasWidth());
            y = random.nextInt(GameSnake.getCanvasHeight());
        } while (snake.isInSnake(x, y));
        set(x, y);
    }

    public boolean isEaten() {
        return getX() == -1;
    }

    public void eat() {
        set(-1, -1);
    }

    public boolean isFood(int x, int y) {
        return (getX() == x && getY() == y);
    }
}
