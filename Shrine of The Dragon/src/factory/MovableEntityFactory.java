package factory;

import entity.Bear;
import entity.MovableEntity;
import main.MainPanel;

public class MovableEntityFactory{
    MainPanel mp;
    public MovableEntityFactory(MainPanel mp){
        this.mp = mp;
    }
    public MovableEntity factory(String type, int coordX, int coordY, int hp, int speed, int damage){
        switch (type){
            case "Bear" -> {return new Bear(mp,coordX,coordY,hp,speed,damage);}
        }
        throw new IllegalArgumentException("Bad type");
    }
}
