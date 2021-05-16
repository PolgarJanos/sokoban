package sokoban.model.entityImpl.asEnum;

import sokoban.model.Entity;

/**
 * Enum implementation of the {@code Entity} interface.
 */
public enum EntityImpl implements Entity {
    /**
     * Represent a Ball on the board{@code Entity}.
     */
    BALL(false, false, true, true, true),
    /**
     * Represent a Goal on the board{@code Entity}.
     */
    GOAL(false, true, false, false, false),
    /**
     * Represent a Player on the board{@code Entity}.
     */
    PLAYER(true, false, false, true, true),
    /**
     * Represent a Wall on the board{@code Entity}.
     */
    WALL(false, false, false, true, false),
    /**
     * Represent Noting on the board {@code Entity}.
     */
    NONE(false,false,false,false,false)
    ;

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

    @Override
    public boolean isNothing() {
        return !(isPlayer()||isGoal()||isBall()||isMoveAble()||isObstacle());
    }

    @Override
    public Entity giveBackNone() {
        return EntityImpl.NONE;
    }
}
