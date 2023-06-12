package ru.gb.jcore.snakegame.data;

import ru.gb.jcore.snakegame.GameSnake;
import ru.gb.jcore.snakegame.GameSnakeFrame;

public class Poison extends Food{
    public Poison(Snake snake) {
        super(snake, GameSnake.getPoisonColor());
    }
    public boolean isPoison(int x, int y) {
        return (getX() == x && getY() == y);
    }
}
