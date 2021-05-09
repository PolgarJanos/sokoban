package model;

public interface Entity {

    Position getPosition();
    boolean isPlayer();
    boolean isObstacle();
    boolean isGoal();
    boolean isBall();

}
