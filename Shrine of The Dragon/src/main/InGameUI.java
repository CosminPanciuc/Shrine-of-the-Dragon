package main;

import graphics.ImageLoader;
import graphics.SpriteSheet;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class InGameUI {
    MainPanel mp;
    BufferedImage fullHearth;
    BufferedImage halfHearth;
    BufferedImage emptyHearth;
    BufferedImage fullHunger;
    BufferedImage halfHunger;
    BufferedImage emptyHunger;

    BufferedImage selected = ImageLoader.LoadImage("/ui_elemets/Inventory_select.png");

    SpriteSheet inventoryBar;

    int selectedItem;
    public ArrayList<BufferedImage> inventory = new ArrayList<>();

    public InGameUI(MainPanel mp){
        SpriteSheet inventoryBar = new SpriteSheet(mp, ImageLoader.LoadImage("/ui_elemets/Inventory_Bar.png"));
        for(int i = 0; i < 10; ++i){
            inventory.add(inventoryBar.crop(i,0));
        }
        this.mp = mp;

        fullHearth = mp.hudSprite.crop(0,0);
        halfHearth = mp.hudSprite.crop(1,0);
        emptyHearth = mp.hudSprite.crop(2,0);
        fullHunger = mp.hudSprite.crop(0,1);
        halfHunger = mp.hudSprite.crop(1,1);
        emptyHunger = mp.hudSprite.crop(2,1);


        selectedItem = 1;
    }

    public void draw(Graphics2D g2){
        drawPlayerHealth(g2);
        drawPlayerHunger(g2);
        drawInventoryBar(g2);
    }

    public void drawPlayerHealth(Graphics2D g2){
        BufferedImage firstHearth,secondHearth,thirdHearth,forthHearth;
        firstHearth = fullHearth;
        secondHearth = fullHearth;
        thirdHearth = fullHearth;
        forthHearth = fullHearth;
        int drawPosition = mp.tileSize/2;

        if(mp.player.healthPool <= 75){
            forthHearth = emptyHearth;
        } else if( mp.player.healthPool <= 83){
            forthHearth = halfHearth;
        }

        if(mp.player.healthPool <= 50){
            thirdHearth = emptyHearth;
        }
        else if(mp.player.healthPool <= 62){
            thirdHearth = halfHearth;
        }

        if(mp.player.healthPool <= 25){
            secondHearth = emptyHearth;
        }
        else if(mp.player.healthPool <= 37){
            secondHearth = halfHearth;
        }

        if(mp.player.healthPool <= 0){
            firstHearth = emptyHearth;
        }
        else if(mp.player.healthPool <= 12){
            firstHearth = halfHearth;
        }

        g2.drawImage(firstHearth, drawPosition, drawPosition, mp.tileSize, mp.tileSize, null);
        g2.drawImage(secondHearth, drawPosition + 52, drawPosition, mp.tileSize, mp.tileSize, null);
        g2.drawImage(thirdHearth, drawPosition + 104, drawPosition, mp.tileSize, mp.tileSize, null);
        g2.drawImage(forthHearth, drawPosition + 156, drawPosition, mp.tileSize, mp.tileSize, null);
    }

    public void drawPlayerHunger(Graphics2D g2){
        BufferedImage firstH, secondH, thirdH, forthH;
        firstH = fullHunger;
        secondH = fullHunger;
        thirdH = fullHunger;
        forthH = fullHunger;
        int drawPositionX = 14 * mp.tileSize + mp.tileSize/2;
        int drawPositionY = mp.tileSize/2;

        if(mp.player.healthPool <= 75){
            forthH = emptyHunger;
        } else if( mp.player.healthPool <= 83){
            forthH = halfHunger;
        }

        if(mp.player.healthPool <= 50){
            thirdH = emptyHunger;
        }
        else if(mp.player.healthPool <= 62){
            thirdH = halfHunger;
        }

        if(mp.player.healthPool <= 25){
            secondH = emptyHunger;
        }
        else if(mp.player.healthPool <= 37){
            secondH = halfHunger;
        }

        if(mp.player.healthPool <= 0){
            firstH = emptyHunger;
        }
        else if(mp.player.healthPool <= 12){
            firstH = halfHunger;
        }

        g2.drawImage(firstH, drawPositionX, drawPositionY, mp.tileSize, mp.tileSize, null);
        g2.drawImage(secondH, drawPositionX - 52, drawPositionY, mp.tileSize, mp.tileSize, null);
        g2.drawImage(thirdH, drawPositionX - 104, drawPositionY, mp.tileSize, mp.tileSize, null);
        g2.drawImage(forthH, drawPositionX - 156, drawPositionY, mp.tileSize, mp.tileSize, null);
    }

    public void drawInventoryBar(Graphics2D g2){
        int drawPositionX = mp.tileSize * 3;
        int drawPositionY = mp.tileSize * 11;

        for(int i = 0; i < inventory.size(); ++i){
            g2.drawImage(inventory.get(i), drawPositionX + (i * 48), drawPositionY, mp.tileSize, mp.tileSize, null);
        }
    }
}
