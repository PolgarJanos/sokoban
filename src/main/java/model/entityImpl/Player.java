package model.entityImpl;


import model.Entity;

/**
 * Implementation of {@code Entity} which represents a player {@code Entity}.
 */
public class Player implements Entity {
    @Override
    public boolean isPlayer() {
        return true;

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
        return true;
    }
}
