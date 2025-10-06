package com.game;

import javax.swing.JFrame;

import com.game.panel.GamePanel;

/**
 * Hello world!
 *
 */
public class App {
  public static void main(String[] args) {
    JFrame window = new JFrame();
    window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    window.setResizable(false);
    window.setTitle("2D Adventure");

    GamePanel gamePanel = new GamePanel();
    window.add(gamePanel);
    window.pack();

    window.setLocationRelativeTo(null);
    window.setVisible(true);
  }
}
