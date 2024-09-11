package PingPong.src;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class Ball extends Rectangle
{
    Random random;
    int xVelocity;
    int yVelocity;
    int inSpeed = 5;

    Ball(int x, int y, int width, int height)
    {
        super(x, y, width, height);
        random = new Random();

        // Ensure the ball always moves left or right
        int randomX = random.nextInt(2);
        if (randomX == 0) {
            randomX = -1; // Make sure it's either -1 or 1
        }
        setX(randomX * inSpeed);

        // Randomize the vertical direction but ensure no zero velocities for the ball
        int randomY = random.nextInt(2);
        if (randomY == 0) {
            randomY = -1;
        }
        setY(randomY * inSpeed);
    }  
    
    public void setX(int randomX)
    {
        xVelocity = randomX;
    }

    public void setY(int randomY)
    {
        yVelocity = randomY;
    }

    public void move()
    {
        x += xVelocity;
        y += yVelocity;
    }

    public void draw(Graphics g)
    {
        g.setColor(Color.WHITE);
        g.fillOval(x, y, width, height);
    }
}

