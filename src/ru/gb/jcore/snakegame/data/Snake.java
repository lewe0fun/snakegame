package ru.gb.jcore.snakegame.data;

import ru.gb.jcore.snakegame.GameSnake;
import ru.gb.jcore.snakegame.GameSnakeFrame;

import java.awt.*;
import java.util.LinkedList;

public class Snake {
    private final LinkedList<Cell> snake;
    private int direction;
    private Food food;
    private Poison poison;

    public Snake(int x, int y, int length, int direction) {
        snake = new LinkedList<>();
        for (int i = 0; i < length; i++) {
            snake.add(new Cell(x - i, y, GameSnakeFrame.getCellSize(), GameSnake.getSnakeColor()));
        }
        this.direction = direction;
    }

    public int size() {
        return snake.size();
    }

    public void setDirection(int direction) {
        if ((direction >= GameSnakeFrame.getKeyLeft()) && (direction <= GameSnakeFrame.getKeyDown())) {
            if (Math.abs(this.direction - direction) != 2) {
                this.direction = direction;
            }
        }
    }

    public void move() {
        int x = snake.getFirst().getX();
        int y = snake.getFirst().getY();
        if (direction == GameSnakeFrame.getKeyLeft()) {
            x--;
            if (x < 0) x = GameSnakeFrame.getCanvasWidth() - 1;
        }
        if (direction == GameSnakeFrame.getKeyRight()) {
            x++;
            if (x == GameSnakeFrame.getCanvasWidth()) x = 0;
        }
        if (direction == GameSnakeFrame.getKeyUp()) {
            y--;
            if (y < 0) y = GameSnakeFrame.getCanvasHeight() - 1;
        }
        if (direction == GameSnakeFrame.getKeyDown()) {
            y++;
            if (y == GameSnakeFrame.getCanvasHeight()) y = 0;
        }

        if (isInSnake(x, y) ||           // if the snake crosses itself
                snake.size() < 2) {
            //poison.isPoison(x, y)) { // or if it eats poison
            GameSnake.setGameOver(true);
            return;
        }
        snake.addFirst(new Cell(x, y, GameSnakeFrame.getCellSize(), GameSnake.getSnakeColor())); // new head of snake
        if (food.isFood(x, y)) {
            food.eat();
        } else if (poison.isPoison(x, y)) {
            poison.eat();
            snake.removeLast();
            snake.removeLast();
        } else {
            snake.removeLast();
        }
    }

    public void paint(Graphics2D g) {
        for (Cell cell : snake) {
            cell.paint(g);
        }
    }

    public boolean isInSnake(int x, int y) {
        for (Cell cell : snake) {
            if ((cell.getX() == x) && (cell.getY() == y)) {
                return true;
            }
        }
        return false;
    }

    public void setFood(Food food) {
        this.food = food;
    }

    public void setPoison(Poison poison) {
        this.poison = poison;
    }
}
