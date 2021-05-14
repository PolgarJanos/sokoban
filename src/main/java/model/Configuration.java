package model;

import model.entityImpl.asEnum.EntityImpl;
import model.positionImpl.IntPosition;

import java.util.LinkedList;
import java.util.List;

public class Configuration {

    public static int BOARD_SIZE= 5;
    public static List<EntityWrapper> ValuableEntities(){
        List<EntityWrapper> entities =new LinkedList<>();
        entities.add(new EntityWrapper(EntityImpl.PLAYER, new IntPosition(1,1)));
        return entities;
    }
}
