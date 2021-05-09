package model;

import java.util.Stack;

public class MoverImplementation implements Mover {
    @Override
    public void move(Position position, Direction direction, Table table) throws cantBeMovedException, IllegalArgumentException {

        Stack<Position> moveAbleEntitiesPositions = collectMoveAbleEntitiesInTheWay(position, direction, table);

        if (table.isEmptyOnPosition(position)) {
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
        while (!moveAbleEntitiesPositions.empty()) {
            Position currentPosition = moveAbleEntitiesPositions.pop();
            Entity currentEntity = table.getEntityFromPosition(currentPosition);
            table.removeFromPosition(currentPosition);
            table.putOnPosition(currentEntity, currentPosition);
        }
    }
}
