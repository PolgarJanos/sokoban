package model;

/**
 * A class that can move an {@code Entity} on a {@code Table}.
 */
public interface Mover {
    /**
     *Moves a {@code Entity} from  a given position to a given direction on a given table.
     *
     * @param position which position will be moved
     * @param direction in what direction will be moved
     * @param table where the movement is occurred
     * @throws cantBeMovedException if the {@code Entity} can be moved.
     */
    void move(Position position, Direction direction, Table table) throws cantBeMovedException;
}
