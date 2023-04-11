package main;

import entity.Entity;

public class CollisionChecker {

    MainPanel mp;
    public CollisionChecker(MainPanel mp){
        this.mp = mp;
    }

    public void checkTile(Entity entity){
        int entityLeftX = entity.worldX + entity.hitBox.x;
        int entityRightX = entity.worldX + entity.hitBox.x + entity.hitBox.width;
        int entityTopY = entity.worldY + entity.hitBox.y;
        int entityBottomY = entity.worldY + entity.hitBox.y + entity.hitBox.height;

        int entityLeftCol = entityLeftX/mp.tileSize;
        int entityRightCol = entityRightX/mp.tileSize;
        int entityTopRow = entityTopY/mp.tileSize;
        int entityBottomRow = entityBottomY/mp.tileSize;

        int tileNumber1,tileNumber2;

        switch (entity.direction) {
            case "up" -> {
                entityTopRow = (entityTopY - entity.speed) / mp.tileSize;
                tileNumber1 = mp.tileManager.mapTileNumber[entityLeftCol][entityTopRow];
                tileNumber2 = mp.tileManager.mapTileNumber[entityRightCol][entityTopRow];
                if (mp.tileManager.tile[tileNumber1].collision || mp.tileManager.tile[tileNumber2].collision) {
                    entity.collision = true;
                }
            }
            case "down" -> {
                entityBottomRow = (entityBottomY + entity.speed) / mp.tileSize;
                tileNumber1 = mp.tileManager.mapTileNumber[entityLeftCol][entityBottomRow];
                tileNumber2 = mp.tileManager.mapTileNumber[entityRightCol][entityBottomRow];
                if (mp.tileManager.tile[tileNumber1].collision || mp.tileManager.tile[tileNumber2].collision) {
                    entity.collision = true;
                }
            }
            case "left" -> {
                entityLeftCol = (entityLeftX - entity.speed) / mp.tileSize;
                tileNumber1 = mp.tileManager.mapTileNumber[entityLeftCol][entityTopRow];
                tileNumber2 = mp.tileManager.mapTileNumber[entityLeftCol][entityBottomRow];
                if (mp.tileManager.tile[tileNumber1].collision || mp.tileManager.tile[tileNumber2].collision) {
                    entity.collision = true;
                }
            }
            case "right" -> {
                entityRightCol = (entityRightX + entity.speed) / mp.tileSize;
                tileNumber1 = mp.tileManager.mapTileNumber[entityRightCol][entityTopRow];
                tileNumber2 = mp.tileManager.mapTileNumber[entityRightCol][entityBottomRow];
                if (mp.tileManager.tile[tileNumber1].collision || mp.tileManager.tile[tileNumber2].collision) {
                    entity.collision = true;
                }
            }

        }
    }
}
