package model.tableImpl;

import javafx.beans.property.ReadOnlyObjectWrapper;
import model.Entity;
import model.Position;
import model.Table;
import model.entityImpl.asEnum.EntityImpl;
import model.positionImpl.IntPosition;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Representing the game board.
 */
public class TableImpl implements Table {

    private final int BOARD_SIZE = 5;
    private final ReadOnlyObjectWrapper<Entity>[][] table = new ReadOnlyObjectWrapper[BOARD_SIZE][BOARD_SIZE];


    /**
     * Representing the game bord.
     */
    public TableImpl() {

        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                table[i][j] = new ReadOnlyObjectWrapper<Entity>(EntityImpl.NONE);
            }
        }
    }

    @Override
    public boolean isObstacleOnPosition(Position position) throws IllegalArgumentException {
        if (validatePosition(position)) {
            return table[position.getXCoordinate()][position.getYCoordinate()].getValue().isObstacle();
        } else {
            throw new IllegalArgumentException("Coordinate out of boundary.Coordinate: " + position.getXCoordinate() + " " + position.getYCoordinate());
        }
    }

    @Override
    public boolean isMoveAbleOnPosition(Position position) throws IllegalArgumentException {
        if (validatePosition(position)) {
            return table[position.getXCoordinate()][position.getYCoordinate()].getValue().isMoveAble();
        } else {
            throw new IllegalArgumentException("Coordinate out of boundary.Coordinate: " + position.getXCoordinate() + " " + position.getYCoordinate());
        }
    }

    @Override
    public boolean isEmptyOnPosition(Position position) throws IllegalArgumentException {
        if (validatePosition(position)) {
            return table[position.getXCoordinate()][position.getYCoordinate()].getValue().isNothing();
        } else {
            throw new IllegalArgumentException("Coordinate out of boundary.Coordinate: " + position.getXCoordinate() + " " + position.getYCoordinate());
        }
    }

    @Override
    public Entity getEntityFromPosition(Position position) throws IllegalArgumentException {
        if (validatePosition(position)) {
            return table[position.getXCoordinate()][position.getYCoordinate()].getValue();
        } else {
            throw new IllegalArgumentException("Coordinate out of boundary.Coordinate: " + position.getXCoordinate() + " " + position.getYCoordinate());
        }
    }

    @Override
    public void putOnPosition(Entity entity, Position position) throws IllegalArgumentException {
        if (validatePosition(position)) {
            table[position.getXCoordinate()][position.getYCoordinate()].set(entity);
        } else {
            throw new IllegalArgumentException("Coordinate out of boundary.Coordinate: " + position.getXCoordinate() + " " + position.getYCoordinate());
        }
    }

    @Override
    public void removeFromPosition(Position position) throws IllegalArgumentException {
        if (validatePosition(position)) {
            table[position.getXCoordinate()][position.getYCoordinate()].set(EntityImpl.NONE);
        } else {
            throw new IllegalArgumentException("Coordinate out of boundary.Coordinate: " + position.getXCoordinate() + " " + position.getYCoordinate());
        }

    }

    @Override
    public Position getPlayerPosition() {

        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                if (table[i][j].getValue().isPlayer()) {
                    return new IntPosition(i, j);
                }

            }
        }
        throw new Error("Can't find the player");
    }

    @Override
    public Set<Position> getBallPositions() {
        Set<Position> ballPositions = new HashSet<>();
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                if (table[i][j].getValue().isBall()) {
                    ballPositions.add(new IntPosition(i, j));
                }

            }
        }
        return ballPositions;
    }

    @Override
    public Set<Position> getGoalPositions() {
        Set<Position> goalPositions = new HashSet<>();
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                if (table[i][j].getValue().isGoal()) {
                    goalPositions.add(new IntPosition(i, j));
                }

            }
        }
        return goalPositions;
    }

    private boolean validatePosition(Position position) {
        return (((position.getXCoordinate() < (BOARD_SIZE)) &&
                (position.getYCoordinate() < (BOARD_SIZE))) &&
                ((position.getXCoordinate() >= 0) &&
                        (position.getYCoordinate() >= 0)));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TableImpl table1 = (TableImpl) o;
        if (this.BOARD_SIZE != table1.BOARD_SIZE) {
            return false;
        }
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                if (!(this.table[i][j].getValue().equals(table1.table[i][j].getValue()))) {
                    return false;
                }

            }
        }
        return true;
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(BOARD_SIZE);
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {

                result=result+table[i][j].getValue().hashCode();

            }
        }
        result = 31 * result;
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                sb.append(table[j][i].getValue().toString()).append(' ');
            }
            sb.append('\n');
        }
        return sb.toString();
    }
}
