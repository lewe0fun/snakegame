package ru.gb.jcore.snakegame;

import ru.gb.jcore.snakegame.data.Food;
import ru.gb.jcore.snakegame.data.Poison;
import ru.gb.jcore.snakegame.data.Snake;

import java.awt.*;

public class GameSnake {
    final static Color SNAKE_COLOR = Color.darkGray;
    final static Color FOOD_COLOR = Color.green;
    final static Color POISON_COLOR = Color.red;
    final int START_SNAKE_SIZE = 2;            // initialization data
    int snakeSize = 0;                         // current snake size
    static boolean gameOver = false;           // a sign game is over or not
    static Snake snake;                     // declare a snake object
    static Food food;                       // declare a food object
    static Poison poison;                   // declare a poison object
    static GameSnakeFrame gameSnakeFrame;

    public static void main(String[] args) {
        new GameSnake().game();
    }

    public GameSnake() {
        gameSnakeFrame = new GameSnakeFrame();
    }

    private void game() {
        snake = new Snake(GameSnakeFrame.START_SNAKE_X, GameSnakeFrame.START_SNAKE_Y, START_SNAKE_SIZE, GameSnakeFrame.KEY_RIGHT);

        food = new Food(snake);
        snake.setFood(food);
        poison = new Poison(snake);
        snake.setPoison(poison);

        while (!gameOver) {
            snake.move();
            if (snake.size() != snakeSize) {
                snakeSize = snake.size();
                gameSnakeFrame.setTitle(GameSnakeFrame.TITLE_OF_PROGRAM + ":" + snakeSize);
            }

            if (food.isEaten() || poison.isEaten()) {
                food.appear();
                poison.appear();
            }
            GameSnakeFrame.canvas.repaint();
            sleep(GameSnakeFrame.SNAKE_DELAY);
        }
        gameSnakeFrame.setTitle(GameSnakeFrame.GAME_OVER_MSG);
    }

    private void sleep(long ms) {    // method for suspending
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static Color getSnakeColor() {
        return SNAKE_COLOR;
    }

    public static Color getFoodColor() {
        return FOOD_COLOR;
    }

    public static Color getPoisonColor() {
        return POISON_COLOR;
    }

    public static void setGameOver(Boolean isGameOver) {
        gameOver = isGameOver;
    }
}
