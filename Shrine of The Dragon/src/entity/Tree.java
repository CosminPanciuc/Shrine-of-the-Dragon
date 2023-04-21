package entity;

import graphics.ImageLoader;
import graphics.SpriteSheet;
import main.MainPanel;

import java.awt.*;

public class Tree extends StationaryEntity{
    public Tree(MainPanel mp, String type, int cordX, int cordY){
        SpriteSheet spriteSheet = new SpriteSheet(mp, ImageLoader.LoadImage("/tiles/48x48.png"));
        switch(type) {
            case "Small" ->{
                hitBox = new Rectangle(9, 9, 30, 30);

            }
            case "Big" ->{
                hitBox = new Rectangle(48,110, 50,40);
                texture.add(spriteSheet.crop(0,5));
                texture.add(spriteSheet.crop(0,6));
                texture.add(spriteSheet.crop(0,7));
                texture.add(spriteSheet.crop(0,8));

                texture.add(spriteSheet.crop(1,5));
                texture.add(spriteSheet.crop(1,6));
                texture.add(spriteSheet.crop(1,7));
                texture.add(spriteSheet.crop(1,8));

                texture.add(spriteSheet.crop(2,5));
                texture.add(spriteSheet.crop(2,6));
                texture.add(spriteSheet.crop(2,7));
                texture.add(spriteSheet.crop(2,8));


                rowNumber = 4;
                colNumber = 4;

            }
        }
        this.mp = mp;
        worldX = cordX * mp.tileSize;
        worldY = cordY * mp.tileSize;
    }
}
