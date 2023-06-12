package ru.gb.jcore.snakegame;

import java.awt.*;
import java.util.Random;

public class Food extends Cell{
    private Random random;
    private Snake snake;
    //private Color color;

    public Food(Snake snake, Color color) {
        super(-1, -1, GameSnake.CELL_SIZE, color);
        random = new Random();
        this.snake = snake;
    }

    public void appear(){
        int x, y;
        do{
            x = random.nextInt(GameSnake.CANVAS_WIDTH);
            y = random.nextInt(GameSnake.CANVAS_HEIGHT);
        } while(snake.isInSnake(x, y));
        set(x, y);
    }

    public boolean isEaten(){
        return getX() == -1;
    }

    public void eat() {
        set(-1, -1);
    }

    public boolean isFood(int x, int y) {
        return (getX() == x && getY() == y);
    }
}
