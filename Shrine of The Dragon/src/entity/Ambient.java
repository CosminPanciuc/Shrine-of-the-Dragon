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
            case "Campfire"->{
                hitBox = new Rectangle(9,9,30,30);
                hitPoints = hp;
                texture.add(mp.tileSheet.crop(11, 5));
                rowNumber = 1;
                colNumber = 1;
            }
            case "Bush"->{
                hitBox = new Rectangle(9,9,30,30);
                hitPoints = hp;
                texture.add(mp.tileSheet.crop(9, 1));
                rowNumber = 1;
                colNumber = 1;
            }
            case "Sunflower"->{
                hitBox = new Rectangle(9,9,30,30);
                hitPoints = hp;
                texture.add(mp.tileSheet.crop(13, 0));
                texture.add(mp.tileSheet.crop(13, 1));
                rowNumber = 2;
                colNumber = 1;
            }
            case "Deadbush"->{
                hitBox = new Rectangle(9,9,30,30);
                hitPoints = hp;
                texture.add(mp.tileSheet.crop(10, 0));
                rowNumber = 1;
                colNumber = 1;
            }
            case "Lilipad"->{
                hitBox = new Rectangle(9,9,30,30);
                hitPoints = hp;
                texture.add(mp.tileSheet.crop(11, 1));
                rowNumber = 1;
                colNumber = 1;
            }
            case "Flower"->{
                hitBox = new Rectangle(9,9,30,30);
                hitPoints = hp;
                texture.add(mp.tileSheet.crop(12, 1));
                rowNumber = 1;
                colNumber = 1;
            }
            case "Tallgrass"->{
                hitBox = new Rectangle(48,48,30,30);
                hitPoints = hp;
                texture.add(mp.tileSheet.crop(10, 1));
                texture.add(mp.tileSheet.crop(10, 2));
                rowNumber = 1;
                colNumber = 2;
            }
        }
    }
}
