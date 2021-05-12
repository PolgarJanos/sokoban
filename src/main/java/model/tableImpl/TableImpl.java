package model.tableImpl;

import model.Entity;
import model.Position;
import model.Table;

import java.util.Set;

public class TableImpl implements Table {
    @Override
    public boolean isObstacleOnPosition(Position position) throws IllegalArgumentException {
        return false;
    }

    @Override
    public boolean isMoveAbleOnPosition(Position position) throws IllegalArgumentException {
        return false;
    }

    @Override
    public boolean isEmptyOnPosition(Position position) throws IllegalArgumentException {
        return false;
    }

    @Override
    public Entity getEntityFromPosition(Position position) throws IllegalArgumentException {
        return null;
    }

    @Override
    public void putOnPosition(Entity entity, Position position) throws IllegalArgumentException {

    }

    @Override
    public void removeFromPosition(Position position) throws IllegalArgumentException {

    }

    @Override
    public Position getPlayerPosition() {
        return null;
    }

    @Override
    public Set<Position> getBallPositions() {
        return null;
    }

    @Override
    public Set<Position> getGoalPositions() {
        return null;
    }
}
