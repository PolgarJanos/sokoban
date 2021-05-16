package model;

import model.directionImpl.EnumDirection;
import model.entityImpl.asEnum.EntityImpl;
import model.positionImpl.IntPosition;

import java.util.LinkedList;
import java.util.List;

/**
 * It provides configuration data for the Game model.
 */
public class Configuration {

    /**
     * Teh size of the board.
     */
    public static int BOARD_SIZE = 10;


    private static List<EntityWrapper> entities = new LinkedList<>();

    /**
     * @return a {@code List} Which is representing the Valuable entities on the board;
     */
    public static List<EntityWrapper> ValuableEntities() {
        entities.add(new EntityWrapper(EntityImpl.PLAYER, new IntPosition(1,1)));
        entities.add(new EntityWrapper(EntityImpl.BALL, new IntPosition(2,2)));
        entities.add(new EntityWrapper(EntityImpl.BALL, new IntPosition(2,3)));
        entities.add(new EntityWrapper(EntityImpl.BALL, new IntPosition(3,2)));
        entities.add(new EntityWrapper(EntityImpl.GOAL, new IntPosition(7,3)));
        entities.add(new EntityWrapper(EntityImpl.GOAL, new IntPosition(7,4)));
        entities.add(new EntityWrapper(EntityImpl.GOAL, new IntPosition(7,5)));
       creatWall(new IntPosition(0,0), EnumDirection.RIGHT,5);
        creatWall(new IntPosition(4,1), EnumDirection.DOWN,4);
        creatWall(new IntPosition(5,4), EnumDirection.RIGHT,2);
        creatWall(new IntPosition(6,3), EnumDirection.UP,2);
        creatWall(new IntPosition(7,2), EnumDirection.RIGHT,2);
        creatWall(new IntPosition(8,2), EnumDirection.DOWN,6);
        creatWall(new IntPosition(7,7), EnumDirection.LEFT,2);
        creatWall(new IntPosition(5,6), EnumDirection.DOWN,3);
        creatWall(new IntPosition(4,8), EnumDirection.LEFT,4);
        creatWall(new IntPosition(1,4), EnumDirection.DOWN,5);
        creatWall(new IntPosition(2,4), EnumDirection.DOWN,2);
        creatWall(new IntPosition(0,1), EnumDirection.DOWN,4);

        return entities;
    }

    private static void creatWall(Position startPosition, Direction direction, int length) {
        Position positionToAdd = startPosition;
        for (int i = 0; i < length; i++) {
            entities.add(new EntityWrapper(EntityImpl.WALL, positionToAdd));
            positionToAdd = new IntPosition(positionToAdd.getXCoordinate() + direction.getXChange(),
                    positionToAdd.getYCoordinate() + direction.getYChange());
        }
    }
}
