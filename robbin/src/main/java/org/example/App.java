package org.example;

import org.w3c.dom.ls.LSOutput;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;

public class App extends JPanel implements Runnable {

    final int originalTitleSize = 16;
    final int scale = 3;
    final int titleSize = originalTitleSize * scale;
    final int maxScreenCol = 16;
    final int maxScreenRow = 12;
    final int screenWidth = titleSize * maxScreenCol;
    final int screenHeight = titleSize * maxScreenRow;
    KeyHandler keyH = new KeyHandler();
     Thread gameThread;
    int playerX = 100, playerY = 100, playerSpeed = 4;
    int FPS = 60;


    public App(){
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.WHITE);
        this.setDoubleBuffered(true);
        this.setFocusable(true);
        this.addKeyListener(keyH);

    }
    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();

    }

    @Override
    public void run() {
        double drawinterval = 1000000000/FPS;
        double nextDrawTime = System.nanoTime() + drawinterval;
     while(gameThread != null){





         update();
         repaint();

         try {
             double remainingTime = nextDrawTime - System.nanoTime();
             remainingTime = remainingTime /1000000;
             if(remainingTime < 0){
                 remainingTime = 0;

             }
             Thread.sleep((long) remainingTime);
             nextDrawTime += drawinterval;

         } catch (InterruptedException e) {
             e.printStackTrace();
         }
     }
    }

    public void update(){
        if(keyH.upPressed == true){
            playerY -= playerSpeed;
        }
        else if(keyH.downPressed == true){
            playerY += playerSpeed;
        }
        else if(keyH.leftPressed == true){
            playerX -= playerSpeed;
        }
        else if(keyH.rightPressed == true){
            playerX += playerSpeed;
        }

    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.BLACK);

        g2.fillRect(playerX, playerY, titleSize, titleSize);

        g2.dispose();
    }

}

