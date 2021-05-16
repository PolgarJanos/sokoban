package sokoban.model.moverImpl;


import sokoban.model.*;
import sokoban.model.positionImpl.IntPosition;

/**
 * Represent a mover which can move entity on a table.
 * It can only move two  entity.
 */
public class WeakMover implements Mover {
    @Override
    public void move(Position position, Direction direction, Table table) throws CantBeMovedException, IllegalArgumentException {

        move(position, direction, table, 0);


    }

    private void move(Position position, Direction direction, Table table, int recursiveLimiter) throws CantBeMovedException, IllegalArgumentException {
        Position whereToMovedPosition = new IntPosition(position.getXCoordinate() + direction.getXChange(),
                position.getYCoordinate() + direction.getYChange());
        if (table.isEmptyOnPosition(whereToMovedPosition) || table.isMoveAbleOnPosition(whereToMovedPosition)) {
            if (table.isMoveAbleOnPosition(whereToMovedPosition)) {
                if(recursiveLimiter<1) {
                    move(whereToMovedPosition, direction, table, recursiveLimiter + 1);
                }else {
                    throw new CantBeMovedException(table.getEntityFromPosition(whereToMovedPosition).toString() + " can't be moved.");
                }
            }

            Entity toBeMovedEntity = table.getEntityFromPosition(position);
            table.removeFromPosition(position);
            table.putOnPosition(toBeMovedEntity, whereToMovedPosition);
        } else if (!table.isObstacleOnPosition(whereToMovedPosition)) {

            Entity toBeMovedEntity = table.getEntityFromPosition(position);
            table.removeFromPosition(position);
            table.putOnPosition(toBeMovedEntity, whereToMovedPosition);
        } else {

            throw new CantBeMovedException(table.getEntityFromPosition(whereToMovedPosition).toString() + " can't be moved.");
        }
    }
}
