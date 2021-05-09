package model;

import java.util.Set;

/**
 * Representation of the game board.
 */
public interface Table {

    /**
     * Returns true if on the given posit the {@code Entity} is obstacle.
     * @param position The position of the  {@code Entity}
     * @return Returns true if on the given position the {@code Entity} is obstacle
     * @throws IllegalArgumentException if position is out of boundary
     */
    boolean isObstacleOnPosition(Position position) throws  IllegalArgumentException;

    /**
     * Returns true if on the given posit the {@code Entity} can be moved.
     *@param position The position of the  {@code Entity}
     *@return Returns true if on the given position the {@code Entity}  can be moved
     * @throws IllegalArgumentException if position is out of boundary
     */
    boolean isMoveAbleOnPosition(Position position) throws  IllegalArgumentException;

    /**
     *Returns whit the {@code Entity} in the given position.
     * @param position The position of the  {@code Entity}
     * @return whit the entity on the given position or {@code null} if there is no entity.
     * @throws IllegalArgumentException if position is out of boundary
     */
    Entity getEntityFromPosition(Position position) throws  IllegalArgumentException;

    /**
     * Put the given {@code Entity} on thew given {@code Position}.
     * If there was an {@code Entity} already it will be replaced
     * @param entity the type of the entity
     * @param position the position of the entity where it will be placed
     * @throws IllegalArgumentException if position is out of boundary
     */
    void putOnPosition(Entity entity, Position position) throws  IllegalArgumentException;

    /**
     * Remove the entity from the given position.
     * @param position the position where to remove
     * @throws IllegalArgumentException if position is out of boundary
     */
    void removeFromPosition(Position position)throws  IllegalArgumentException;

    /**
     * Returns the player {@code Position}.
     * @return the player {@code Position}
     */
    Position getPlayerPosition();

    /**
     * Returns set of {@code Position} representing the Balls position.
     * @return set of {@code Position} representing the Balls position.
     */
    Set<Position> getBallPositions();

    /**
     *Returns set of {@code Position} representing the Goals position.
     *@return set of {@code Position} representing the Goals position.
     */
    Set<Position> getGoalPositions();


}
