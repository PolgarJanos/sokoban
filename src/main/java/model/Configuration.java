package model;

import model.entityImpl.asEnum.EntityImpl;
import model.positionImpl.IntPosition;

import java.util.LinkedList;
import java.util.List;

/**
 *It provides configuration data for the Game model.
 */
public class Configuration {

    /**
     * Teh size of the board.
     */
    public static int BOARD_SIZE = 10;

    /**
     * @return a {@code List} Which is representing the Valuable entities on the board;
     */
    public static List<EntityWrapper> ValuableEntities() {
        List<EntityWrapper> entities = new LinkedList<>();
        entities.add(new EntityWrapper(EntityImpl.PLAYER, new IntPosition(1, 1)));
        entities.add(new EntityWrapper(EntityImpl.BALL, new IntPosition(5, 5)));
        entities.add(new EntityWrapper(EntityImpl.BALL, new IntPosition(7, 7)));
        entities.add(new EntityWrapper(EntityImpl.BALL, new IntPosition(8, 2)));
        entities.add(new EntityWrapper(EntityImpl.GOAL, new IntPosition(3, 2)));
        entities.add(new EntityWrapper(EntityImpl.GOAL, new IntPosition(4, 7)));
        entities.add(new EntityWrapper(EntityImpl.WALL, new IntPosition(6, 4)));
        entities.add(new EntityWrapper(EntityImpl.WALL, new IntPosition(6, 3)));
        entities.add(new EntityWrapper(EntityImpl.GOAL, new IntPosition(2, 5)));
        return entities;
    }
}
