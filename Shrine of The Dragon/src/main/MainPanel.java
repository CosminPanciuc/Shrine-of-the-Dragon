package main;

import entity.*;
import graphics.ImageLoader;
import graphics.SpriteSheet;
import tile.TileManager;

import javax.swing.*;
import javax.swing.plaf.basic.BasicTreeUI;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;


public class MainPanel extends JPanel implements Runnable{
    public static MainPanel instace = new MainPanel();
    final int ogTileSize = 16; // 16px square tile
    final int scale = 3; // used for rescale of tile
    public final int tileSize = ogTileSize * scale; // upscale to 48px square tile
    public final int screenTileColCount = 16;
    public final int screenTileRowCount = 12;
    final public int screenWidth = tileSize * screenTileColCount;
    final public int screenHeight = tileSize * screenTileRowCount;

    //WORLD
    public final int maxWorldCol = 32;
    public final int maxWorldRow = 25;
    public final int worldWidth = tileSize * maxWorldCol;
    public final int worldHeight = tileSize * maxWorldRow;


    //FPS
    public SpriteSheet tileSheet = new SpriteSheet(this, ImageLoader.LoadImage("/tiles/48x48.png"));
    public SpriteSheet animalSheet = new SpriteSheet(this,ImageLoader.LoadImage("/animals/AnimalSheet.png"));
    int FPS = 60;
    KeyInput keyHandler = new KeyInput();

    MouseInput mouseInput = new MouseInput();
    Thread gameThread;
    public CollisionChecker collisionChecker = new CollisionChecker(this);

    public Player player = new Player(this,keyHandler, mouseInput);

    TileManager tileManager = new TileManager(this);

    public ArrayList<StationaryEntity> stationaryEntities = new ArrayList<StationaryEntity>();
    public ArrayList<MovableEntity> movableEntities = new ArrayList<>();

    private MainPanel(){
        this.setPreferredSize(new Dimension(screenWidth,screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyHandler);
        this.addMouseListener(mouseInput);
        this.setFocusable(true);
        initEntity();
    }

    public static MainPanel getInstance(){
        return instace;
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
        /*
        for(MovableEntity i:movableEntities){
            i.update();
        }
         */
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D)g;

        tileManager.draw(g2);


        for(StationaryEntity i: stationaryEntities){
            i.draw(g2);
        }

        for(MovableEntity i:movableEntities){
            i.draw(g2);
        }

        player.draw(g2);

        g2.dispose();
    }

    public void initEntity(){
        stationaryEntities.add(new Rock(this, "Small", 14, 16));
        stationaryEntities.add(new Rock(this, "Small", 11, 20));
        stationaryEntities.add(new Rock(this, "Big", 19, 10));
        stationaryEntities.add(new Rock(this, "Big", 25, 3));
        stationaryEntities.add(new Tree(this, "Big", 4, 11));
        stationaryEntities.add(new Tree(this, "Big", 5, 16));
        stationaryEntities.add(new Tree(this, "Big", 27, 11));
        stationaryEntities.add(new Tree(this, "Big", 25, 16));
        stationaryEntities.add(new Tree(this, "Big", 22, 9));
        stationaryEntities.add(new Tree(this, "Big", 10, 10));

        movableEntities.add(new Bear(this,9,5));
        movableEntities.add(new Bear(this,21,4));
    }
}
