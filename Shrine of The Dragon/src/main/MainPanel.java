package main;

import entity.Player;
import tile.TileManager;

import javax.swing.*;
import javax.swing.plaf.basic.BasicTreeUI;
import java.awt.*;

public class MainPanel extends JPanel implements Runnable{
    final int ogTileSize = 16; // 16px square tile
    final int scale = 3; // used for rescale of tile
    public final int tileSize = ogTileSize * scale; // upscale to 48px square tile
    public final int screenTileColCount = 16;
    public final int screenTileRowCount = 12;
    final int screenWidth = tileSize * screenTileColCount;
    final int screenHeight = tileSize * screenTileRowCount;

    //WORLD
    public final int maxWorldCol = 32;
    public final int maxWorldRow = 25;
    public final int worldWidth = tileSize * maxWorldCol;
    public final int worldHeight = tileSize * maxWorldRow;


    //FPS
    int FPS = 60;
    KeyInput keyHandler = new KeyInput();
    Thread gameThread;
    public CollisionChecker collisionChecker = new CollisionChecker(this);

    public Player player = new Player(this,keyHandler);

    TileManager tileManager = new TileManager(this);
    public MainPanel(){
        this.setPreferredSize(new Dimension(screenWidth,screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyHandler);
        this.setFocusable(true);
    }

    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        double drawInterval = 1000000000/FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;

        while (gameThread != null){

            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;

            lastTime = currentTime;
            if(delta >= 1){
                update();

                repaint();

                --delta;
            }
        }
    }

    public void update(){
        player.update();
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D)g;

        tileManager.draw(g2);

        player.draw(g2);

        g2.dispose();

    }
}
