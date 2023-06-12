package ru.gb.jcore.snakegame.data;

import ru.gb.jcore.snakegame.GameSnake;
import ru.gb.jcore.snakegame.GameSnakeFrame;

import java.awt.*;
import java.util.Random;

public class Food extends Cell {
    private final Random random;
    private final Snake snake;

    public Food(Snake snake) {
        this(snake, GameSnake.getFoodColor());
    }

    public Food(Snake snake, Color color) {
        super(-1, -1, GameSnakeFrame.getCellSize(), color);
        random = new Random();
        this.snake = snake;
    }

    public void appear() {
        int x, y;
        do {
            x = random.nextInt(GameSnakeFrame.getCanvasWidth());
            y = random.nextInt(GameSnakeFrame.getCanvasHeight());
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
