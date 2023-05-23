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

    //verifica coliziunea cu tileurile
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
                tileNumber1 = mp.levelManager.levels.get(mp.levelManager.currentLevelID).tileManager.mapTileNumber[entityLeftCol][entityTopRow];
                tileNumber2 = mp.levelManager.levels.get(mp.levelManager.currentLevelID).tileManager.mapTileNumber[entityRightCol][entityTopRow];
                if (mp.levelManager.levels.get(mp.levelManager.currentLevelID).tileManager.tile[tileNumber1].collision || mp.levelManager.levels.get(mp.levelManager.currentLevelID).tileManager.tile[tileNumber2].collision) {
                    entity.collision = true;
                }
            }
            case "down" -> {
                entityBottomRow = (entityBottomY + entity.speed) / mp.tileSize;
                tileNumber1 = mp.levelManager.levels.get(mp.levelManager.currentLevelID).tileManager.mapTileNumber[entityLeftCol][entityBottomRow];
                tileNumber2 = mp.levelManager.levels.get(mp.levelManager.currentLevelID).tileManager.mapTileNumber[entityRightCol][entityBottomRow];
                if (mp.levelManager.levels.get(mp.levelManager.currentLevelID).tileManager.tile[tileNumber1].collision || mp.levelManager.levels.get(mp.levelManager.currentLevelID).tileManager.tile[tileNumber2].collision) {
                    entity.collision = true;
                }
            }
            case "left" -> {
                entityLeftCol = (entityLeftX - entity.speed) / mp.tileSize;
                tileNumber1 = mp.levelManager.levels.get(mp.levelManager.currentLevelID).tileManager.mapTileNumber[entityLeftCol][entityTopRow];
                tileNumber2 = mp.levelManager.levels.get(mp.levelManager.currentLevelID).tileManager.mapTileNumber[entityLeftCol][entityBottomRow];
                if (mp.levelManager.levels.get(mp.levelManager.currentLevelID).tileManager.tile[tileNumber1].collision || mp.levelManager.levels.get(mp.levelManager.currentLevelID).tileManager.tile[tileNumber2].collision) {
                    entity.collision = true;
                }
            }
            case "right" -> {
                entityRightCol = (entityRightX + entity.speed) / mp.tileSize;
                tileNumber1 = mp.levelManager.levels.get(mp.levelManager.currentLevelID).tileManager.mapTileNumber[entityRightCol][entityTopRow];
                tileNumber2 = mp.levelManager.levels.get(mp.levelManager.currentLevelID).tileManager.mapTileNumber[entityRightCol][entityBottomRow];
                if (mp.levelManager.levels.get(mp.levelManager.currentLevelID).tileManager.tile[tileNumber1].collision || mp.levelManager.levels.get(mp.levelManager.currentLevelID).tileManager.tile[tileNumber2].collision) {
                    entity.collision = true;
                }
            }

        }
    }
    //verifica coliziunea entitatilor
    public void checkEntity(MovableEntity entity){
        for(StationaryEntity stationaryEntity: mp.levelManager.levels.get(mp.levelManager.currentLevelID).stationaryEntities) {

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

        for(MovableEntity movableEntity: mp.levelManager.levels.get(mp.levelManager.currentLevelID).movableEntities) {
            if(movableEntity != entity){
                int defaultEntityHBX = entity.hitBox.x;
                int defaultEntityHBY = entity.hitBox.y;
                int defaultObjX = movableEntity.hitBox.x;
                int defaultObjY = movableEntity.hitBox.y;

                entity.hitBox.x = entity.worldX + entity.hitBox.x;
                entity.hitBox.y = entity.worldY + entity.hitBox.y;
                movableEntity.hitBox.x = movableEntity.worldX + movableEntity.hitBox.x;
                movableEntity.hitBox.y = movableEntity.worldY + movableEntity.hitBox.y;

                switch (entity.direction) {
                    case "up" -> {
                        entity.hitBox.y -= entity.speed;
                        if (entity.hitBox.intersects(movableEntity.hitBox)) {
                            entity.collision = true;
                        }
                    }
                    case "down" -> {
                        entity.hitBox.y += entity.speed;
                        if (entity.hitBox.intersects(movableEntity.hitBox)) {
                            entity.collision = true;
                        }
                    }
                    case "right" -> {
                        entity.hitBox.x += entity.speed;
                        if (entity.hitBox.intersects(movableEntity.hitBox)) {
                            entity.collision = true;
                        }
                    }
                    case "left" -> {
                        entity.hitBox.x -= entity.speed;
                        if (entity.hitBox.intersects(movableEntity.hitBox)) {
                            entity.collision = true;
                        }
                    }
                }
                entity.hitBox.x = defaultEntityHBX;
                entity.hitBox.y = defaultEntityHBY;
                movableEntity.hitBox.x = defaultObjX;
                movableEntity.hitBox.y = defaultObjY;
            }
        }

    }
    public void checkMobPlayer(MovableEntity entity){
        int defaultEntityHBX = entity.hitBox.x;
        int defaultEntityHBY = entity.hitBox.y;
        int defaultObjX = mp.player.hitBox.x;
        int defaultObjY = mp.player.hitBox.y;
        boolean player = false;

        entity.hitBox.x = entity.worldX + entity.hitBox.x;
        entity.hitBox.y = entity.worldY + entity.hitBox.y;
        mp.player.hitBox.x = mp.player.worldX + mp.player.hitBox.x;
        mp.player.hitBox.y = mp.player.worldY + mp.player.hitBox.y;

        switch (entity.direction) {
            case "up" -> {
                entity.hitBox.y -= entity.speed;
                if (entity.hitBox.intersects(mp.player.hitBox)) {
                    entity.collision = true;
                    player = true;
                }
            }
            case "down" -> {
                entity.hitBox.y += entity.speed;
                if (entity.hitBox.intersects(mp.player.hitBox)) {
                    entity.collision = true;
                    player = true;
                }
            }
            case "right" -> {
                entity.hitBox.x += entity.speed;
                if (entity.hitBox.intersects(mp.player.hitBox)) {
                    entity.collision = true;
                    player = true;
                }
            }
            case "left" -> {
                entity.hitBox.x -= entity.speed;
                if (entity.hitBox.intersects(mp.player.hitBox)) {
                    entity.collision = true;
                    player = true;
                }
            }
        }
        if(entity.collision && entity.attackCooldown == 0 && player){
            mp.player.healthPool -= entity.damage;
            entity.attackCooldown = 30;
        }
        entity.hitBox.x = defaultEntityHBX;
        entity.hitBox.y = defaultEntityHBY;
        mp.player.hitBox.x = defaultObjX;
        mp.player.hitBox.y = defaultObjY;
    }
    //verifica interactiunea playerului cu obiectele statice
    public void checkPlayerInteraction(Player player, String selectedTool){
        ArrayList<StationaryEntity> temp = new ArrayList<>();
        for(StationaryEntity stationaryEntity: mp.levelManager.levels.get(mp.levelManager.currentLevelID).stationaryEntities) {

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
                            if(((Objects.equals(stationaryEntity.size, "Big" )  || Objects.equals(stationaryEntity.size, "Fallen" )) && player.axeQuality > 1))
                                stationaryEntity.hitPoints = stationaryEntity.hitPoints - player.axeQuality - 1;
                            else if(Objects.equals(stationaryEntity.size, "Small"))
                                stationaryEntity.hitPoints = stationaryEntity.hitPoints - player.axeQuality;
                        }
                    }
                    case "Pickaxe" ->{
                        if(stationaryEntity.getClass() == Rock.class)
                            if(Objects.equals(stationaryEntity.size, "Big") && player.pickaxeQuality > 1)
                                stationaryEntity.hitPoints = stationaryEntity.hitPoints - player.pickaxeQuality - 1;
                        if(Objects.equals(stationaryEntity.size, "BigMossy") && player.pickaxeQuality > 1)
                            stationaryEntity.hitPoints = stationaryEntity.hitPoints - player.pickaxeQuality - 1;
                            else if(Objects.equals(stationaryEntity.size, "Small"))
                                stationaryEntity.hitPoints = stationaryEntity.hitPoints - player.pickaxeQuality;
                    }
                    case "NPC"->{
                        if(stationaryEntity.getClass() == NPC.class){
                            if(Objects.equals(mp.player.selectedTool, "Axe") && mp.player.gold > 100){
                                mp.player.axeQuality = 2;
                                mp.player.gold -= 100;
                            }
                            if(Objects.equals(mp.player.selectedTool, "Pickaxe") && mp.player.gold > 100){
                                mp.player.pickaxeQuality = 2;
                                mp.player.gold -= 100;
                            }
                        }
                    }
                    case "quest"->{
                        if(stationaryEntity.getClass() == Dragon.class){
                            switch (mp.quest){
                                case 1->{
                                    if(mp.player.gold >= 300){
                                        mp.player.gold -= 300;
                                        mp.quest = 2;
                                    }
                                }
                                case 2->{
                                    if(mp.player.meat >= 100){
                                        mp.player.meat -= 100;
                                        mp.quest = 3;
                                    }
                                }
                                case 3->{
                                    if(mp.player.skin >= 50){
                                        mp.player.skin -= 50;
                                        mp.quest = 4;
                                    }
                                }
                                case 4->{
                                    mp.currentState = MainPanel.GameState.WON;
                                }
                            }
                        }
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
            if(stationaryEntity.getClass() == Tree.class){
                player.gold += 50;
            }
            if(stationaryEntity.getClass() == Rock.class){
                player.gold += 100;
            }
            mp.levelManager.levels.get(mp.levelManager.currentLevelID).stationaryEntities.remove(stationaryEntity);
        }
    }
    public void checkHit(MovableEntity entity){
        ArrayList<MovableEntity> temp = new ArrayList<>();

        for(MovableEntity movableEntity: mp.levelManager.levels.get(mp.levelManager.currentLevelID).movableEntities){

            int currentWorldX = entity.worldX;
            int currentWorldY = entity.worldY;
            int hitBoxWidth = entity.hitBox.width;
            int hitBoxHeight = entity.hitBox.height;

            switch (entity.actionDirection){
                case "Up" -> entity.worldY -= entity.actionArea.height;
                case "Down" -> entity.worldY += entity.actionArea.height;
                case "Left" -> entity.worldX -= entity.actionArea.width;
                case "Right" -> entity.worldX += entity.actionArea.width;
            }


            int defaultEntityHBX = entity.hitBox.x;
            int defaultEntityHBY = entity.hitBox.y;
            int defaultObjX = movableEntity.hitBox.x;
            int defaultObjY = movableEntity.hitBox.y;

            entity.hitBox.x = entity.worldX + entity.hitBox.x;
            entity.hitBox.y = entity.worldY + entity.hitBox.y;

            movableEntity.hitBox.x = movableEntity.worldX + movableEntity.hitBox.x;
            movableEntity.hitBox.y = movableEntity.worldY + movableEntity.hitBox.y;

            if (entity.hitBox.intersects(movableEntity.hitBox) && entity.attackCooldown == 0){
                movableEntity.healthPool -= entity.damage;
                entity.attackCooldown = 15;
                System.out.println("I am hitting");
            }

            if(movableEntity.healthPool <= 0){
                temp.add(movableEntity);
            }

            entity.hitBox.x = defaultEntityHBX;
            entity.hitBox.y = defaultEntityHBY;
            movableEntity.hitBox.x = defaultObjX;
            movableEntity.hitBox.y = defaultObjY;

            entity.worldX = currentWorldX;
            entity.worldY = currentWorldY;
            entity.hitBox.width = hitBoxWidth;
            entity.hitBox.height = hitBoxHeight;
        }

        for(MovableEntity movableEntity: temp){
            if(movableEntity.getClass() == Bear.class){
                mp.player.meat += 50;
                if(mp.player.hunger + 50 > 100){
                    mp.player.hunger = 100;
                }else{
                    mp.player.hunger += 50;
                }
            }
            if(movableEntity.getClass() == Snake.class){
                mp.player.skin += 25;
                if(mp.player.healthPool + 30 > 100){
                    mp.player.healthPool = 100;
                }else{
                    mp.player.healthPool += 30;
                }
            }
            mp.levelManager.levels.get(mp.levelManager.currentLevelID).movableEntities.remove(movableEntity);
        }
    }

    public void checkTileHit(MovableEntity entity){
        switch (entity.actionDirection){
            case "Up" -> {
                int newEntityX = (entity.worldX)/mp.tileSize;
                int newEntityY = (entity.worldY - 48)/mp.tileSize;
                if(mp.levelManager.levels.get(mp.levelManager.currentLevelID).tileManager.tile[mp.levelManager.levels.get(mp.levelManager.currentLevelID).tileManager.mapTileNumber[newEntityX][newEntityY]].plantable){
                    boolean found = false;
                    for(StationaryEntity stationaryEntity: mp.levelManager.levels.get(mp.levelManager.currentLevelID).stationaryEntities){
                        if(stationaryEntity.worldX/mp.tileSize == newEntityX && stationaryEntity.worldY/mp.tileSize == newEntityY){
                            found = true;
                            break;
                        }
                    }
                    if(!found)
                        mp.levelManager.levels.get(mp.levelManager.currentLevelID).stationaryEntities.add(new Soil(mp,newEntityX,newEntityY));
                }
            }

            case "Down" -> {
                int newEntityX = (entity.worldX)/mp.tileSize;
                int newEntityY = (entity.worldY + 48)/mp.tileSize;
                if(mp.levelManager.levels.get(mp.levelManager.currentLevelID).tileManager.tile[mp.levelManager.levels.get(mp.levelManager.currentLevelID).tileManager.mapTileNumber[newEntityX][newEntityY]].plantable){
                    boolean found = false;
                    for(StationaryEntity stationaryEntity: mp.levelManager.levels.get(mp.levelManager.currentLevelID).stationaryEntities){
                        if(stationaryEntity.worldX/mp.tileSize == newEntityX && stationaryEntity.worldY/mp.tileSize == newEntityY){
                            found = true;
                            break;
                        }
                    }
                    if(!found)
                        mp.levelManager.levels.get(mp.levelManager.currentLevelID).stationaryEntities.add(new Soil(mp,newEntityX, newEntityY));
                }
            }

            case "Right" -> {
                int newEntityX = (entity.worldX + 48)/mp.tileSize;
                int newEntityY = (entity.worldY)/mp.tileSize;
                if(mp.levelManager.levels.get(mp.levelManager.currentLevelID).tileManager.tile[mp.levelManager.levels.get(mp.levelManager.currentLevelID).tileManager.mapTileNumber[newEntityX][newEntityY]].plantable){
                    boolean found = false;
                    for(StationaryEntity stationaryEntity: mp.levelManager.levels.get(mp.levelManager.currentLevelID).stationaryEntities){
                        if(stationaryEntity.worldX/mp.tileSize  == newEntityX && stationaryEntity.worldY/mp.tileSize == newEntityY){
                            found = true;
                            break;
                        }
                    }
                    if(!found)
                        mp.levelManager.levels.get(mp.levelManager.currentLevelID).stationaryEntities.add(new Soil(mp,newEntityX, newEntityY));
                }
            }

            case "Left" -> {
                int newEntityX = (entity.worldX - 48)/mp.tileSize;
                int newEntityY = (entity.worldY)/mp.tileSize;
                if(mp.levelManager.levels.get(mp.levelManager.currentLevelID).tileManager.tile[mp.levelManager.levels.get(mp.levelManager.currentLevelID).tileManager.mapTileNumber[newEntityX][newEntityY]].plantable){
                    boolean found = false;
                    for(StationaryEntity stationaryEntity: mp.levelManager.levels.get(mp.levelManager.currentLevelID).stationaryEntities){
                        if(stationaryEntity.worldX/mp.tileSize == newEntityX && stationaryEntity.worldY/mp.tileSize == newEntityY){
                            found = true;
                            break;
                        }
                    }
                    if(!found)
                        mp.levelManager.levels.get(mp.levelManager.currentLevelID).stationaryEntities.add(new Soil(mp,newEntityX, newEntityY));
                }
            }
        }
    }
}
