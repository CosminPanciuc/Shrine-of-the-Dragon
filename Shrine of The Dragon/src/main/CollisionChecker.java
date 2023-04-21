package main;

import entity.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

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
    public void checkPlayerInteraction(Player player, String selectedTool){
        ArrayList<StationaryEntity> temp = new ArrayList<>();
        for(StationaryEntity stationaryEntity: mp.stationaryEntities) {

            int currentWorldX = player.worldX;
            int currentWorldY = player.worldY;
            int hitBoxWidth = player.hitBox.width;
            int hitBoxHeight = player.hitBox.height;

            switch (player.actionDirection){
                case "Up" -> player.worldY -= player.actionArea.height;
                case "Down" -> player.worldY += player.actionArea.height;
                case "Left" -> player.worldX -= player.actionArea.width;
                case "Right" -> player.worldX += player.actionArea.width;
            }


            int defaultEntityHBX = player.hitBox.x;
            int defaultEntityHBY = player.hitBox.y;
            int defaultObjX = stationaryEntity.hitBox.x;
            int defaultObjY = stationaryEntity.hitBox.y;

            player.hitBox.x = player.worldX + player.hitBox.x;
            player.hitBox.y = player.worldY + player.hitBox.y;

            stationaryEntity.hitBox.x = stationaryEntity.worldX + stationaryEntity.hitBox.x;
            stationaryEntity.hitBox.y = stationaryEntity.worldY + stationaryEntity.hitBox.y;

            if (player.hitBox.intersects(stationaryEntity.hitBox)) {
                switch (selectedTool){
                    case "Axe" ->{
                        if(stationaryEntity.getClass() == Tree.class){
                            if(Objects.equals(stationaryEntity.size, "Big") && player.axeQuality > 1)
                                stationaryEntity.hitPoints = stationaryEntity.hitPoints - player.axeQuality - 1;
                            else if(Objects.equals(stationaryEntity.size, "Small"))
                                stationaryEntity.hitPoints = stationaryEntity.hitPoints - player.axeQuality;
                        }
                    }
                    case "Pickaxe" ->{
                        if(stationaryEntity.getClass() == Rock.class)
                            if(Objects.equals(stationaryEntity.size, "Big") && player.pickaxeQuality > 1)
                                stationaryEntity.hitPoints = stationaryEntity.hitPoints - player.pickaxeQuality - 1;
                            else if(Objects.equals(stationaryEntity.size, "Small"))
                                stationaryEntity.hitPoints = stationaryEntity.hitPoints - player.pickaxeQuality;
                    }
                }
            }
            if(stationaryEntity.hitPoints <= 0){
                temp.add(stationaryEntity);
            }
            player.hitBox.x = defaultEntityHBX;
            player.hitBox.y = defaultEntityHBY;
            stationaryEntity.hitBox.x = defaultObjX;
            stationaryEntity.hitBox.y = defaultObjY;

            player.worldX = currentWorldX;
            player.worldY = currentWorldY;
            player.hitBox.width = hitBoxWidth;
            player.hitBox.height = hitBoxHeight;
        }
        for(StationaryEntity stationaryEntity:temp){
            mp.stationaryEntities.remove(stationaryEntity);
        }
    }
}
