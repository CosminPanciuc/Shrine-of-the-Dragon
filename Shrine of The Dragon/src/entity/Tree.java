package entity;

import graphics.ImageLoader;
import graphics.SpriteSheet;
import main.MainPanel;

import java.awt.*;

public class Tree extends StationaryEntity{
    public Tree(MainPanel mp, String type, int cordX, int cordY, int hp){
        size = type;
        switch(type) {
            case "Small" ->{
                hitBox = new Rectangle(9, 9, 30, 30);
                hitPoints = 3;
            }
            case "Big" ->{
                hitBox = new Rectangle(48,110, 50,40);
                texture.add(mp.tileSheet.crop(0,5));
                texture.add(mp.tileSheet.crop(0,6));
                texture.add(mp.tileSheet.crop(0,7));
                texture.add(mp.tileSheet.crop(0,8));

                texture.add(mp.tileSheet.crop(1,5));
                texture.add(mp.tileSheet.crop(1,6));
                texture.add(mp.tileSheet.crop(1,7));
                texture.add(mp.tileSheet.crop(1,8));

                texture.add(mp.tileSheet.crop(2,5));
                texture.add(mp.tileSheet.crop(2,6));
                texture.add(mp.tileSheet.crop(2,7));
                texture.add(mp.tileSheet.crop(2,8));


                rowNumber = 3;
                colNumber = 4;

                hitPoints = hp;
            }
        }
        this.mp = mp;
        worldX = cordX * mp.tileSize;
        worldY = cordY * mp.tileSize;
    }
}
