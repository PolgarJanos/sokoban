package model.directionImpl;

import model.Direction;

/**
 * Represents the four main directions.
 */
public enum EnumDirection implements Direction {
    /**
     * Representing the direction up.
     */
    UP(-1, 0),

    /**
     * Representing the direction RIGHT.
     */
    RIGHT(0, 1),

    /**
     * Representing the direction DOWN.
     */
    DOWN(1, 0),
    
    /**
     * Representing the direction LEFT.
     */
    LEFT(0, -1);


    private final int xChange;
    private final int yChange;

    EnumDirection(int yChange, int xChange) {
        this.xChange = xChange;
        this.yChange = yChange;
    }


    @Override
    public int getXChange() {
        return xChange;
    }

    @Override
    public int getYChange() {
        return yChange;
    }
}
