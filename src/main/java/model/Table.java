package model;

public interface Table {

    boolean isObstacleOnPosition(Position position);
    boolean isMoveAbleOnPosition(Position position);
    void putOnPosition(Entity entity, Position position);
    void removeFromPosition(Entity entity, Position position);

}
