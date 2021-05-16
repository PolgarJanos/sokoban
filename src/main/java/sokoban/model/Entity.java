package sokoban.model;

/**
 * This interface represents an object which can give back what property does it have.
 */
public interface Entity {

    /**
     * @return true if the object is Player.
     */
    boolean isPlayer();
    /**
     * @return true if the object is goal
     */
    boolean isGoal();
    /**
     * @return true if the object is ball.
     */
    boolean isBall();
    /**
     * @return true if the object is Obstacle.
     */
    boolean isObstacle();
    /**
     * @return true if the object can be moved.
     */
    boolean isMoveAble();

    /**
     * Return true if the object represent the nothing.
     * @return true if every other property is false.
     */
    boolean isNothing();

    /**
     *
     * @return an {@code Entity } which representing the nothing.
     */
    Entity giveBackNone();
}
