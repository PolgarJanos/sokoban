package model.moverImpl;

import model.*;
import model.positionImpl.IntPosition;

/**
 * Represent a mover which can move entity on a table.
 */
public class MoverImplementation implements Mover {

    @Override
    public void move(Position position, Direction direction, Table table) throws cantBeMovedException, IllegalArgumentException {
        Position whereToMovedPosition = new IntPosition(position.getXCoordinate() + direction.getXChange(),
                position.getYCoordinate() + direction.getYChange());
        if (table.isEmptyOnPosition(whereToMovedPosition) || table.isMoveAbleOnPosition(whereToMovedPosition)) {
            if (table.isMoveAbleOnPosition(whereToMovedPosition)) {
                move(whereToMovedPosition, direction, table);
            }

            Entity toBeMovedEntity = table.getEntityFromPosition(position);
            table.removeFromPosition(position);
            table.putOnPosition(toBeMovedEntity, whereToMovedPosition);
        } else if (!table.isObstacleOnPosition(whereToMovedPosition)) {
            Entity toBeMovedEntity = table.getEntityFromPosition(position);
            table.putOnPosition(toBeMovedEntity, whereToMovedPosition);
        } else {

            throw new cantBeMovedException(table.getEntityFromPosition(whereToMovedPosition).toString() + " can't be moved.");
        }


    }


}
