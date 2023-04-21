package entity;

import main.MainPanel;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class StationaryEntity extends Entity{
    public int hitPoints;
    MainPanel mp;
    public ArrayList<BufferedImage> texture = new ArrayList<BufferedImage>();
    int rowNumber;
    int colNumber;

    @Override
    public void draw(Graphics2D g2){
        int screenX = worldX - mp.player.worldX + mp.player.screenX;
        int screenY = worldY - mp.player.worldY + mp.player.screenY;

        int currentRow = 0,currentCol = 0;

        if(144 + worldX + mp.tileSize > mp.player.worldX - mp.player.screenX &&
                worldX - mp.tileSize - 144 < mp.player.worldX + mp.player.screenX &&
                144 + worldY + mp.tileSize > mp.player.worldY - mp.player.screenY &&
                 worldY - mp.tileSize - 144 < mp.player.worldY + mp.player.screenY) {
                for(BufferedImage buff:texture){
                    g2.drawImage(buff, screenX + (currentRow * mp.tileSize), screenY + (currentCol * mp.tileSize), mp.tileSize , mp.tileSize, null);
                    ++currentCol;
                    if(currentCol == colNumber){
                        currentCol = 0;
                        ++currentRow;
                    }
                }
        }
    }
}
