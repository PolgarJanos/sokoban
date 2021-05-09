package model.EntityImpl;

import model.Entity;
/**
 * Implementation of {@code Entity} which class represents a goal {@code Entity}.
 */
public class Goal implements Entity {
    @Override
    public boolean isPlayer() {
        return false;
    }

    @Override
    public boolean isGoal() {
        return true;
    }

    @Override
    public boolean isBall() {
        return false;
    }

    @Override
    public boolean isObstacle() {
        return false;
    }

    @Override
    public boolean isMoveAble() {
        return false;
    }
}
