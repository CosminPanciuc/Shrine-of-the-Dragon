package factory;

import entity.Entity;
import entity.MovableEntity;
import entity.StationaryEntity;


public class EntityFactory extends AbstractFactory{
    public Entity getEntity(String type) {
        if(type.compareTo("Stationary") == 0)
            return new StationaryEntity();
        if(type.compareTo("Movable") == 0)
            return new MovableEntity();
        throw new IllegalArgumentException("Entity type does not exist!");
    }
}
