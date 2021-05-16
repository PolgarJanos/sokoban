package sokoban.model.tableImpl;

import javafx.beans.property.ReadOnlyObjectWrapper;
import sokoban.model.Entity;
import sokoban.model.Position;
import sokoban.model.Table;
import sokoban.model.positionImpl.IntPosition;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Representing the game board.
 */
public class TableImpl implements Table {


    private final ReadOnlyObjectWrapper<Entity>[][][] table;

    private int bordSize;
    private ReadOnlyObjectWrapper<Entity>[][] table2DRepresentation;
    private Entity entity;


    /**
     * Representing the game bord.
     *
     * @param bordSize              the size of the board.
     * @param table2DRepresentation the table 2 dimension representation.
     * @param entity                which will be place on the board.
     */
    public TableImpl(int bordSize, ReadOnlyObjectWrapper<Entity>[][] table2DRepresentation, Entity entity) {
        this.bordSize = bordSize;
        this.table2DRepresentation = table2DRepresentation;
        this.entity = entity;
        table = new ReadOnlyObjectWrapper[bordSize][bordSize][2];
        for (int i = 0; i < bordSize; i++) {
            for (int j = 0; j < bordSize; j++) {
                table[i][j][0] = table2DRepresentation[i][j];
                table[i][j][1] = new ReadOnlyObjectWrapper<Entity>(entity.giveBackNone());

            }
        }
        //creat2DRepresentation();
    }

    @Override
    public boolean isObstacleOnPosition(Position position) throws IllegalArgumentException {
        if (validatePosition(position)) {
            return table[position.getXCoordinate()][position.getYCoordinate()][0].getValue().isObstacle();
        } else {
            throw new IllegalArgumentException("Coordinate out of boundary.Coordinate: " + position.getXCoordinate() + " " + position.getYCoordinate());
        }
    }

    @Override
    public boolean isMoveAbleOnPosition(Position position) throws IllegalArgumentException {
        if (validatePosition(position)) {
            return table[position.getXCoordinate()][position.getYCoordinate()][0].getValue().isMoveAble();
        } else {
            throw new IllegalArgumentException("Coordinate out of boundary.Coordinate: " + position.getXCoordinate() + " " + position.getYCoordinate());
        }
    }

    @Override
    public boolean isEmptyOnPosition(Position position) throws IllegalArgumentException {
        if (validatePosition(position)) {
            return table[position.getXCoordinate()][position.getYCoordinate()][0].getValue().isNothing();
        } else {
            throw new IllegalArgumentException("Coordinate out of boundary.Coordinate: " + position.getXCoordinate() + " " + position.getYCoordinate());
        }
    }

    @Override
    public Entity getEntityFromPosition(Position position) throws IllegalArgumentException {
        if (validatePosition(position)) {
            return table[position.getXCoordinate()][position.getYCoordinate()][0].getValue();
        } else {
            throw new IllegalArgumentException("Coordinate out of boundary.Coordinate: " + position.getXCoordinate() + " " + position.getYCoordinate());
        }
    }

    @Override
    public void putOnPosition(Entity entity, Position position) throws IllegalArgumentException {
        if (validatePosition(position)) {
            if (getEntityFromPosition(position).isGoal()) {
                pushDownWhitEntity(entity, position);
            } else {
                table[position.getXCoordinate()][position.getYCoordinate()][0].set(entity);
            }
        } else {
            throw new IllegalArgumentException("Coordinate out of boundary.Coordinate: " + position.getXCoordinate() + " " + position.getYCoordinate());
        }
        creat2DRepresentation();
    }

    private void pushDownWhitEntity(Entity entity, Position position) {
        table[position.getXCoordinate()][position.getYCoordinate()][1].set(table[position.getXCoordinate()][position.getYCoordinate()][0].getValue());
        table[position.getXCoordinate()][position.getYCoordinate()][0].set(entity);
    }

    @Override
    public void removeFromPosition(Position position) throws IllegalArgumentException {
        if (validatePosition(position)) {
            table[position.getXCoordinate()][position.getYCoordinate()][0].set(table[position.getXCoordinate()][position.getYCoordinate()][1].getValue());
            table[position.getXCoordinate()][position.getYCoordinate()][1].set(entity.giveBackNone());
        } else {
            throw new IllegalArgumentException("Coordinate out of boundary.Coordinate: " + position.getXCoordinate() + " " + position.getYCoordinate());
        }
        creat2DRepresentation();
    }

    @Override
    public Position getPlayerPosition() {

        for (int i = 0; i < bordSize; i++) {
            for (int j = 0; j < bordSize; j++) {
                if (table[i][j][0].getValue().isPlayer()) {
                    return new IntPosition(i, j);
                }

            }
        }
        throw new Error("Can't find the player");
    }

    @Override
    public Set<Position> getBallPositions() {
        Set<Position> ballPositions = new HashSet<>();
        for (int i = 0; i < bordSize; i++) {
            for (int j = 0; j < bordSize; j++) {
                if (table[i][j][0].getValue().isBall()) {
                    ballPositions.add(new IntPosition(i, j));
                }

            }
        }
        return ballPositions;
    }

    @Override
    public Set<Position> getGoalPositions() {
        Set<Position> goalPositions = new HashSet<>();
        for (int i = 0; i < bordSize; i++) {
            for (int j = 0; j < bordSize; j++) {
                if (table[i][j][0].getValue().isGoal() || table[i][j][1].getValue().isGoal()) {
                    goalPositions.add(new IntPosition(i, j));
                }

            }
        }
        return goalPositions;
    }

    private boolean validatePosition(Position position) {
        return (((position.getXCoordinate() < (bordSize)) &&
                (position.getYCoordinate() < (bordSize))) &&
                ((position.getXCoordinate() >= 0) &&
                        (position.getYCoordinate() >= 0)));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TableImpl table1 = (TableImpl) o;
        if (this.bordSize != table1.bordSize) {
            return false;
        }
        for (int i = 0; i < bordSize; i++) {
            for (int j = 0; j < bordSize; j++)
                for (int k = 0; k < 2; k++) {
                    if (!(this.table[i][j][k].getValue().equals(table1.table[i][j][k].getValue()))) {
                        return false;
                    }

                }
        }
        return true;
    }

    private void creat2DRepresentation() {
        for (int i = 0; i < bordSize; i++) {
            for (int j = 0; j < bordSize; j++) {
                table2DRepresentation[i][j] = table[i][j][0];
            }
        }
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(bordSize);
        for (int i = 0; i < bordSize; i++) {
            for (int j = 0; j < bordSize; j++) {
                for (int k = 0; k < 2; k++) {
                    result = result + table[i][j][k].getValue().hashCode();

                }
            }
        }
        result = 31 * result;
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < bordSize; i++) {
            for (int j = 0; j < bordSize; j++) {
                sb.append(table[j][i][0].getValue().toString()).append(' ');
            }
            sb.append('\n');
        }
        return sb.toString();
    }
}
