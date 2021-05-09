package model.entityImpl;

import model.Entity;

/**
 * Implementation of {@code Entity} which represents a ball {@code Entity}.
 */
public class Ball implements Entity {
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
        return true;
    }

    @Override
    public boolean isObstacle() {
        return true;
    }

    @Override
    public boolean isMoveAble() {
        return true;
    }
}
