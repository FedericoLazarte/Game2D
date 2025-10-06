package com.game.panel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import com.game.entities.Player;
import com.game.utils.KeyHandler;

public class GamePanel extends JPanel implements Runnable {
  // Screen settings
  final int originalTileSize = 16; // 16 x 16 tile

  final int scale = 3;

  public final int tileSize = originalTileSize * scale; // 48+48 tile
  final int maxScreenCol = 16;
  final int maxScreenRow = 12;
  final int screenWidth = tileSize * maxScreenCol; // 768 pixels
  final int screenHeight = tileSize * maxScreenRow; // 576 pixels

  int FPS = 60;

  KeyHandler keyH = new KeyHandler();
  Thread gameThread;

  Player player = new Player(this, keyH);

  public GamePanel() {
    this.setPreferredSize(new Dimension(screenWidth, screenHeight));
    this.setBackground(Color.BLACK);
    this.setDoubleBuffered(true);
    this.addKeyListener(keyH);
    this.setFocusable(true);
  }

  public void startGameThread() {
    gameThread = new Thread(this);
    gameThread.start();
  }

  /*
   * @Override
   * privateublic void run() {
   * double drawInterval = 1000000000 / FPS;
   * double nextDrawTime = System.nanoTime() + drawInterval;
   * 
   * while (gameThread != null) {
   * 
   * update();
   * 
   * repaint();
   * 
   * try {
   * double remainingTime = nextDrawTime - System.nanoTime();
   * remainingTime = remainingTime / 1000000;
   * if (remainingTime < 0) {
   * remainingTime = 0;
   * }
   * Thread.sleep((long) remainingTime);
   * nextDrawTime += drawInterval;
   * } catch (InterruptedException e) {
   * e.printStackTrace();
   * }
   * }
   * }
   */

  @Override
  public void run() {
    double drawInterval = 1000000000 / FPS;
    double delta = 0;
    long lastTime = System.nanoTime();
    long currentTime;
    while (gameThread != null) {
      currentTime = System.nanoTime();
      delta += (currentTime - lastTime) / drawInterval;
      lastTime = currentTime;
      if (delta >= 1) {
        update();
        repaint();
        delta--;
      }
    }
  }

  public void update() {
    System.out.println("Keys - Up: " + keyH.upPressed +
        ", Down: " + keyH.downPressed +
        ", Left: " + keyH.leftPressed +
        ", Right: " + keyH.rightPressed);
    player.update();
  }

  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D g2 = (Graphics2D) g;
    player.draw(g2);
    g2.dispose();
  }

}
