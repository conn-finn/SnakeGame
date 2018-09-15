package SnakeGame;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.Timer;

/**
 *
 * The Snake Game.
 *
 * @author Connor Finneran
 */

public class SnakeGame implements ActionListener, KeyListener {

    public static SnakeGame snake;
    public JFrame jFrame;
    public RenderPanel renderPanel;
    public Timer timer = new Timer(20, this);
    public ArrayList<Point> body = new ArrayList<Point>();
    public int ticks = 0, direction = DOWN, score = 0, tail = 5, time;
    public Point head, food;
    public static final int UP = 0, RIGHT = 1, DOWN = 2, LEFT = 3, SCALE = 20,
            WINDOW_DIM = 600, WIDTHCORRECTION = 1, HEIGHTCORRECTION = 2;
    public Random ran;
    public Dimension d;
    public boolean gameOver = false, paused = false;

    /**
     * creates window
     */
    public SnakeGame() {
        this.d = Toolkit.getDefaultToolkit().getScreenSize();
        this.jFrame = new JFrame("Snake");
        this.jFrame.setVisible(true);
        this.jFrame.setSize(WINDOW_DIM, WINDOW_DIM);
        this.jFrame.setResizable(false);
        this.jFrame.setLocation(
                (this.d.width / 2) - (this.jFrame.getWidth() / 2),
                (this.d.height / 2) - (this.jFrame.getHeight() / 2));

        this.jFrame.add(this.renderPanel = new RenderPanel());
        this.jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.jFrame.addKeyListener(this);
        this.startGame();
    }

    /**
     * Set everything to it's default value when a new game is started
     */
    public void startGame() {
        this.gameOver = false;
        this.paused = false;
        this.score = 0;
        this.time = 0;
        this.ticks = 0;
        this.tail = 5;
        //starting direction down, from the top left corner
        this.direction = DOWN;
        this.head = new Point(0, -1);
        this.ran = new Random();
        this.body.clear();
        this.food = new Point(
                this.ran.nextInt((WINDOW_DIM / SCALE) - WIDTHCORRECTION),
                this.ran.nextInt((WINDOW_DIM / SCALE) - HEIGHTCORRECTION));
        this.timer.start();
    }

    @Override
    public void actionPerformed(ActionEvent arg0) {

        this.renderPanel.repaint();
        this.ticks++;

        //the smaller the number, the more often it will be updated
        if (this.ticks % 5 == 0 && this.head != null && !this.gameOver
                && !this.paused) {

            this.time += 2;
            this.body.add(new Point(this.head.x, this.head.y));

            if (this.direction == UP) {
                if (this.head.y - 1 >= 0
                        && this.notTouchingTail(this.head.x, this.head.y - 1)) {
                    this.head = new Point(this.head.x, this.head.y - 1);
                } else {
                    this.gameOver = true;
                }
            }
            if (this.direction == RIGHT) {
                if (this.head.x + 1 < (WINDOW_DIM / SCALE) - WIDTHCORRECTION
                        && this.notTouchingTail(this.head.x + 1, this.head.y)) {
                    this.head = new Point(this.head.x + 1, this.head.y);
                } else {
                    this.gameOver = true;
                }
            }
            if (this.direction == DOWN) {
                if (this.head.y + 1 < (WINDOW_DIM / SCALE) - HEIGHTCORRECTION
                        && this.notTouchingTail(this.head.x, this.head.y + 1)) {
                    this.head = new Point(this.head.x, this.head.y + 1);
                } else {
                    this.gameOver = true;
                }
            }
            if (this.direction == LEFT) {
                if (this.head.x - 1 >= 0
                        && this.notTouchingTail(this.head.x - 1, this.head.y)) {
                    this.head = new Point(this.head.x - 1, this.head.y);
                } else {
                    this.gameOver = true;
                }

            }
            if (this.body.size() > this.tail) {
                this.body.remove(0);
            }

            if (this.food != null) {
                if (this.head.equals(this.food)) {
                    this.score += 100;
                    this.tail++;
                    this.food.setLocation(
                            this.ran.nextInt(
                                    (WINDOW_DIM / SCALE) - WIDTHCORRECTION),
                            this.ran.nextInt(
                                    (WINDOW_DIM / SCALE) - HEIGHTCORRECTION));
                }
            }
        }

    }

    public boolean notTouchingTail(int x, int y) {
        boolean test = true;
        for (Point point : this.body) {
            if (point.equals(new Point(x, y))) {
                test = false;
            }
        }
        return test;
    }

    public static void main(String args[]) {
        snake = new SnakeGame();
    }

    @Override
    public void keyPressed(KeyEvent e) {

        int i = e.getKeyCode();

        //works for either WASD or arrows

        if (i == KeyEvent.VK_A || i == KeyEvent.VK_LEFT) {
            this.direction = LEFT;
        } else if (i == KeyEvent.VK_D || i == KeyEvent.VK_RIGHT) {
            this.direction = RIGHT;
        }
        if (i == KeyEvent.VK_W || i == KeyEvent.VK_UP) {
            this.direction = UP;
        } else if (i == KeyEvent.VK_S || i == KeyEvent.VK_DOWN) {
            this.direction = DOWN;
        }
        if (i == KeyEvent.VK_SPACE) {
            if (this.gameOver) {
                this.startGame();
            } else {
                //if game is paused, unpause it and visa versa
                this.paused = !this.paused;
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent arg0) {
        //do not need this method

    }

    @Override
    public void keyTyped(KeyEvent arg0) {
        // do not need this method

    }

}
