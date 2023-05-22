package main;

import entity.MovableEntity;
import entity.StationaryEntity;
import tile.TileManager;

import java.util.ArrayList;

public class LevelManager {
    MainPanel mp;
    public int currentLevelID = 0;
    public ArrayList<Level> levels = new ArrayList<>();
    public LevelManager(MainPanel mp){
        this.mp = mp;
        for(int i = 0; i < 4; ++i){
            levels.add(new Level(mp));
        }
        //levels.get(0).maxWorldCol = 32;
        //levels.get(0).maxWorldRow = 25;
    }

    public void addLevel(){git 
        Level temp = new Level(mp);
        levels.add(temp);
    }

    public void addMovableEntity(MovableEntity movableEntity, int LevelID){
        levels.get(LevelID).movableEntities.add(movableEntity);
    }

    public void addStationaryEntity(StationaryEntity stationaryEntity, int LevelID){
        levels.get(LevelID).stationaryEntities.add(stationaryEntity);
    }
}
