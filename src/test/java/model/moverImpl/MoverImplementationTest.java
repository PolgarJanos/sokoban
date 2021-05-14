package model.moverImpl;

import javafx.beans.property.ReadOnlyObjectWrapper;
import model.*;
import model.directionImpl.EnumDirection;
import model.entityImpl.asEnum.EntityImpl;
import model.positionImpl.IntPosition;
import model.tableImpl.TableImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.tinylog.Logger;

import static org.junit.jupiter.api.Assertions.*;

class MoverImplementationTest {

    Table excepted;
    Table actual;
    ReadOnlyObjectWrapper[][] table2d;
    ReadOnlyObjectWrapper[][] table2d1;

    @BeforeEach
    void init() {
        int boardSize = 5;
        table2d = new ReadOnlyObjectWrapper[boardSize][boardSize];
        table2d1 = new ReadOnlyObjectWrapper[boardSize][boardSize];
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {


                table2d[i][j] = new ReadOnlyObjectWrapper<Entity>(EntityImpl.NONE);
                table2d1[i][j] = new ReadOnlyObjectWrapper<Entity>(EntityImpl.NONE);

            }
        }
        Entity entity = EntityImpl.NONE;
        excepted = new TableImpl(boardSize, table2d, entity);
        actual = new TableImpl(boardSize, table2d1, entity);
    }

    @Test
    void testMoveOnePlayerUp() throws cantBeMovedException {
        //Given
        Direction upDirection = EnumDirection.UP;
        Entity movingEntity = EntityImpl.PLAYER;
        int xCoordinate = 2;
        int yCoordinate = 2;
        Position positionBeforeMove = new IntPosition(xCoordinate, yCoordinate);
        Position positionAfterMove = new IntPosition(xCoordinate + upDirection.getXChange(), yCoordinate + upDirection.getYChange());


        excepted.putOnPosition(movingEntity, positionAfterMove);

        actual.putOnPosition(movingEntity, positionBeforeMove);

        Mover mover = new MoverImplementation();
        //When

        mover.move(positionBeforeMove, upDirection, actual);

        //Then
        Logger.trace("actual:{}, {}", actual.getPlayerPosition().getXCoordinate(), actual.getPlayerPosition().getYCoordinate());
        Logger.trace("excepted:{}, {}", excepted.getPlayerPosition().getXCoordinate(), excepted.getPlayerPosition().getYCoordinate());
        assertEquals(actual, excepted);


    }

    @Test
    void testMoveOnePlayerDown() throws cantBeMovedException {
        //Given
        Direction direction = EnumDirection.DOWN;
        Entity movingEntity = EntityImpl.PLAYER;
        int xCoordinate = 2;
        int yCoordinate = 2;
        Position positionBeforeMove = new IntPosition(xCoordinate, yCoordinate);
        Position positionAfterMove = new IntPosition(xCoordinate + direction.getXChange(), yCoordinate + direction.getYChange());


        excepted.putOnPosition(movingEntity, positionAfterMove);

        actual.putOnPosition(movingEntity, positionBeforeMove);

        Mover mover = new MoverImplementation();
        //When

        mover.move(positionBeforeMove, direction, actual);

        //Then
        Logger.trace("actual:{}, {}", actual.getPlayerPosition().getXCoordinate(), actual.getPlayerPosition().getYCoordinate());
        Logger.trace("excepted:{}, {}", excepted.getPlayerPosition().getXCoordinate(), excepted.getPlayerPosition().getYCoordinate());
        assertEquals(actual, excepted);


    }

    @Test
    void testMoveOnePlayerRight() throws cantBeMovedException {
        //Given
        Direction direction = EnumDirection.RIGHT;
        Entity movingEntity = EntityImpl.PLAYER;
        int xCoordinate = 2;
        int yCoordinate = 2;
        Position positionBeforeMove = new IntPosition(xCoordinate, yCoordinate);
        Position positionAfterMove = new IntPosition(xCoordinate + direction.getXChange(), yCoordinate + direction.getYChange());


        excepted.putOnPosition(movingEntity, positionAfterMove);

        actual.putOnPosition(movingEntity, positionBeforeMove);

        Mover mover = new MoverImplementation();
        //When

        mover.move(positionBeforeMove, direction, actual);

        //Then
        Logger.trace("actual:{}, {}", actual.getPlayerPosition().getXCoordinate(), actual.getPlayerPosition().getYCoordinate());
        Logger.trace("excepted:{}, {}", excepted.getPlayerPosition().getXCoordinate(), excepted.getPlayerPosition().getYCoordinate());
        assertEquals(actual, excepted);


    }

    @Test
    void testMoveOnePlayerLeft() throws cantBeMovedException {
        //Given
        Direction direction = EnumDirection.LEFT;
        Entity movingEntity = EntityImpl.PLAYER;
        int xCoordinate = 2;
        int yCoordinate = 2;
        Position positionBeforeMove = new IntPosition(xCoordinate, yCoordinate);
        Position positionAfterMove = new IntPosition(xCoordinate + direction.getXChange(), yCoordinate + direction.getYChange());


        excepted.putOnPosition(movingEntity, positionAfterMove);

        actual.putOnPosition(movingEntity, positionBeforeMove);

        Mover mover = new MoverImplementation();
        //When

        mover.move(positionBeforeMove, direction, actual);

        //Then
        Logger.trace("actual:{}, {}", actual.getPlayerPosition().getXCoordinate(), actual.getPlayerPosition().getYCoordinate());
        Logger.trace("excepted:{}, {}", excepted.getPlayerPosition().getXCoordinate(), excepted.getPlayerPosition().getYCoordinate());
        assertEquals(actual, excepted);


    }

    @Test
    void testMoveWhenMovingEntityIsMovingOnAnUnMoveAbelEntityShouldNotChangeTheTable() throws cantBeMovedException {
        //Given
        Direction direction = EnumDirection.LEFT;
        Entity movingEntity = EntityImpl.BALL;
        Entity unMoveAbleEntity = EntityImpl.WALL;
        int ballXCoordinate = 2;
        int ballYCoordinate = 2;
        Position positionBeforeMove = new IntPosition(ballXCoordinate, ballYCoordinate);
        Position wallPosition = new IntPosition(ballXCoordinate + direction.getXChange(), ballYCoordinate + direction.getYChange());


        excepted.putOnPosition(movingEntity, positionBeforeMove);
        excepted.putOnPosition(unMoveAbleEntity, wallPosition);


        actual.putOnPosition(movingEntity, positionBeforeMove);
        actual.putOnPosition(unMoveAbleEntity, wallPosition);

        Mover mover = new MoverImplementation();
        Logger.trace("actual before move:\n{}", actual.toString());
        Logger.trace("excepted before move:\n{}", excepted.toString());
        //When
        try {
            mover.move(positionBeforeMove, direction, actual);
        } catch (Exception e) {

        }
        //Then
        Logger.trace("actual after move:\n{}", actual.toString());
        Logger.trace("excepted after move:\n{}", excepted.toString());
        assertEquals(actual, excepted);
    }

    @Test
    void testMoveMovingEntityIsMovingOnAnUnMoveAbelEntityShouldThrowCantBeMoveException() {
        //Given
        Direction direction = EnumDirection.DOWN;
        Entity movingEntity = EntityImpl.BALL;
        Entity unMoveAbleEntity = EntityImpl.WALL;
        int ballXCoordinate = 2;
        int ballYCoordinate = 2;
        Position positionBeforeMove = new IntPosition(ballXCoordinate, ballYCoordinate);
        Position wallPosition = new IntPosition(ballXCoordinate + direction.getXChange(), ballYCoordinate + direction.getYChange());


        excepted.putOnPosition(movingEntity, positionBeforeMove);
        excepted.putOnPosition(unMoveAbleEntity, wallPosition);


        actual.putOnPosition(movingEntity, positionBeforeMove);
        actual.putOnPosition(unMoveAbleEntity, wallPosition);

        Mover mover = new MoverImplementation();
        Logger.trace("actual before move:\n{}", actual.toString());
        Logger.trace("excepted before move:\n{}", excepted.toString());
        //When


        //Then
        Logger.trace("actual after move:\n{}", actual.toString());
        Logger.trace("excepted after move:\n{}", excepted.toString());
        assertThrows(cantBeMovedException.class, () -> mover.move(positionBeforeMove, direction, actual));
    }

    @Test
    void testMoveMovingEntityPushTheEntityWhichInHisWay() throws cantBeMovedException {
        //Given
        Direction direction = EnumDirection.DOWN;
        Entity movingEntity = EntityImpl.PLAYER;
        Entity moveAbleEntity = EntityImpl.BALL;
        int ballXCoordinate = 2;
        int ballYCoordinate = 2;
        Position positionBeforeMove = new IntPosition(ballXCoordinate, ballYCoordinate);
        Position moveAbleEntityWhichWillBePushPositionBeforeMove = new IntPosition(ballXCoordinate + direction.getXChange(),
                ballYCoordinate + direction.getYChange());

        Position positionAfterMove = new IntPosition(ballXCoordinate + direction.getXChange(),
                ballYCoordinate + direction.getYChange());
        Position moveAbleEntityWhichWillBePushPositionAfterMove = new IntPosition(positionAfterMove.getXCoordinate() + direction.getXChange(),
                positionAfterMove.getYCoordinate() + direction.getYChange());


        actual.putOnPosition(movingEntity, positionBeforeMove);
        actual.putOnPosition(moveAbleEntity, moveAbleEntityWhichWillBePushPositionBeforeMove);


        excepted.putOnPosition(movingEntity, positionAfterMove);
        excepted.putOnPosition(moveAbleEntity, moveAbleEntityWhichWillBePushPositionAfterMove);

        Mover mover = new MoverImplementation();
        Logger.trace("actual before move:\n{}", actual.toString());
        Logger.trace("excepted before move:\n{}", excepted.toString());
        //When

        mover.move(positionBeforeMove, direction, actual);

        //Then
        Logger.trace("actual after move:\n{}", actual.toString());
        Logger.trace("excepted after move:\n{}", excepted.toString());
        assertEquals(actual, excepted);
    }

    @Test
    void TestMovePushingTwoMoveAbleWhitEntity() throws cantBeMovedException {
        //Given
        Direction direction = EnumDirection.RIGHT;
        Entity movingEntity = EntityImpl.PLAYER;
        Entity moveAbleEntity = EntityImpl.BALL;
        int ballXCoordinate = 0;
        int ballYCoordinate = 0;
        Position positionBeforeMove = new IntPosition(ballXCoordinate, ballYCoordinate);
        Position moveAbleEntityWhichWillBePushPositionBeforeMove = new IntPosition(ballXCoordinate + direction.getXChange(),
                ballYCoordinate + direction.getYChange());
        Position moveAbleEntityTwoWhichWillBePushPositionBeforeMove = new IntPosition(ballXCoordinate + direction.getXChange() * 2,
                ballYCoordinate + direction.getYChange() * 2);

        Position positionAfterMove = new IntPosition(ballXCoordinate + direction.getXChange(),
                ballYCoordinate + direction.getYChange());
        Position moveAbleEntityWhichWillBePushPositionAfterMove = new IntPosition(positionAfterMove.getXCoordinate() + direction.getXChange(),
                positionAfterMove.getYCoordinate() + direction.getYChange());
        Position moveAbleEntityTwoWhichWillBePushPositionAfterMove = new IntPosition(positionAfterMove.getXCoordinate() + direction.getXChange() * 2,
                positionAfterMove.getYCoordinate() + direction.getYChange() * 2);


        actual.putOnPosition(movingEntity, positionBeforeMove);
        actual.putOnPosition(moveAbleEntity, moveAbleEntityWhichWillBePushPositionBeforeMove);
        actual.putOnPosition(moveAbleEntity, moveAbleEntityTwoWhichWillBePushPositionBeforeMove);


        excepted.putOnPosition(movingEntity, positionAfterMove);
        excepted.putOnPosition(moveAbleEntity, moveAbleEntityWhichWillBePushPositionAfterMove);
        excepted.putOnPosition(moveAbleEntity, moveAbleEntityTwoWhichWillBePushPositionAfterMove);

        Mover mover = new MoverImplementation();
        Logger.trace("actual before move:\n{}", actual.toString());
        Logger.trace("excepted before move:\n{}", excepted.toString());

        //When

        mover.move(positionBeforeMove, direction, actual);

        //Then
        Logger.trace("actual after move:\n{}", actual.toString());
        Logger.trace("excepted after move:\n{}", excepted.toString());
        assertEquals(actual, excepted);
    }

    @Test
    void TestMoveWhenEntitiesMovingAndHitAunMoveAbleShouldNotChangeTheTable() throws cantBeMovedException {
        //Given
        Direction direction = EnumDirection.RIGHT;
        Entity movingEntity = EntityImpl.PLAYER;
        Entity moveAbleEntity = EntityImpl.BALL;
        Entity unMoveAbleEntity = EntityImpl.WALL;
        int ballXCoordinate = 0;
        int ballYCoordinate = 0;
        Position positionBeforeMove = new IntPosition(ballXCoordinate, ballYCoordinate);
        Position moveAbleEntityPosition = new IntPosition(ballXCoordinate + direction.getXChange(),
                ballYCoordinate + direction.getYChange());
        Position unMoveAbleEntityPosition = new IntPosition(ballXCoordinate + direction.getXChange() * 2,
                ballYCoordinate + direction.getYChange() * 2);


        actual.putOnPosition(movingEntity, positionBeforeMove);
        actual.putOnPosition(moveAbleEntity, moveAbleEntityPosition);
        actual.putOnPosition(unMoveAbleEntity, unMoveAbleEntityPosition);


        excepted.putOnPosition(movingEntity, positionBeforeMove);
        excepted.putOnPosition(moveAbleEntity, moveAbleEntityPosition);
        excepted.putOnPosition(unMoveAbleEntity, unMoveAbleEntityPosition);

        Mover mover = new MoverImplementation();
        Logger.trace("actual before move:\n{}", actual.toString());
        Logger.trace("excepted before move:\n{}", excepted.toString());

        //When

        try {

            mover.move(positionBeforeMove, direction, actual);
        } catch (Exception e) {

        }
        //Then
        Logger.trace("actual after move:\n{}", actual.toString());
        Logger.trace("excepted after move:\n{}", excepted.toString());
        assertEquals(actual, excepted);
    }

    @Test
    void TestMoveWhenEntitiesMovingAndHitAunMoveAbleShouldThrowCantBeMovedException() throws cantBeMovedException {
        //Given
        Direction direction = EnumDirection.RIGHT;
        Entity movingEntity = EntityImpl.PLAYER;
        Entity moveAbleEntity = EntityImpl.BALL;
        Entity unMoveAbleEntity = EntityImpl.WALL;
        int ballXCoordinate = 0;
        int ballYCoordinate = 0;
        Position positionBeforeMove = new IntPosition(ballXCoordinate, ballYCoordinate);
        Position moveAbleEntityPosition = new IntPosition(ballXCoordinate + direction.getXChange(),
                ballYCoordinate + direction.getYChange());
        Position unMoveAbleEntityPosition = new IntPosition(ballXCoordinate + direction.getXChange() * 2,
                ballYCoordinate + direction.getYChange() * 2);


        actual.putOnPosition(movingEntity, positionBeforeMove);
        actual.putOnPosition(moveAbleEntity, moveAbleEntityPosition);
        actual.putOnPosition(unMoveAbleEntity, unMoveAbleEntityPosition);


        excepted.putOnPosition(movingEntity, positionBeforeMove);
        excepted.putOnPosition(moveAbleEntity, moveAbleEntityPosition);
        excepted.putOnPosition(unMoveAbleEntity, unMoveAbleEntityPosition);

        Mover mover = new MoverImplementation();
        Logger.trace("actual before move:\n{}", actual.toString());
        Logger.trace("excepted before move:\n{}", excepted.toString());

        //When

        //Then
        Logger.trace("actual after move:\n{}", actual.toString());
        Logger.trace("excepted after move:\n{}", excepted.toString());
        assertThrows(cantBeMovedException.class, () -> mover.move(positionBeforeMove, direction, actual));
    }

    @Test
    void TestMoveWhenMovingEntitysHitAnUnMoveAbleEntityWhichIsNotObstacleShouldMoveThere() throws cantBeMovedException {
        Direction direction = EnumDirection.RIGHT;
        Entity movingEntity = EntityImpl.PLAYER;
        Entity moveAbleEntity = EntityImpl.BALL;
        Entity notObstacleEntity = EntityImpl.GOAL;
        int ballXCoordinate = 0;
        int ballYCoordinate = 0;
        Position positionBeforeMove = new IntPosition(ballXCoordinate, ballYCoordinate);
        Position moveAbleEntityPosition = new IntPosition(ballXCoordinate + direction.getXChange(),
                ballYCoordinate + direction.getYChange());
        Position notObstacleEntityPosition = new IntPosition(ballXCoordinate + direction.getXChange() * 2,
                ballYCoordinate + direction.getYChange() * 2);


        actual.putOnPosition(movingEntity, positionBeforeMove);
        actual.putOnPosition(moveAbleEntity, moveAbleEntityPosition);
        actual.putOnPosition(notObstacleEntity, notObstacleEntityPosition);

        Position positionAfterMove = new IntPosition(ballXCoordinate + direction.getXChange(),
                ballYCoordinate + direction.getYChange());

        excepted.putOnPosition(movingEntity, positionAfterMove);
        excepted.putOnPosition(notObstacleEntity, new IntPosition(positionAfterMove.getXCoordinate() + direction.getXChange(),
                positionAfterMove.getYCoordinate() + direction.getYChange()));
        excepted.putOnPosition(moveAbleEntity, new IntPosition(positionAfterMove.getXCoordinate() + direction.getXChange(),
                positionAfterMove.getYCoordinate() + direction.getYChange()));


        Mover mover = new MoverImplementation();
        Logger.trace("actual before move:\n{}", actual.toString());
        Logger.trace("excepted before move:\n{}", excepted.toString());

        //When
        mover.move(positionBeforeMove, direction, actual);
        //Then
        Logger.trace("actual after move:\n{}", actual.toString());
        Logger.trace("excepted after move:\n{}", excepted.toString());
        assertEquals(actual, excepted);
    }
    @Test
    void TestMoveWhenMovingEntityHitAnUnMoveAbleEntityWhichIsNotObstacleShouldMoveThere() throws cantBeMovedException {
        Direction direction = EnumDirection.RIGHT;
        Entity movingEntity = EntityImpl.PLAYER;

        Entity notObstacleEntity = EntityImpl.GOAL;
        int ballXCoordinate = 0;
        int ballYCoordinate = 0;
        Position positionBeforeMove = new IntPosition(ballXCoordinate, ballYCoordinate);

        Position notObstacleEntityPosition = new IntPosition(ballXCoordinate + direction.getXChange() ,
                ballYCoordinate + direction.getYChange() );


        actual.putOnPosition(movingEntity, positionBeforeMove);

        actual.putOnPosition(notObstacleEntity, notObstacleEntityPosition);

        Position positionAfterMove = new IntPosition(ballXCoordinate + direction.getXChange(),
                ballYCoordinate + direction.getYChange());


        excepted.putOnPosition(notObstacleEntity, new IntPosition(positionAfterMove.getXCoordinate() ,
                positionAfterMove.getYCoordinate() ));
        excepted.putOnPosition(movingEntity, positionAfterMove);


        Mover mover = new MoverImplementation();
        Logger.trace("actual before move:\n{}", actual.toString());
        Logger.trace("excepted before move:\n{}", excepted.toString());

        //When
        mover.move(positionBeforeMove, direction, actual);
        //Then
        Logger.trace("actual after move:\n{}", actual.toString());
        Logger.trace("excepted after move:\n{}", excepted.toString());
        assertEquals(actual, excepted);
    }

    @Test
    void TestMovingThroughNoneObstacleEntity() throws cantBeMovedException {
        //Given
        Direction direction = EnumDirection.RIGHT;
        Entity movingEntity = EntityImpl.PLAYER;
        Entity notObstacleEntity = EntityImpl.GOAL;
        int x = 1;
        int y = 1;
        Position position = new IntPosition(x, y);

        actual.putOnPosition(movingEntity, position);
        actual.putOnPosition(notObstacleEntity, new IntPosition(x + direction.getXChange(), y + direction.getYChange()));

        excepted.putOnPosition(notObstacleEntity, new IntPosition(x + direction.getXChange(), y + direction.getYChange()));
        excepted.putOnPosition(movingEntity, new IntPosition(x + direction.getXChange() + direction.getXChange(),
                y + direction.getYChange() + direction.getYChange()));

        Mover mover = new MoverImplementation();
        Logger.trace("actual before move:\n{}", actual.toString());
        Logger.trace("excepted before move:\n{}", excepted.toString());

       //When
        mover.move(position, direction, actual);
        mover.move(new IntPosition(x + direction.getXChange(), y + direction.getYChange()), direction, actual);
        //Then
        Logger.trace("actual after move:\n{}", actual.toString());
        Logger.trace("excepted after move:\n{}", excepted.toString());
        assertEquals(actual, excepted);

    }
}