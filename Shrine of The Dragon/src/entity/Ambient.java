package entity;

import main.MainPanel;

import java.awt.*;

public class Ambient extends StationaryEntity{
    public Ambient(MainPanel mp, String type, int cordX, int cordY, int hp){
        this.mp = mp;
        worldX = cordX * mp.tileSize;
        worldY = cordY * mp.tileSize;
        size = type;
        switch (type){
            case "Tent"->{
                hitBox = new Rectangle(9,50,110,100);
                hitPoints = hp;
                texture.add(mp.tileSheet.crop(6,6));
                texture.add(mp.tileSheet.crop(6,7));
                texture.add(mp.tileSheet.crop(6,8));
                texture.add(mp.tileSheet.crop(6,9));

                texture.add(mp.tileSheet.crop(7,6));
                texture.add(mp.tileSheet.crop(7,7));
                texture.add(mp.tileSheet.crop(7,8));
                texture.add(mp.tileSheet.crop(7,9));

                texture.add(mp.tileSheet.crop(8,6));
                texture.add(mp.tileSheet.crop(8,7));
                texture.add(mp.tileSheet.crop(8,8));
                texture.add(mp.tileSheet.crop(8,9));


                rowNumber = 3;
                colNumber = 4;
            }
        }
    }
}
