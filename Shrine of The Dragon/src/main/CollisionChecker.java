package main;

import entity.Entity;
import entity.MovableEntity;
import entity.StationaryEntity;

public class CollisionChecker {

    MainPanel mp;
    public CollisionChecker(MainPanel mp){
        this.mp = mp;
    }

    public void checkTile(MovableEntity entity){
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

    public void checkEntity(MovableEntity entity){
        for(StationaryEntity stationaryEntity: mp.stationaryEntities) {

            int defaultEntityHBX = entity.hitBox.x;
            int defaultEntityHBY = entity.hitBox.y;
            int defaultObjX = stationaryEntity.hitBox.x;
            int defaultObjY = stationaryEntity.hitBox.y;
            entity.hitBox.x = entity.worldX + entity.hitBox.x;
            entity.hitBox.y = entity.worldY + entity.hitBox.y;
            stationaryEntity.hitBox.x = stationaryEntity.worldX + stationaryEntity.hitBox.x;
            stationaryEntity.hitBox.y = stationaryEntity.worldY + stationaryEntity.hitBox.y;

            switch (entity.direction) {
                case "up" -> {
                    entity.hitBox.y -= entity.speed;
                    if (entity.hitBox.intersects(stationaryEntity.hitBox)) {
                        entity.collision = true;
                    }
                }
                case "down" -> {
                    entity.hitBox.y += entity.speed;
                    if (entity.hitBox.intersects(stationaryEntity.hitBox)) {
                        entity.collision = true;
                    }
                }
                case "right" -> {
                    entity.hitBox.x += entity.speed;
                    if (entity.hitBox.intersects(stationaryEntity.hitBox)) {
                        entity.collision = true;
                    }
                }
                case "left" -> {
                    entity.hitBox.x -= entity.speed;
                    if (entity.hitBox.intersects(stationaryEntity.hitBox)) {
                        entity.collision = true;
                    }
                }
            }
            entity.hitBox.x = defaultEntityHBX;
            entity.hitBox.y = defaultEntityHBY;
            stationaryEntity.hitBox.x = defaultObjX;
            stationaryEntity.hitBox.y = defaultObjY;
        }
    }
}
