package main;

import graphics.ImageLoader;
import graphics.SpriteSheet;

import java.awt.*;
import java.awt.image.BufferedImage;

public class InGameUI {
    MainPanel mp;
    BufferedImage bottomInventory;

    public InGameUI(MainPanel mp){
        SpriteSheet spriteSheet = new SpriteSheet(mp, ImageLoader.LoadImage("/tiles/48x48.png"));
        this.mp = mp;
        bottomInventory = spriteSheet.crop(0,0);
    }

    public void draw(Graphics2D g2){
        g2.drawImage(bottomInventory, mp.tileSize/2, mp.tileSize/2, mp.tileSize, mp.tileSize, null);
    }
}
