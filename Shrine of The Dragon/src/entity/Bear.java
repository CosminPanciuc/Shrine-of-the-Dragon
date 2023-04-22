package entity;

import main.MainPanel;

import java.awt.*;
import java.util.Random;

public class Bear extends MovableEntity{
    public Bear(MainPanel mp, int cordX, int cordY){
        speed = 3;
        this.mp = mp;
        hitBox = new Rectangle(0,0,48,48);
        up1 = mp.animalSheet.crop(0,19,16);
        up1 = mp.animalSheet.scaleImage(up1,48,48);
        up2 = mp.animalSheet.crop(0,1,16);
        up2 = mp.animalSheet.scaleImage(up2,48,48);
        worldX = cordX * mp.tileSize;
        worldY = cordY * mp.tileSize;
        direction = "up";
        healthPool = 20;
    }

    public void randomMovement(){
        Random random = new Random();
        float randomNumber = random.nextFloat(1);
        if( randomNumber < 0.5){
            if(randomNumber < 0.25)
                worldX += speed;
            else worldX -=speed;
        }
        else {
            if(randomNumber > 0.75)
                worldY += speed;
            else worldY -=speed;
        }
    }
    @Override
    public void update(){
        randomMovement();
        collision = false;
        mp.collisionChecker.checkTile(this);
        mp.collisionChecker.checkEntity(this);
    }
}
