package entity;

import graphics.ImageLoader;
import graphics.ImageScaleUtil;
import graphics.SpriteSheet;
import main.KeyInput;
import main.MainPanel;
import main.MouseInput;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Player extends MovableEntity{
    MainPanel mp;
    KeyInput key;
    MouseInput mouse;
    public final int screenX;
    public final int screenY;
    String selectedTool = "Pickaxe";
    public String actionDirection = "";

    public int axeQuality = 1;
    public int pickaxeQuality = 1;

    public ArrayList<BufferedImage> actionImage = new ArrayList<>();

    public Player(MainPanel mp, KeyInput key, MouseInput mouse){
        this.mp = mp;
        this.key = key;
        this.mouse = mouse;

        screenX = (mp.screenTileColCount * mp.tileSize)/2 - (mp.tileSize/2);
        screenY = (mp.screenTileRowCount * mp.tileSize)/2 - (mp.tileSize/2);

        hitBox = new Rectangle(9,9,30,30);
        actionArea.width = 30;
        actionArea.height = 30;

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
        ImageScaleUtil util = new ImageScaleUtil();
        SpriteSheet sheet = new SpriteSheet(mp, ImageLoader.LoadImage("/player/BasicCharacterSpritesheet.png"));
        up1 = sheet.crop(0,1);
        up2 = sheet.crop(1,1);
        down1 = sheet.crop(0,0);
        down2 = sheet.crop(1,0);
        left1 = sheet.crop(0,2);
        left2 = sheet.crop(1,2);
        right1 = sheet.crop(0,3);
        right2 = sheet.crop(1,3);
        SpriteSheet sheet1 = new SpriteSheet(mp, ImageLoader.LoadImage("/player/Basic Charakter Actions.png"));
        //TODO fix image scaling draw position
        for(int i=0; i < 12; ++i)
            for(int j = 0; j < 2; ++j)
                actionImage.add(util.scaleImage(sheet1.crop(j,i),144,144));
                //actionImage.add(sheet1.crop(j,i));
    }

    public void update(){
        playerMovement();
        mouseAction();
    }

    public void playerMovement(){
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
            //
            mp.collisionChecker.checkTile(this);
            mp.collisionChecker.checkEntity(this);

            if(!collision){
                switch (direction) {
                    case "up" -> {worldY -= speed;}
                    case "down" -> {worldY += speed;}
                    case "left" -> {worldX -= speed;}
                    case "right" -> {worldX += speed;}
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

    public void mouseAction(){
        if(mouse.leftButtonPressed ){
            getActionDirection();
            spriteCounter++;

            if(spriteCounter <= 5){spriteNumber = 1;}
            if(spriteCounter >5 && spriteCounter <= 25){spriteNumber = 2;}
            if(spriteCounter > 25){
                spriteNumber = 1;
                spriteCounter = 0;
            }

            mp.collisionChecker.checkPlayerInteraction(this, this.selectedTool);
        }
        if(mouse.middleButtonPressed)
            System.out.println("Middle button = " + mouse.mouseX + " " + mouse.mouseY);
        if(mouse.rightButtonPressed)
            System.out.println("Rigth button = " + mouse.mouseX + " " + mouse.mouseY);
    }

    public void getActionDirection(){
        //De reparat Pozitia mouseului aprope de caracter
        if(mouse.mouseY <= mp.screenHeight/2) {
            if(mouse.mouseX <= (mp.screenWidth)/5)
                actionDirection = "Left";
            else if((mouse.mouseX > ((mp.screenWidth)/5)* 4))
                actionDirection = "Right";
            else actionDirection = "Up";
        }else {
            if(mouse.mouseX <= (mp.screenWidth)/5)
                actionDirection = "Left";
            else if((mouse.mouseX > ((mp.screenWidth)/5)* 4))
                actionDirection = "Right";
            else actionDirection = "Down";
        }
    }
    public void draw(Graphics2D playerGraphics){
        //TO FIX 1/2 px diff in first column char sprites
        BufferedImage image = null;
        if(mouse.leftButtonPressed){
            switch (selectedTool){
                case "Pickaxe" ->{
                    switch (actionDirection){
                        case "Up" -> {
                            if(spriteNumber == 1){image = actionImage.get(2);}
                            if (spriteNumber == 2) {image = actionImage.get(3);}
                        }
                        case "Down" -> {
                            if(spriteNumber == 1){image = actionImage.get(0);}
                            if (spriteNumber == 2) {image = actionImage.get(1);}
                        }
                        case "Right" ->{
                            if(spriteNumber == 1){image = actionImage.get(6);}
                            if (spriteNumber == 2) {image = actionImage.get(7);}
                        }
                        case "Left" ->{
                            if(spriteNumber == 1){image = actionImage.get(4);}
                            if (spriteNumber == 2) {image = actionImage.get(5);}
                        }
                    }
                }

                case "Axe" ->{
                    switch (actionDirection){
                        case "Up" -> {
                            if(spriteNumber == 1){image = actionImage.get(8);}
                            if (spriteNumber == 2) {image = actionImage.get(9);}
                        }
                        case "Down" -> {
                            if(spriteNumber == 1){image = actionImage.get(10);}
                            if (spriteNumber == 2) {image = actionImage.get(11);}
                        }
                        case "Right" ->{
                            if(spriteNumber == 1){image = actionImage.get(14);}
                            if (spriteNumber == 2) {image = actionImage.get(15);}
                        }
                        case "Left" ->{
                            if(spriteNumber == 1){image = actionImage.get(12);}
                            if (spriteNumber == 2) {image = actionImage.get(13);}
                        }
                    }
                }
            }
        }
        else {
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
        }
        if(mouse.leftButtonPressed)
            playerGraphics.drawImage(image, screenX - 48, screenY - 48, null);
        else playerGraphics.drawImage(image, screenX, screenY, null);
    }
}
