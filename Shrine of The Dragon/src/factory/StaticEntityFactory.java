package factory;

import entity.Rock;
import entity.Soil;
import entity.StationaryEntity;
import entity.Tree;
import main.MainPanel;

public class StaticEntityFactory{
    MainPanel mp;
    public StaticEntityFactory(MainPanel mp){
        this.mp = mp;
    }
    public StationaryEntity factory(String type, String size, int coordX, int coordY, int hp){
        switch (type) {
            case "Rock" -> {return new Rock(mp, size, coordX, coordY, hp);}
            case "Tree" -> {return new Tree(mp, size, coordX, coordY, hp);}
            case "Soil" -> {return new Soil(mp, coordX, coordY);}
        }
        throw new IllegalArgumentException("Bad argument");
    }
}
