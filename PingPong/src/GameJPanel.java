package PingPong.src;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class GameJPanel extends JPanel implements Runnable
{
    static final int GAME_WIDTH = 1000;
    static final int GAME_HEIGHT = (int)(GAME_WIDTH * (0.5555));
    static final Dimension SCREEN_SIZE = new Dimension(GAME_WIDTH, GAME_HEIGHT);
    static final int BALL_DIAMETER = 20;
    static final int PADDLE_WIDTH = 25;
    static final int PADDLE_HEIGHT = 100;

    Thread gameThread;
    Image image;
    Graphics graphics;
    Random random;
    Paddles paddle1;
    Paddles paddle2;
    Ball ball;
    Score score; 

    GameJPanel()
    {
        newPaddles();
        newBall();
        score = new Score(GAME_WIDTH, GAME_HEIGHT);
        this.setFocusable(true);
        this.addKeyListener(new actionListener());
        this.setPreferredSize(SCREEN_SIZE);

        gameThread = new Thread(this);
        gameThread.start();
    }   

    public void newBall()
    {
        random = new Random();
        ball = new Ball((GAME_WIDTH / 2) - (BALL_DIAMETER / 2), random.nextInt(GAME_HEIGHT - BALL_DIAMETER), BALL_DIAMETER, BALL_DIAMETER);
    }

    public void newPaddles()
    {
        paddle1 = new Paddles(0, (GAME_HEIGHT / 2) - (PADDLE_HEIGHT / 2), PADDLE_WIDTH, PADDLE_HEIGHT, 1);
        paddle2 = new Paddles(GAME_WIDTH - PADDLE_WIDTH, (GAME_HEIGHT / 2) - (PADDLE_HEIGHT / 2), PADDLE_WIDTH, PADDLE_HEIGHT, 2);
    }

    public void paint(Graphics g)
    {
        image = createImage(getWidth(), getHeight());
        graphics = image.getGraphics();
        draw(graphics);
        g.drawImage(image, 0, 0, this);
    }

    public void draw(Graphics g)
    {
        paddle1.draw(g);
        paddle2.draw(g);
        ball.draw(g);
        score.draw(g);
    }

    public void move()
    {
        paddle1.move();
        paddle2.move();
        ball.move();
    }

    public void checkCollision() {
        // Ball collision with top and bottom walls
        if(ball.y <= 0) {
            ball.setY(-ball.yVelocity); // Reverse direction when hitting the top
        }
        
        if(ball.y >= (GAME_HEIGHT - BALL_DIAMETER)) {
            ball.setY(-ball.yVelocity); // Reverse direction when hitting the bottom
        }
    
        // Ball collision with paddle1
        if(ball.intersects(paddle1)) {
            ball.xVelocity = Math.abs(ball.xVelocity) + 1; // Ensure ball always moves right, increase speed
            if(ball.yVelocity > 0) {
                ball.yVelocity++; // Increase speed for difficulty
            } else {
                ball.yVelocity--;
            }
            ball.setX(ball.xVelocity);
            ball.setY(ball.yVelocity);
        }
    
        // Ball collision with paddle2
        if(ball.intersects(paddle2)) {
            ball.xVelocity = -Math.abs(ball.xVelocity) + 1; // Ensure ball always moves left, increase speed
            if(ball.yVelocity > 0) {
                ball.yVelocity++; // Increase speed for difficulty
            } else {
                ball.yVelocity--;
            }
            ball.setX(ball.xVelocity);
            ball.setY(ball.yVelocity);
        }
    
        // Scoring and resetting ball/paddles
        if(ball.x <= 0) { // Player 2 scores
            score.player2++;
            newPaddles();
            newBall();
            System.out.println("Player 2: " + score.player2);
        }
    
        if(ball.x >= (GAME_WIDTH - BALL_DIAMETER)) { // Player 1 scores
            score.player1++;
            newPaddles();
            newBall();
            System.out.println("Player 1: " + score.player1);
        }
    
        // Set boundaries for paddles
        if(paddle1.y <= 0) {
            paddle1.y = 0;
        }
        
        if(paddle1.y >= (GAME_HEIGHT - PADDLE_HEIGHT)) {
            paddle1.y = GAME_HEIGHT - PADDLE_HEIGHT;
        }
    
        if(paddle2.y <= 0) {
            paddle2.y = 0;
        }
        
        if(paddle2.y >= (GAME_HEIGHT - PADDLE_HEIGHT)) {
            paddle2.y = GAME_HEIGHT - PADDLE_HEIGHT;
        }
    }
    

    public void run()
    {
        //Game loop
        long lastTime = System.nanoTime();
        double Ticks = 60.0;
        double nanoSecs = 1000000000 / Ticks;
        double delta = 0;

        while(true)
        {
            long now = System.nanoTime();
            delta += (now - lastTime) / nanoSecs;
            lastTime = now;

            if(delta >= 1)
            {
                move();
                checkCollision();
                repaint();
                delta--;
            }
        }
    }

    public class actionListener extends KeyAdapter 
    {
        public void keyPressed(KeyEvent e)
        {
            paddle1.keyPressed(e);
            paddle2.keyPressed(e);
        }

        public void keyReleased(KeyEvent e)
        {
            paddle1.keyReleased(e);
            paddle2.keyReleased(e);
        }
    }
}
