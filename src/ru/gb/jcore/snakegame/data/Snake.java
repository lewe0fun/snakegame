package ru.gb.jcore.snakegame.data;

import ru.gb.jcore.snakegame.GameSnake;

import java.awt.*;
import java.util.LinkedList;

public class Snake {
    private LinkedList<Cell> snake;
    private int direction;
    private Food food;
    private Poison poison;

    public Snake(int x, int y, int length, int direction) {
        snake = new LinkedList<>();
        for (int i = 0; i < length; i++) {
            snake.add(new Cell(x - i, y, GameSnake.getCellSize(), GameSnake.getSnakeColor()));
        }
        this.direction = direction;
    }

    public int size() {
        return snake.size();
    }

    public void setDirection(int direction) {
        if ((direction >= GameSnake.getKeyLeft()) && (direction <= GameSnake.getKeyDown())) {
            if (Math.abs(this.direction - direction) != 2) {
                this.direction = direction;
            }
        }
    }

    public void move() {
        int x = snake.getFirst().getX();
        int y = snake.getFirst().getY();
/*       final  int left = GameSnake.getKeyLeft();
       final int right = GameSnake.getKeyRight();
       final int up = GameSnake.getKeyUp();
        final int down = GameSnake.getKeyDown();*/


        if (direction == GameSnake.getKeyLeft()) {
            x--;
            if (x < 0) x = GameSnake.getCanvasWidth() - 1;
        }
        if (direction == GameSnake.getKeyRight()) {
            x++;
            if (x == GameSnake.getCanvasWidth()) x = 0;
        }
        if (direction == GameSnake.getKeyUp()) {
            y--;
            if (y < 0) y = GameSnake.getCanvasHeight() - 1;
        }
        if (direction == GameSnake.getKeyDown()) {
            y++;
            if (y == GameSnake.getCanvasHeight()) y = 0;
        }


/*        switch (direction) {
            case left:
                x--;
                if (x < 0) x = GameSnake.getCanvasWidth() - 1;
                break;
            case right:
                x++;
                if (x == GameSnake.getCanvasWidth()) x = 0;
                break;
            case up:
                y--;
                if (y < 0) y = GameSnake.getCanvasHeight() - 1;
                break;
            case down:
                y++;
                if (y == GameSnake.getCanvasHeight()) y = 0;
                break;
        }*/
        if (isInSnake(x, y) ||           // if the snake crosses itself
                snake.size() < 2) {
            //poison.isPoison(x, y)) { // or if it eats poison
            GameSnake.setGameOver(true);
            return;
        }
        snake.addFirst(new Cell(x, y, GameSnake.getCellSize(), GameSnake.getSnakeColor())); // new head of snake
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
