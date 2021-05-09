package model;

public interface Entity {

    boolean isPlayer();
    boolean isGoal();
    boolean isBall();

    boolean isObstacle();
    boolean isMoveAble();
}
