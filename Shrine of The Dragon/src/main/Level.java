package main;

import entity.MovableEntity;
import entity.StationaryEntity;
import tile.TileManager;

import java.util.ArrayList;

public class Level {
    MainPanel mp;

    public TileManager tileManager;
    public ArrayList<StationaryEntity> stationaryEntities = new ArrayList<StationaryEntity>();
    public ArrayList<MovableEntity> movableEntities = new ArrayList<>();
    public Level(MainPanel mp){
        this.mp = mp;
        tileManager = new TileManager(mp);
    }
}
