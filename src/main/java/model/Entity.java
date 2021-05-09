package model;

/**
 * This interface represents an object which can give back what property does it have.
 */
public interface Entity {

    /**
     * @return true if the object is Player
     */
    boolean isPlayer();

    boolean isGoal();

    boolean isBall();

    boolean isObstacle();

    boolean isMoveAble();
}
