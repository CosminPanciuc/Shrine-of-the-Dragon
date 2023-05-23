package entity;

import graphics.ImageLoader;
import graphics.SpriteSheet;
import main.MainPanel;

import java.awt.*;

public class NPC extends StationaryEntity{
    public NPC(MainPanel mp, int coordX, int coordY) {
        this.mp = mp;
        hitPoints = 1;
        worldX = coordX * mp.tileSize;
        worldY = coordY * mp.tileSize;
        hitBox = new Rectangle(9, 9, 48, 48);
        texture.add(ImageLoader.LoadImage("/NPC/NPC.png"));
        rowNumber = 1;
        colNumber = 1;
    }
}
