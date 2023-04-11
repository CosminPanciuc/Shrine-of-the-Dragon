package tile;

import graphics.ImageLoader;
import graphics.SpriteSheet;
import main.MainPanel;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TileManager {
    MainPanel mp;
    public Tile[] tile;
    public int[][] mapTileNumber;

    public TileManager(MainPanel mp){
        this.mp = mp;
        tile = new Tile[10];
        mapTileNumber = new int[mp.maxWorldCol][mp.maxWorldRow];
        getTileImage();
        loadMap();
    }

    public void loadMap(){
        try{
            InputStream is = getClass().getResourceAsStream("/maps/map.txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int col = 0;
            int row = 0;
            while(col < mp.maxWorldCol && row < mp.maxWorldRow){
                String line = br.readLine();
                while(col < mp.maxWorldCol){
                    String[] numbers = line.split(" ");

                    int num = Integer.parseInt(numbers[col]);

                    mapTileNumber[col][row] = num;
                    ++col;
                }
                if(col == mp.maxWorldCol){
                    col = 0;
                    ++row;
                }
            }
            br.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void getTileImage(){
        SpriteSheet spriteSheet = new SpriteSheet(mp, ImageLoader.LoadImage("/tiles/48x48.png"));
        tile[0] = new Tile();
        tile[0].texture = spriteSheet.crop(8,0);
        tile[1] = new Tile();
        tile[1].texture = spriteSheet.crop(8,1);
        tile[2] = new Tile();
        tile[2].texture = spriteSheet.crop(9,0);
        tile[3] = new Tile();
        tile[3].texture = spriteSheet.crop(5,0);
        tile[3].collision = true;
    }

    public void draw(Graphics2D g2){

        int worldCol = 0;
        int worldRow = 0;

        while(worldCol < mp.maxWorldCol && worldRow < mp.maxWorldRow){
            int tileNum = mapTileNumber[worldCol][worldRow];

            int worldX = worldCol * mp.tileSize;
            int worldY = worldRow * mp.tileSize;

            int screenX = worldX - mp.player.worldX + mp.player.screenX;
            int screenY = worldY - mp.player.worldY + mp.player.screenY;

            if(worldX + mp.tileSize > mp.player.worldX - mp.player.screenX &&
                    worldX - mp.tileSize < mp.player.worldX + mp.player.screenX &&
                    worldY + mp.tileSize > mp.player.worldY - mp.player.screenY &&
                    worldY - mp.tileSize < mp.player.worldY + mp.player.screenY) {
                g2.drawImage(tile[tileNum].texture, screenX, screenY, mp.tileSize, mp.tileSize, null);
            }
            ++worldCol;

            if(worldCol == mp.maxWorldCol){
                worldCol = 0;
                ++worldRow;

            }
        }
    }
}
