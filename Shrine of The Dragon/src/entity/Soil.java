package entity;

import graphics.ImageLoader;
import graphics.SpriteSheet;
import main.MainPanel;

import java.awt.*;

public class Soil extends StationaryEntity{
    public Soil(MainPanel mp, int cordX, int cordY){
        this.mp = mp;

        texture.add(mp.tileSheet.crop(14,0));
        hitBox = new Rectangle(0,0,0,0);
        hitPoints = 1;
        worldX = cordX * mp.tileSize;
        worldY = cordY * mp.tileSize;
    }
}
