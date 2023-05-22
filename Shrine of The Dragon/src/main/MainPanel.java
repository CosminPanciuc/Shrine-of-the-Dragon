package main;

import entity.*;
import factory.MovableEntityFactory;
import factory.StaticEntityFactory;
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
    //public final int maxWorldCol = 32;
    //public final int maxWorldRow = 25;
    //public final int worldWidth = tileSize * maxWorldCol;
    //public final int worldHeight = tileSize * maxWorldRow;

    //FPS
    public SpriteSheet tileSheet = new SpriteSheet(this, ImageLoader.LoadImage("/tiles/48x48.png"));
    public SpriteSheet animalSheet = new SpriteSheet(this,ImageLoader.LoadImage("/animals/AnimalSheet.png"));
    public SpriteSheet hudSprite = new SpriteSheet(this, ImageLoader.LoadImage("/ui_elemets/hud_sprites.png"));
    int FPS = 60;
    KeyInput keyHandler = new KeyInput(this);

    MouseInput mouseInput = new MouseInput(this);
    Thread gameThread;
    public StaticEntityFactory staticEntityFactory = new StaticEntityFactory(this);
    public MovableEntityFactory movableEntityFactory = new MovableEntityFactory(this);
    public CollisionChecker collisionChecker = new CollisionChecker(this);

    public Player player = new Player(this,keyHandler, mouseInput);

    public LevelManager levelManager = new LevelManager(this);
    public DatabaseLoader defaultDatabase = new DatabaseLoader(this,"default");
    public DatabaseLoader savesDatabase = new DatabaseLoader(this,"saves");
    public InGameUI inGameUI = new InGameUI(this);
    public Menu menu = new Menu(this);

    public enum GameState{
        MENU, PLAY, INGAMEMENU
    }
    public GameState currentState = GameState.MENU;
    private MainPanel(){
        this.setPreferredSize(new Dimension(screenWidth,screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyHandler);
        this.addMouseListener(mouseInput);
        this.setFocusable(true);
    }

    public static MainPanel getInstance(){
        return instace;
    }
    public void loadFromDefaultDB(){
        //defaultDatabase.LoadFromDatabase();
        //defaultDatabase.closeDB();
    }
    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();
        //loadFromDefaultDB();
        //savesDatabase.SaveToDatabase();
        //savesDatabase.LoadFromDatabase();
        //System.out.println(levelManager.currentLevelID);
        //savesDatabase.closeDB();
    }

    @Override
    public void run() {
        double drawInterval = 1000000000/FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;

        while (gameThread != null){
            if(currentState == GameState.PLAY){
                currentTime = System.nanoTime();
                delta += (currentTime - lastTime) / drawInterval;

                lastTime = currentTime;
                if(delta >= 1){
                    update();

                    repaint();

                    --delta;
                }
            } else if(currentState == GameState.MENU){
                menu.update();
                repaint();
            } else if(currentState == GameState.INGAMEMENU){
                menu.InGameMenuUpdate();
                repaint();
            }
        }
    }

    public void update(){
        player.update();
        for(MovableEntity i:levelManager.levels.get(levelManager.currentLevelID).movableEntities){
            i.update();
        }
    }

    public void paintComponent(Graphics g) {

            super.paintComponent(g);

            Graphics2D g2 = (Graphics2D) g;
            if (currentState == GameState.PLAY){
            levelManager.levels.get(levelManager.currentLevelID).tileManager.draw(g2);


            for (StationaryEntity i : levelManager.levels.get(levelManager.currentLevelID).stationaryEntities) {
                i.draw(g2);
            }

            for (MovableEntity i : levelManager.levels.get(levelManager.currentLevelID).movableEntities) {
                i.draw(g2);
            }

            player.draw(g2);

            inGameUI.draw(g2);

            g2.dispose();
        } else if(currentState == GameState.MENU){
            menu.draw(g2);
        } else if(currentState == GameState.INGAMEMENU){
                menu.drawInGameMenu(g2);
            }
    }
}
