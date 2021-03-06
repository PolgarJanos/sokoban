package model;

import sokoban.Configuration;
import sokoban.model.*;
import sokoban.model.directionImpl.EnumDirection;
import sokoban.model.entityImpl.asEnum.EntityImpl;
import sokoban.model.positionImpl.IntPosition;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.tinylog.Logger;

import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SokobanGameModelTest {

    SokobanGameModel excepted;
    SokobanGameModel actual;

    @BeforeEach
    void init() {
        List<EntityWrapper> entityWrapperList = new LinkedList<>();
        entityWrapperList.add(new EntityWrapper(EntityImpl.PLAYER, new IntPosition(1, 1)));
        entityWrapperList.add(new EntityWrapper(EntityImpl.BALL, new IntPosition(1, 2)));
        entityWrapperList.add(new EntityWrapper(EntityImpl.GOAL, new IntPosition(1, 3)));
        actual = new SokobanGameModel(entityWrapperList);
    }

    @Test
    void getReadOnlyGameOverPropertyWhenAfterInitShouldGiveBackFalse() {
        Logger.trace("{}", actual.toString());
        assertFalse(actual.GetReadOnlyGameOverProperty().getValue());
    }

    @Test
    void testEqualsWhenTheTwoObjectIsEqualShouldReturnTrue() {
        //Given
        List<EntityWrapper> entityWrapperList = new LinkedList<>();
        entityWrapperList.add(new EntityWrapper(EntityImpl.PLAYER, new IntPosition(1, 1)));
        entityWrapperList.add(new EntityWrapper(EntityImpl.BALL, new IntPosition(1, 2)));
        entityWrapperList.add(new EntityWrapper(EntityImpl.GOAL, new IntPosition(1, 3)));
        excepted = new SokobanGameModel(entityWrapperList);
        //When
        //Then
        assertTrue(actual.equals(excepted));
    }

    @Test
    void testEqualsWhenTheTwoObjectIsNotEqualShouldReturnFalse() {
        //Given
        List<EntityWrapper> entityWrapperList = new LinkedList<>();
        entityWrapperList.add(new EntityWrapper(EntityImpl.PLAYER, new IntPosition(1, 1)));
        entityWrapperList.add(new EntityWrapper(EntityImpl.BALL, new IntPosition(1, 2)));
        entityWrapperList.add(new EntityWrapper(EntityImpl.GOAL, new IntPosition(1, 3)));
        entityWrapperList.add(new EntityWrapper(EntityImpl.GOAL, new IntPosition(1, 4)));
        excepted = new SokobanGameModel(entityWrapperList);
        //When
        //Then
        assertFalse(actual.equals(excepted));
        //Given
        entityWrapperList = new LinkedList<>();
        entityWrapperList.add(new EntityWrapper(EntityImpl.PLAYER, new IntPosition(1, 1)));
        entityWrapperList.add(new EntityWrapper(EntityImpl.BALL, new IntPosition(1, 2)));
        entityWrapperList.add(new EntityWrapper(EntityImpl.GOAL, new IntPosition(2, 3)));

        excepted = new SokobanGameModel(entityWrapperList);
        //When
        //Then
        assertFalse(actual.equals(excepted));
    }

    @Test
    void moveLeft() throws CantBeMovedException {
        //Given
        Direction direction = EnumDirection.LEFT;
        List<EntityWrapper> entityWrapperList = new LinkedList<>();
        entityWrapperList.add(new EntityWrapper(EntityImpl.PLAYER, new IntPosition(1 + direction.getXChange(), 1 + direction.getYChange())));
        entityWrapperList.add(new EntityWrapper(EntityImpl.BALL, new IntPosition(1, 2)));
        entityWrapperList.add(new EntityWrapper(EntityImpl.GOAL, new IntPosition(1, 3)));
        excepted = new SokobanGameModel(entityWrapperList);

        //When
        Logger.trace("Before move\n{}", actual.toString());
        actual.move(direction);
        Logger.trace("After move\n{}", actual.toString());
        Logger.trace("Excepted\n{}", excepted.toString());
        //Then


        assertEquals(excepted, actual);

    }

    @Test
    void moveUp() throws CantBeMovedException {
        //Given
        Direction direction = EnumDirection.UP;
        List<EntityWrapper> entityWrapperList = new LinkedList<>();
        entityWrapperList.add(new EntityWrapper(EntityImpl.PLAYER, new IntPosition(1 + direction.getXChange(), 1 + direction.getYChange())));
        entityWrapperList.add(new EntityWrapper(EntityImpl.BALL, new IntPosition(1, 2)));
        entityWrapperList.add(new EntityWrapper(EntityImpl.GOAL, new IntPosition(1, 3)));
        excepted = new SokobanGameModel(entityWrapperList);

        //When
        Logger.trace("Before move\n{}", actual.toString());
        actual.move(direction);
        Logger.trace("After move\n{}", actual.toString());
        Logger.trace("Excepted\n{}", excepted.toString());
        //Then


        assertEquals(excepted, actual);

    }

    @Test
    void moveRight() throws CantBeMovedException {
        //Given
        Direction direction = EnumDirection.RIGHT;
        List<EntityWrapper> entityWrapperList = new LinkedList<>();
        entityWrapperList.add(new EntityWrapper(EntityImpl.PLAYER, new IntPosition(1 + direction.getXChange(), 1 + direction.getYChange())));
        entityWrapperList.add(new EntityWrapper(EntityImpl.BALL, new IntPosition(1, 2)));
        entityWrapperList.add(new EntityWrapper(EntityImpl.GOAL, new IntPosition(1, 3)));
        excepted = new SokobanGameModel(entityWrapperList);

        //When
        Logger.trace("Before move\n{}", actual.toString());
        actual.move(direction);
        Logger.trace("After move\n{}", actual.toString());
        Logger.trace("Excepted\n{}", excepted.toString());
        //Then


        assertEquals(excepted, actual);

    }

    @Test
    void moveDown() throws CantBeMovedException {
        //Given
        Direction direction = EnumDirection.DOWN;

        List<EntityWrapper> entityWrapperList = new LinkedList<>();
        entityWrapperList.add(new EntityWrapper(EntityImpl.PLAYER, new IntPosition(1 + direction.getXChange(), 1 + direction.getYChange())));
        entityWrapperList.add(new EntityWrapper(EntityImpl.BALL, new IntPosition(1 + direction.getXChange(), 2 + direction.getYChange())));

        excepted = new SokobanGameModel(entityWrapperList);

        //When
        Logger.trace("Before move\n{}", actual.toString());
        actual.move(direction);
        Logger.trace("After move\n{}", actual.toString());
        Logger.trace("Excepted\n{}", excepted.toString());
        //Then


        assertEquals(excepted, actual);

    }

    @Test
    void getReadOnlyGameOverPropertyAfterANoWiningMoveShouldGiveBackFalse() throws CantBeMovedException {
        //Given
        Direction direction = EnumDirection.RIGHT;
        //When
        Logger.trace("Before move\n{}", actual.toString());
        actual.move(direction);
        Logger.trace("After move\n{}", actual.toString());

        //Then

        assertFalse(actual.GetReadOnlyGameOverProperty().getValue());
    }

    @Test
    void getReadOnlyGameOverPropertyAfterANoWiningWhitPushingBallMoveShouldGiveBackFalse() throws CantBeMovedException {
        //Given
        Direction direction = EnumDirection.DOWN;
        List<EntityWrapper> entityWrapperList = new LinkedList<>();
        entityWrapperList.add(new EntityWrapper(EntityImpl.PLAYER, new IntPosition(1, 1)));
        entityWrapperList.add(new EntityWrapper(EntityImpl.BALL, new IntPosition(1, 2)));
        entityWrapperList.add(new EntityWrapper(EntityImpl.GOAL, new IntPosition(1, 4)));
        actual = new SokobanGameModel(entityWrapperList);


        //When
        Logger.trace("Before move\n{}", actual.toString());
        actual.move(direction);
        Logger.trace("After move\n{}", actual.toString());

        //Then


        assertFalse(actual.GetReadOnlyGameOverProperty().getValue());
    }

    @Test
    void getReadOnlyGameOverPropertyAfterAWiningMoveShouldGiveBackTrue() throws CantBeMovedException {
        //Given
        Direction direction = EnumDirection.DOWN;

        //When
        Logger.trace("Before move\n{}", actual.toString());
        actual.move(direction);
        Logger.trace("After move\n{}", actual.toString());

        //Then
        assertTrue(actual.GetReadOnlyGameOverProperty().getValue());
    }
    @Test
    void TestConstructors(){
        //Given
        //When
        actual = new SokobanGameModel(Configuration.ValuableEntities());
        excepted = new SokobanGameModel();
        //Then
        assertEquals(actual,excepted);
    }

    @Test
    void TestHashCode(){
        //Given
        //When
        actual = new SokobanGameModel(Configuration.ValuableEntities());
        excepted = new SokobanGameModel();
        //Then
        assertEquals(actual.hashCode(),excepted.hashCode());
    }


}