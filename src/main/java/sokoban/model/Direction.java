package sokoban.model;

/**
 * Represents the directions.
 */
public interface Direction {
    /**
     * Return the change in the x coordinate when moving to the direction.
     * @return the change in the x coordinate when moving to the direction.
     */
    int getXChange();
    /**
     * Return the change in the y coordinate when moving to the direction.
     * @return the change in the y cordinate when moving to the direction.
     */
    int getYChange();

}
