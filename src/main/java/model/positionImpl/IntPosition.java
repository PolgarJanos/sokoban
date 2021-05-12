package model.positionImpl;

import model.Position;

import java.util.Objects;

/**
 * Class to representing a 2 dimension coordinate.
 */
public class IntPosition implements Position {
    private int x;
    private int y;

    /**
     * Creates a {@code IntPosition} object.
     *
     * @param x int representation of the first coordinate
     * @param y int representation of the second coordinate
     */
    public IntPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public int getXCoordinate() {
        return x;
    }

    @Override
    public void setXCoordinate(int x) {
        this.x = x;
    }

    @Override
    public int getYCoordinate() {
        return y;
    }


    @Override
    public void setYCoordinate(int y) {
        this.y = y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IntPosition that = (IntPosition) o;
        return x == that.x && y == that.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

}
