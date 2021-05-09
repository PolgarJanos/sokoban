package model.entityImpl;

import model.Entity;
/**
 * Implementation of {@code Entity} which represents a wall {@code Entity}.
 */
public class Wall implements Entity {
    @Override
    public boolean isPlayer() {
        return false;
    }

    @Override
    public boolean isGoal() {
        return false;
    }

    @Override
    public boolean isBall() {
        return false;
    }

    @Override
    public boolean isObstacle() {
        return true;
    }

    @Override
    public boolean isMoveAble() {
        return false;
    }
}
