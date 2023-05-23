package entity;

import graphics.ImageLoader;
import graphics.SpriteSheet;
import main.MainPanel;
import tile.Tile;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Objects;

public class Rock extends StationaryEntity{

    public Rock(MainPanel mp, String type, int cordX, int cordY, int hp){
        size = type;
        switch (type){
            case "Small" -> {
                hitBox = new Rectangle(9,9,30,30);
                texture.add(mp.tileSheet.crop(5,0));
                rowNumber = 1;
                colNumber = 1;
                hitPoints = 3;
            }
            case "Big" -> {
                hitBox = new Rectangle(14,24, 70,46);
                texture.add(mp.tileSheet.crop(6,0));
                texture.add(mp.tileSheet.crop(6,1));
                texture.add(mp.tileSheet.crop(7,0));
                texture.add(mp.tileSheet.crop(7,1));


                rowNumber = 2;
                colNumber = 2;
                hitPoints = hp;
            }
            case "BigMossy"->{
                hitBox = new Rectangle(14,24, 70,46);
                texture.add(mp.tileSheet.crop(6,2));
                texture.add(mp.tileSheet.crop(6,3));
                texture.add(mp.tileSheet.crop(7,2));
                texture.add(mp.tileSheet.crop(7,3));
                rowNumber = 2;
                colNumber = 2;
                hitPoints = hp;
            }
        }
        this.mp = mp;
        worldX = cordX * mp.tileSize;
        worldY = cordY * mp.tileSize;
    }

}
