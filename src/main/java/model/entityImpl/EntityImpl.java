package model.entityImpl;

import model.Entity;

public enum EntityImpl implements Entity {

    Ball(false, false, true, true, true),
    Goal(false, true, false, false, false),
    Player(true, false, false, true, true),
    Wall(false, false, false, true, false);

    private boolean isPlayer;
    private boolean isGoal;
    private boolean isBall;
    private boolean isObstacle;
    private boolean isMoveAble;

    EntityImpl(boolean isPlayer, boolean isGoal, boolean isBall, boolean isObstacle, boolean isMoveAble) {
        this.isPlayer = isPlayer;
        this.isGoal = isGoal;
        this.isBall = isBall;
        this.isObstacle = isObstacle;
        this.isMoveAble = isMoveAble;
    }

    @Override
    public boolean isPlayer() {
        return isPlayer;
    }

    @Override
    public boolean isGoal() {
        return isGoal;
    }

    @Override
    public boolean isBall() {
        return isBall;
    }

    @Override
    public boolean isObstacle() {
        return isObstacle;
    }

    @Override
    public boolean isMoveAble() {
        return isMoveAble;
    }
}
