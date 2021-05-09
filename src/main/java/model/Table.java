package model;

import java.util.Set;

public interface Table {

    boolean isObstacleOnPosition(Position position);
    boolean isMoveAbleOnPosition(Position position);
    Entity getEntityFromPosition(Position position) throws  IllegalArgumentException;
    void putOnPosition(Entity entity, Position position);
    void removeFromPosition(Position position);
    Position getPlayerPosition();
    Set<Position> getBallPositions();
    Set<Position> getGoalPositions();


}
