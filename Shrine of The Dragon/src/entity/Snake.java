package entity;

import main.MainPanel;

import java.awt.*;

public class Snake extends MovableEntity{
    public Snake(MainPanel mp, int cordX, int cordY, int hp, int speed, int damage){
        this.speed = speed;
        this.mp = mp;
        hitBox = new Rectangle(0,0,48,48);
        up1 = mp.animalSheet.crop(0,1,16);
        up1 = mp.animalSheet.scaleImage(up1,48,48);
        up2 = mp.animalSheet.crop(0,1,16);
        up2 = mp.animalSheet.scaleImage(up2,48,48);
        worldX = cordX * mp.tileSize;
        worldY = cordY * mp.tileSize;
        direction = "up";
        healthPool = hp;
        this.damage = damage;
        attackCooldown = 0;
    }
    public void movement(){
        if(Math.abs(mp.player.worldX - worldX) < 250 || Math.abs(mp.player.worldY - worldY) < 250) {
            if (mp.player.worldX > worldX) {
                direction = "right";
            }
            if (mp.player.worldX < worldX) {
                direction = "left";
            } else if (mp.player.worldY > worldY) {
                direction = "down";
            } else if (mp.player.worldY < worldY) {
                direction = "up";
            } else if (mp.player.worldY == worldY && mp.player.worldX == worldX) {
                direction = "stay";
            }

            collision = false;
            mp.collisionChecker.checkTile(this);
            mp.collisionChecker.checkEntity(this);
            mp.collisionChecker.checkMobPlayer(this);
            if (!collision) {
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
        }
    }
    @Override
    public void update(){
        movement();
        if(attackCooldown > 0){
            attackCooldown -= 1;
        }

    }
}
