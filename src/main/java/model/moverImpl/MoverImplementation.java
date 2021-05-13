package model.moverImpl;

import model.*;
import model.positionImpl.IntPosition;

import java.util.Stack;

/**
 * Todo
 */
public class MoverImplementation implements Mover {
    /**
     * toDo
     * @param position  which position will be moved
     * @param direction in what direction will be moved
     * @param table     where the movement is occurred
     * @throws cantBeMovedException
     * @throws IllegalArgumentException
     */
    @Override
    public void move(Position position, Direction direction, Table table) throws cantBeMovedException, IllegalArgumentException {

        Stack<Position> moveAbleEntitiesPositions = collectMoveAbleEntitiesInTheWay(position, direction, table);

        if (table.isEmptyOnPosition(position)) {

            Position movesFromPosition = moveAbleEntitiesPositions.peek();
            Position movesToPosition = new IntPosition(movesFromPosition.getXCoordinate() + direction.getXChange(),
                    movesFromPosition.getYCoordinate() + direction.getYChange());
            Entity movingEntity = table.getEntityFromPosition(movesFromPosition);
            table.removeFromPosition(movesFromPosition);
            table.putOnPosition(movingEntity, movesToPosition);

            mover(table, moveAbleEntitiesPositions);
        } else {
            throw new cantBeMovedException(table.getEntityFromPosition(position).toString());
        }
    }

    private Stack<Position> collectMoveAbleEntitiesInTheWay(Position position, Direction direction, Table table) {
        Stack<Position> moveAbleEntitiesPositions = new Stack<>();
        while (!(table.isEmptyOnPosition(position)) && table.isMoveAbleOnPosition(position)) {
            moveAbleEntitiesPositions.push(position);
            position.setXCoordinate(position.getXCoordinate() + direction.getXChange());
            position.setYCoordinate(position.getYCoordinate() + direction.getYChange());
        }

        return moveAbleEntitiesPositions;
    }

    private void mover(Table table, Stack<Position> moveAbleEntitiesPositions) {

        while (moveAbleEntitiesPositions.size() != 1) {
            Position movesToPosition = moveAbleEntitiesPositions.pop();
            Position movesFromPosition = moveAbleEntitiesPositions.peek();

            Entity movingEntity = table.getEntityFromPosition(movesFromPosition);
            table.removeFromPosition(movesFromPosition);
            table.putOnPosition(movingEntity, movesToPosition);
        }
    }
}
