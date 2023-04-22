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
        tile = new Tile[30];
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
        initTile(0,8,1,false,true);
        initTile(1,0,14,true,false);
        initTile(2,1,14,true,false);
        initTile(3,2,14,true,false);
        //initTile(4,0,13,true,false);
        //initTile(5,1,13,true,false);
        //initTile(6,2,13,true,false);
        //initTile(7,0,12,true,false);
        //initTile(8,1,12,true,false);
        //initTile(9,2,12,true,false);
        /*
        initTile(10,17,2,true,false);
        initTile(11,18,2,true,false);
        initTile(12,19,2,true,false);
        initTile(13,17,1,true,false);
        initTile(14,18,1,true,false);
        initTile(15,19,1,true,false);
        initTile(16,17,0,true,false);
        initTile(17,18,0,true,false);
        initTile(18,19,0,true,false);
         */
        //initTile(5, 5,0,true,false,"/tiles/house asset.png",32);
        //initTile(6, 5,1,true,false,"/tiles/house asset.png",32);
        //initTile(7, 5,2,true,false,"/tiles/house asset.png",32);

        initTile(4,8,0,false,false);
        initTile(5,9,0,false,false);
        initTile(6,11,0,false,false);
        initTile(7,12,0,false,false);
    }
    public void initTile(int index, int x, int y, boolean collision, boolean plantable){
        tile[index] = new Tile();
        tile[index].texture = mp.tileSheet.crop(x,y);
        tile[index].collision = collision;
        tile[index].plantable = plantable;
    }
    public void initTile(int index, int x, int y, boolean collision, boolean plantable, String path, int pix){
        SpriteSheet sheet = new SpriteSheet(mp, ImageLoader.LoadImage(path));
        tile[index] = new Tile();
        tile[index].texture = sheet.crop(x,y,pix);
        tile[index].collision = collision;
        tile[index].plantable = plantable;
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
