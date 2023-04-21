package entity;

import java.awt.image.BufferedImage;

public class MovableEntity extends Entity{
    public int speed;
    public String direction;
    public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;

    public int spriteCounter = 0;
    public int spriteNumber = 1;
}
