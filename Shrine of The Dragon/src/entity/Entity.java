package entity;

import main.MainPanel;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class Entity {
    public int worldX,worldY;

    MainPanel mp;
    public Rectangle hitBox;
    public boolean collision = false;

    public void draw(Graphics2D g2){}

    public void update() {}

}
