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

    BufferedImage goldSprite;
    BufferedImage meatSprite;
    BufferedImage skinSprite;
    BufferedImage selected = ImageLoader.LoadImage("/ui_elemets/Inventory_select.png");

    BufferedImage inventory;
    ArrayList<BufferedImage> items = new ArrayList<BufferedImage>();
    int selectedItem;

    public InGameUI(MainPanel mp){
        inventory = ImageLoader.LoadImage("/ui_elemets/inventory.png");
        SpriteSheet itemSheet = new SpriteSheet(mp,ImageLoader.LoadImage("/ui_elemets/Basic tools and meterials.png"));
        items.add(itemSheet.crop(1,0,16));
        items.add(itemSheet.crop(2,0,16));

        this.mp = mp;

        fullHearth = mp.hudSprite.crop(0,0);
        halfHearth = mp.hudSprite.crop(1,0);
        emptyHearth = mp.hudSprite.crop(2,0);
        fullHunger = mp.hudSprite.crop(0,1);
        halfHunger = mp.hudSprite.crop(1,1);
        emptyHunger = mp.hudSprite.crop(2,1);

        goldSprite = ImageLoader.LoadImage("/ui_elemets/gold.png");
        meatSprite = ImageLoader.LoadImage("/ui_elemets/meat.png");
        skinSprite = ImageLoader.LoadImage("/ui_elemets/skin.png");


        selectedItem = 1;
    }

    public void draw(Graphics2D g2){
        drawPlayerHealth(g2);
        drawPlayerHunger(g2);
        drawInventoryBar(g2);
        drawPlayerQuestItems(g2);
    }

    public void drawPlayerQuestItems(Graphics2D g2){
        Font fnt = new Font("arial", Font.BOLD, 15);
        g2.setFont(fnt);
        g2.setColor(Color.WHITE);
        g2.drawString(String.valueOf(mp.player.gold), 20, 100);
        g2.drawImage(goldSprite, 50,88, 16, 16, null);
        g2.drawString(String.valueOf(mp.player.meat), 20, 120);
        g2.drawImage(meatSprite, 50,108, 16, 16, null);
        g2.drawString(String.valueOf(mp.player.skin), 20, 140);
        g2.drawImage(skinSprite, 50,128, 16, 16, null);

        g2.drawString("Quest: ", 10, 168);
        switch (mp.quest){
            case 1->{
                g2.drawString("300", 70, 168);
                g2.drawImage(goldSprite, 100,155, 16, 16, null);
            }
            case 2->{
                g2.drawString("100", 70, 168);
                g2.drawImage(meatSprite, 100,155, 16, 16, null);
            }
            case 3->{
                g2.drawString("50", 70, 168);
                g2.drawImage(skinSprite, 100,155, 16, 16, null);
            }
        }

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

        if(mp.player.hunger <= 75){
            forthH = emptyHunger;
        } else if( mp.player.hunger <= 83){
            forthH = halfHunger;
        }

        if(mp.player.hunger <= 50){
            thirdH = emptyHunger;
        }
        else if(mp.player.hunger <= 62){
            thirdH = halfHunger;
        }

        if(mp.player.hunger <= 25){
            secondH = emptyHunger;
        }
        else if(mp.player.hunger <= 37){
            secondH = halfHunger;
        }

        if(mp.player.hunger <= 0){
            firstH = emptyHunger;
        }
        else if(mp.player.hunger <= 12){
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

        for(int i = 0; i < 10; ++i){
            g2.drawImage(inventory, drawPositionX + (i * 48), drawPositionY, mp.tileSize, mp.tileSize, null);
        }
        g2.drawImage(items.get(0), drawPositionX, drawPositionY, mp.tileSize, mp.tileSize, null);
        g2.drawImage(items.get(1), drawPositionX + 48, drawPositionY, mp.tileSize, mp.tileSize, null);
    }
}
