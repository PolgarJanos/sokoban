package model.directionImpl;

import model.Direction;

public enum EnumDirection implements Direction {
    UP(-1, 0),
    RIGHT(0, 1),
    DOWN(1, 0),
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
