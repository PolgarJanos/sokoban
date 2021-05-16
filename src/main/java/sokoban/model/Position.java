package sokoban.model;

/**
 * This interface represents an object that is representation of a 2 dimension coordinate.
 */
public interface Position {

    /**
     * Give back first coordinate.
     *
     * @return a int represented the first (x) coordinate
     */
    int getXCoordinate();

    /**
     * Set the first coordinate to x.
     *
     * @param x int representation of the first coordinate
     */
    void setXCoordinate(int x);

    /**
     * Give back second coordinate.
     *
     * @return a int represented the second (y) coordinate
     */
    int getYCoordinate();

    /**
     * Set the second coordinate to x.
     *
     * @param y int representation of the second coordinate
     */
    void setYCoordinate(int y);

    /**
     * Compares the specified object with this position for equality.
     *
     * @param o the object to be compared for equality with this equality
     * @return true if the specified object is equal to this position
     */
    boolean equals(Object o);

    /**
     *
     * @return the hash code value for this position.
     */
    int hashCode();
}
