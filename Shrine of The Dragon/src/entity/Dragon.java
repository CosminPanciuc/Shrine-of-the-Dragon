package entity;

import graphics.ImageLoader;
import graphics.SpriteSheet;
import main.MainPanel;

import java.awt.*;

public class Dragon extends StationaryEntity {

    public Dragon(MainPanel mp, int coordX, int coordY, int hp) {
        this.mp = mp;
        hitPoints = 1;
        worldX = coordX * mp.tileSize;
        worldY = coordY * mp.tileSize;
        hitBox = new Rectangle(9, 9, 48, 48);
        SpriteSheet dragonTexture = new SpriteSheet(mp, ImageLoader.LoadImage("/animals/Free 4x.png"));
        texture.add(dragonTexture.scaleImage(dragonTexture.crop(1, 0, 64), 48, 48));


        rowNumber = 1;
        colNumber = 1;
    }
}
