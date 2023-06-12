package ru.gb.jcore.snakegame;

public class Poison extends Food{
    public Poison(Snake snake) {
        super(snake, GameSnake.POISON_COLOR);
    }
    public boolean isPoison(int x, int y) {
        return (getX() == x && getY() == y);
    }
}
