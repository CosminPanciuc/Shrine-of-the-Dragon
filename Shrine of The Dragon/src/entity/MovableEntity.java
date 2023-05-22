package entity;

import main.MainPanel;

import java.awt.*;
import java.awt.image.BufferedImage;

public class MovableEntity extends Entity{

    public int speed;
    public String direction;
    public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;

    public Rectangle actionArea = new Rectangle(0,0,0,0);
    public String actionDirection = "";
    public int spriteCounter = 0;
    public int spriteNumber = 1;
    public int healthPool;
    public int damage;
    @Override
    public void draw(Graphics2D g2){
        int screenX = worldX - mp.player.worldX + mp.player.screenX;
        int screenY = worldY - mp.player.worldY + mp.player.screenY;


        if(144 + worldX + mp.tileSize > mp.player.worldX - mp.player.screenX &&
                worldX - mp.tileSize - 144 < mp.player.worldX + mp.player.screenX &&
                144 + worldY + mp.tileSize > mp.player.worldY - mp.player.screenY &&
                worldY - mp.tileSize - 144 < mp.player.worldY + mp.player.screenY) {

                g2.drawImage(up1, screenX , screenY, null);
        }
    }
}



