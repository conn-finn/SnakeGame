package SnakeGame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

import javax.swing.JPanel;

@SuppressWarnings("serial")
/**
 * contains the visual components of the game.
 *
 * @author Connor Finneran
 *
 */
public class RenderPanel extends JPanel {

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        SnakeGame snake = SnakeGame.snake;
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, 600, 600);
        g.setColor(Color.WHITE);
        for (Point point : snake.body) {
            g.fillRect(point.x * SnakeGame.SCALE, point.y * SnakeGame.SCALE,
                    SnakeGame.SCALE, SnakeGame.SCALE);
        }
        g.fillRect(snake.head.x * SnakeGame.SCALE,
                snake.head.y * SnakeGame.SCALE, SnakeGame.SCALE,
                SnakeGame.SCALE);
        g.setColor(Color.GRAY);
        g.fillRect(snake.food.x * SnakeGame.SCALE,
                snake.food.y * SnakeGame.SCALE, SnakeGame.SCALE,
                SnakeGame.SCALE);
        String score = "Score: " + snake.score + " | Length: " + snake.tail
                + " | Time: " + snake.time / 20;
        //time is snake.time / 20 since it increases every twentieth of a second
        g.setColor(Color.RED);
        g.drawString(score,
                Math.round(this.getWidth() / 2 - score.length() * 2.5f), 10);
        String gameOver = "Game Over!";
        if (snake.gameOver) {
            g.drawString(gameOver,
                    Math.round(this.getWidth() / 2 - gameOver.length() * 2.5f),
                    (int) snake.d.getHeight() / 4);
        }
        String pause = "~Paused~";
        if (snake.paused && !snake.gameOver) {
            g.drawString(pause,
                    Math.round(this.getWidth() / 2 - pause.length() * 2.5f),
                    (int) snake.d.getHeight() / 4);
        }
    }
}
