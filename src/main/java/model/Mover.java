package model;

public interface Mover {

    void move(Entity entity, Direction direction, Table table) throws cantBeMovedException;
}
