package PingPong.src;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class Paddles extends Rectangle
{
    int id;
    int yVelocity;
    int speed = 10;

    Paddles(int x, int y, int PADDLE_WIDTH, int PADDLE_HEIGHT, int id)
    {
        super(x, y, PADDLE_WIDTH, PADDLE_HEIGHT);
        this.id = id;
    }    

    public void keyPressed(KeyEvent e)
    {
        switch (id) 
        {
            case 1:
                if(e.getKeyCode() == KeyEvent.VK_W)
                {
                    setY(-speed);
                    move();
                }

                if(e.getKeyCode() == KeyEvent.VK_S)
                {
                    setY(speed);
                    move();
                }
                break;
            
            case 2:
                if(e.getKeyCode() == KeyEvent.VK_I)
                {
                    setY(-speed);
                    move();
                }

                if(e.getKeyCode() == KeyEvent.VK_J)
                {
                    setY(speed);
                    move();
                }
                break;
        }
    }

    public void keyReleased(KeyEvent e)
    {
        switch (id) 
        {
            case 1:
                if(e.getKeyCode() == KeyEvent.VK_W)
                {
                    setY(0);
                    move();
                }

                if(e.getKeyCode() == KeyEvent.VK_S)
                {
                    setY(0);
                    move();
                }
                break;
            
            case 2:
                if(e.getKeyCode() == KeyEvent.VK_I)
                {
                    setY(0);
                    move();
                }

                if(e.getKeyCode() == KeyEvent.VK_J)
                {
                    setY(0);
                    move();
                }
                break;
        }
    }

    public void setY(int y)
    {
        yVelocity = y;
    }

    public void move()
    {
        y = y + yVelocity;
    }

    public void draw(Graphics g)
    {
        //Player 1
        if(id == 1)
        {
            g.setColor(Color.red);
            g.fillRect(x, y, width, height);
        }
        //Player 2
        else
        {
            g.setColor(Color.BLUE);
            g.fillRect(x, y, width, height);
        }
    }
}
