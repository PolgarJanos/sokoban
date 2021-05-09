package model;

public interface Mover {

    void move(Position position, Direction direction, Table table) throws cantBeMovedException;
}
