package entity;

import graphics.ImageLoader;
import graphics.SpriteSheet;
import main.KeyInput;
import main.MainPanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Player extends Entity {
    MainPanel mp;
    KeyInput key;

    public final int screenX;
    public final int screenY;

    public Player(MainPanel mp, KeyInput key){
        this.mp = mp;
        this.key = key;

        screenX = (mp.screenTileColCount * mp.tileSize)/2 - (mp.tileSize/2);
        screenY = (mp.screenTileRowCount * mp.tileSize)/2 - (mp.tileSize/2);

        hitBox = new Rectangle(9,9,30,30);

        setDefault();
        getPlayerImage();
    }

    public void setDefault(){
        worldX = mp.tileSize * 15;
        worldY = mp.tileSize * 15;
        speed = 3;
        direction = "down";
    }
    public void getPlayerImage(){
        SpriteSheet sheet = new SpriteSheet(mp, ImageLoader.LoadImage("/player/BasicCharacterSpritesheet.png"));
            up1 = sheet.crop(0,1);
            up2 = sheet.crop(1,1);
            down1 = sheet.crop(0,0);
            down2 = sheet.crop(1,0);
            left1 = sheet.crop(0,2);
            left2 = sheet.crop(1,2);
            right1 = sheet.crop(0,3);
            right2 = sheet.crop(1,3);
    }
    public void update(){
        if(key.wPressed || key.sPressed || key.aPressed || key.dPressed){
            if(key.wPressed){
                direction = "up";
            }
            if(key.sPressed){
                direction = "down";
            }
            if(key.aPressed){
                direction = "left";
            }
            if(key.dPressed){
                direction = "right";
            }
            collision = false;
            mp.collisionChecker.checkTile(this);

            if(!collision){
                switch (direction) {
                    case "up" -> {
                        worldY -= speed;
                    }
                    case "down" -> {
                        worldY += speed;
                    }
                    case "left" -> {
                        worldX -= speed;
                    }
                    case "right" -> {
                        worldX += speed;
                    }
                }

            }
            spriteCounter++;
            if(spriteCounter > 15){
                if(spriteNumber == 1){
                    spriteNumber = 2;
                }
                else if(spriteNumber == 2){
                    spriteNumber = 1;
                }
                spriteCounter = 0;
            }
        }



    }
    public void draw(Graphics2D playerGraphics){
        //TO FIX 1/2 px diff in first column char sprites
        BufferedImage image = null;
        switch (direction) {
            case "up" -> {
                if (spriteNumber == 1) {
                    image = up1;
                }
                if (spriteNumber == 2) {
                    image = up2;
                }
            }
            case "down" -> {
                if (spriteNumber == 1) {
                    image = down1;
                }
                if (spriteNumber == 2) {
                    image = down2;
                }
            }
            case "left" -> {
                if (spriteNumber == 1) {
                    image = left1;
                }
                if (spriteNumber == 2) {
                    image = left2;
                }
            }
            case "right" -> {
                if (spriteNumber == 1) {
                    image = right1;
                }
                if (spriteNumber == 2) {
                    image = right2;
                }
            }
        }
        ;
        playerGraphics.drawImage(image, screenX, screenY, mp.tileSize, mp.tileSize, null);
    }
}
