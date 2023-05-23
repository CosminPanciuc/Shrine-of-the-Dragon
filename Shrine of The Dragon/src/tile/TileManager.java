package tile;

import graphics.ImageLoader;
import graphics.SpriteSheet;
import main.MainPanel;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Scanner;

public class TileManager {
    MainPanel mp;
    public Tile[] tile;
    public int[][] mapTileNumber;

    public int maxWorldCol = 32;
    public int maxWorldRow = 25;
    public int mapNumber;

    public TileManager(MainPanel mp, int i){
        this.mp = mp;
        tile = new Tile[30];
        mapTileNumber = new int[maxWorldCol][maxWorldRow];
        mapNumber = i;
        getTileImage();
        loadMap();
    }

    public void loadMap(){
        Scanner scanner;
        try{
            InputStream is = null;
            switch (mapNumber){
                case 0->{
                     is = getClass().getResourceAsStream("/maps/map.txt");

                }
                case 1->{
                    is = getClass().getResourceAsStream("/maps/map1.txt");
                }
                case 2->{
                    is = getClass().getResourceAsStream("/maps/map2.txt");
                }
                case 3->{
                    is = getClass().getResourceAsStream("/maps/map3.txt");
                }
                default -> throw new Exception("No such map");
            }
            scanner = new Scanner(is);


            int col = 0;
            int row = 0;
            while(col < maxWorldCol && row < maxWorldRow){
                while(col < maxWorldCol){
                    int val = scanner.nextInt();
                    mapTileNumber[col][row] = val;
                    ++col;
                }
                if(col == maxWorldCol){
                    col = 0;
                    ++row;
                }
            }
            scanner.close();
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
        initTile(9,2,12,true,false);
        //initTile(10,17,2,true,false);
        /*

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

        initTile(8,17,0,false,false);
        initTile(9,18,0,false,false);
        initTile(10,19,0,false,false);

        initTile(11,17,1,false,false);
        initTile(12,18,1,false,false);
        initTile(13,19,1,false,false);

        initTile(14,17,2,false,false);
        initTile(15,18,2,false,false);
        initTile(16,19,2,false,false);

        initTile(17, 24,1,true,false);
        initTile(18,12,8,false,false);

        initTile(19,0,0,true,false, "/textures/column.png",48);
        initTile(20,0,0,false,false, "/textures/modeletile.png",48);
        initTile(21,11,8,false,false);
        initTile(22,13,8,false,false);
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

        while(worldCol < maxWorldCol && worldRow < maxWorldRow){
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

            if(worldCol == maxWorldCol){
                worldCol = 0;
                ++worldRow;

            }
        }
    }
}
