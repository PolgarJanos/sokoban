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
    public static int BOARD_SIZE = 5;

    /**
     * @return a {@code List} Which is representing the Valuable entities on the board;
     */
    public static List<EntityWrapper> ValuableEntities() {
        List<EntityWrapper> entities = new LinkedList<>();
        entities.add(new EntityWrapper(EntityImpl.PLAYER, new IntPosition(1, 1)));
        return entities;
    }
}
