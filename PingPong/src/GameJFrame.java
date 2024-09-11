package PingPong.src;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class GameJFrame extends JFrame
{
    GameJPanel panel;

    //Constructor
    GameJFrame()
    {
        panel = new GameJPanel();
        this.add(panel);
        this.setTitle("Ping Pong");
        this.setResizable(false);
        this.setBackground(Color.green);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }
}
