package model;

import javafx.beans.property.*;
import javafx.collections.ObservableSet;
import model.directionImpl.EnumDirection;
import model.entityImpl.asEnum.EntityImpl;
import model.positionImpl.IntPosition;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.tinylog.Logger;

import java.util.HashSet;
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
    void moveLeft() throws cantBeMovedException {
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
    void moveUp() throws cantBeMovedException {
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
    void moveRight() throws cantBeMovedException {
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
    void moveDown() throws cantBeMovedException {
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
    void getReadOnlyGameOverPropertyAfterANoWiningMoveShouldGiveBackFalse() throws cantBeMovedException {
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
    void getReadOnlyGameOverPropertyAfterANoWiningWhitPushingBallMoveShouldGiveBackFalse() throws cantBeMovedException {
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
    void getReadOnlyGameOverPropertyAfterAWiningMoveShouldGiveBackTrue() throws cantBeMovedException {
        //Given
        Direction direction = EnumDirection.DOWN;



        //When
        Logger.trace("Before move\n{}", actual.toString());
        actual.move(direction);
        Logger.trace("After move\n{}", actual.toString());

        //Then


        assertTrue(actual.GetReadOnlyGameOverProperty().getValue());
    }



}