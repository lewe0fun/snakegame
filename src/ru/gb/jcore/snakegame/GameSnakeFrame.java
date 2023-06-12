package ru.gb.jcore.snakegame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class GameSnakeFrame extends JFrame {
    static final String TITLE_OF_PROGRAM = "Classic Game Snake";
    static final String GAME_OVER_MSG = "GAME OVER";
    static final String WIN_MSG = "WIN";
    final static int CELL_SIZE = 20;           // size of cell in pix
    final static int CANVAS_WIDTH = 30;        // width in cells
    final static int CANVAS_HEIGHT = 25;       // height in cells

    final static int KEY_LEFT = 37;            // codes
    final static int KEY_UP = 38;              //   of
    final static int KEY_RIGHT = 39;           //   cursor
    final static int KEY_DOWN = 40;            //   keys

    static final int START_SNAKE_X = CANVAS_WIDTH / 2;  //   for
    static final int START_SNAKE_Y = CANVAS_HEIGHT / 2; //   snake
    static final int SNAKE_DELAY = 150;               // snake delay in milliseconds


    static Canvas canvas;                   // canvas object for rendering (drawing)

    public GameSnakeFrame() {
        setTitle(TITLE_OF_PROGRAM);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        canvas = new Canvas();
        canvas.setBackground(Color.WHITE);
        canvas.setPreferredSize(new Dimension(CELL_SIZE * CANVAS_WIDTH, CELL_SIZE * CANVAS_HEIGHT));
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                GameSnake.snake.setDirection(e.getKeyCode());
            }
        });

        add(canvas);
        pack();
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
    }


    class Canvas extends JPanel {    // class for rendering (drawing)
        @Override
        public void paint(Graphics g) {
            super.paint(g);
            Graphics2D g2D = (Graphics2D) g;
            g2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            GameSnake.snake.paint(g2D);
            GameSnake.food.paint(g2D);
            GameSnake.poison.paint(g2D);
        }
    }

    public static int getCellSize() {
        return CELL_SIZE;
    }

    public static int getCanvasWidth() {
        return CANVAS_WIDTH;
    }

    public static int getCanvasHeight() {
        return CANVAS_HEIGHT;
    }

    public static int getKeyUp() {
        return KEY_UP;
    }

    public static int getKeyDown() {
        return KEY_DOWN;
    }

    public static int getKeyLeft() {
        return KEY_LEFT;
    }

    public static int getKeyRight() {
        return KEY_RIGHT;
    }

}
