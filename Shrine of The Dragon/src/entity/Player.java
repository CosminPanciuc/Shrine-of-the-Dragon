package entity;

import graphics.ImageLoader;
import graphics.SpriteSheet;
import main.KeyInput;
import main.MainPanel;
import main.MouseInput;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Objects;

public class Player extends MovableEntity{
    MainPanel mp;
    KeyInput key;
    MouseInput mouse;
    public final int screenX;
    public final int screenY;
    String selectedTool = "Pickaxe";

    public int axeQuality = 1;
    public int pickaxeQuality = 1;
    boolean leftMouse = false;

    int hungerCount = 0;
    public ArrayList<BufferedImage> actionImage = new ArrayList<>();
    public int gold;
    public int meat;
    public int skin;

    public int hunger;
    public Player(MainPanel mp, KeyInput key, MouseInput mouse){
        this.mp = mp;
        this.key = key;
        this.mouse = mouse;

        screenX = (mp.screenTileColCount * mp.tileSize)/2 - (mp.tileSize/2);
        screenY = (mp.screenTileRowCount * mp.tileSize)/2 - (mp.tileSize/2);

        hitBox = new Rectangle(9,9,30,30);
        actionArea.width = 30;
        actionArea.height = 30;
        healthPool = 100;
        hunger = 100;

        setDefault();
        getPlayerImage();
    }
    public void setPlayerStats(int coordX, int coordY, int hp, int hunger, int actionWidth, int actionHeight, int gold, int meat, int skin){
        worldX = coordX * mp.tileSize;
        worldY = coordY * mp.tileSize;
        healthPool = hp;
        this.hunger = hunger;
        actionArea.width = actionWidth;
        actionArea.height = actionHeight;
        this.gold = gold;
        this.meat = meat;
        this.skin = skin;
    }

    public void setDefault(){
        worldX = mp.tileSize * 15;
        worldY = mp.tileSize * 15;
        speed = 4;
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
        SpriteSheet sheet1 = new SpriteSheet(mp, ImageLoader.LoadImage("/player/Basic Charakter Actions.png"));
        for(int i=0; i < 12; ++i)
            for(int j = 0; j < 2; ++j)
                actionImage.add(sheet1.scaleImage(sheet1.crop(j,i),144,144));
                //actionImage.add(sheet1.crop(j,i));
    }

    public void update(){
        playerMovement();
        mouseAction();
        setSelectedTool();
        playerPosition();
        if(healthPool <= 0 || hunger <= 0){
            mp.currentState = MainPanel.GameState.LOST;
        }
        playerHunger();
        if(attackCooldown > 0){
            attackCooldown -= 1;
        }
    }

    public void playerHunger(){
        if(hungerCount == 300){
            hunger--;
            hungerCount = 0;
        }else {
            hungerCount++;
        }
    }
    public void playerPosition(){
        switch (mp.levelManager.currentLevelID){
            case 0 ->{
                if(worldX > 24 * mp.tileSize && worldX < 28 * mp.tileSize){
                    if(worldY > 0 && worldY < mp.tileSize/2){
                        mp.levelManager.currentLevelID = 1;
                        worldY = mp.tileSize * 24 - mp.tileSize/2;
                    }
                }
                if(worldX > 0  && worldX < mp.tileSize - 24){
                    if(worldY > 19 * mp.tileSize && worldY < 22 * mp.tileSize){
                        mp.levelManager.currentLevelID = 3;
                        worldX = 31 * mp.tileSize;
                    }
                }
            }
            case 1 ->{
                if(worldX > 24 * mp.tileSize && worldX < 28 * mp.tileSize){
                    if(worldY > 24 * mp.tileSize -24 && worldY < 25 * mp.tileSize){
                        mp.levelManager.currentLevelID = 0;
                        worldY = mp.tileSize + mp.tileSize/2;
                    }
                }
                if(worldX > 0  && worldX < mp.tileSize - 20){
                    if(worldY > 14 * mp.tileSize && worldY < 19 * mp.tileSize){
                        mp.levelManager.currentLevelID = 2;
                        worldX = 31 * mp.tileSize;
                    }
                }
            }
            case 2 ->{
                if(worldX > 31 * mp.tileSize && worldX < 32 * mp.tileSize){
                    if(worldY > 15 * mp.tileSize && worldY < 18 * mp.tileSize){
                        mp.levelManager.currentLevelID = 1;
                        worldX = 48;
                    }
                }
            }
            case 3 ->{
                if(worldX > 31 * mp.tileSize  && worldX < 32 * mp.tileSize){
                    if(worldY > 18 * mp.tileSize && worldY < 23 * mp.tileSize){
                        mp.levelManager.currentLevelID = 0;
                        worldX = mp.tileSize;
                    }
                }
            }
        }
    }
    public void setSelectedTool(){
        switch (key.numberPressed){
            case 1 -> {
                selectedTool = "Axe";
                axeQuality = 1;
            }
            case 2 -> {
                selectedTool = "Pickaxe";
                pickaxeQuality = 1;
            }
            case 3 -> {
                selectedTool = "Axe";
                axeQuality = 2;
            }
            case 4 -> {
                selectedTool = "Pickaxe";
                pickaxeQuality = 2;
            }
            case 5-> {
                selectedTool = "Sword";
                damage = 5;
            }
            case 6-> {
                selectedTool = "Hoe";
            }
        }
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
        leftMouse = mouse.isMouseClicked(1);
        if(leftMouse){

            getActionDirection();
            spriteCounter++;

            if(spriteCounter <= 5){spriteNumber = 1;}
            if(spriteCounter >5 && spriteCounter <= 25){spriteNumber = 2;}
            if(spriteCounter > 25){
                spriteNumber = 1;
                spriteCounter = 0;
            }

            mp.collisionChecker.checkPlayerInteraction(this, this.selectedTool);
            mp.collisionChecker.checkPlayerInteraction(this,"quest");
            if(Objects.equals(selectedTool, "Hoe"))
                mp.collisionChecker.checkTileHit(this);

            if(Objects.equals(selectedTool, "Sword"))
                mp.collisionChecker.checkHit(this);

        }
        if(mouse.isMouseClicked(2))
            System.out.println("Middle button = " + mouse.mouseX + " " + mouse.mouseY);
        /*
        if(mouse.isMouseClicked(3)){

        }

         */
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
        BufferedImage image = null;
        if(leftMouse){
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
                            if(spriteNumber == 1){image = actionImage.get(10);}
                            if (spriteNumber == 2) {image = actionImage.get(11);}
                        }
                        case "Down" -> {
                            if(spriteNumber == 1){image = actionImage.get(8);}
                            if (spriteNumber == 2) {image = actionImage.get(9);}
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
        if(leftMouse){
            playerGraphics.drawImage(image, screenX - 48, screenY - 48, null);
        }
        else playerGraphics.drawImage(image, screenX, screenY, null);
    }
}
