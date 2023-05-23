package factory;

import entity.*;
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
            case "Ambient" -> {return new Ambient(mp, size, coordX, coordY, hp);}
            case "Dragon" ->{return new Dragon(mp,coordX,coordY,hp);}
        }
        throw new IllegalArgumentException("Bad argument");
    }
}
