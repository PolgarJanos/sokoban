package model.tableImpl;

import model.Entity;
import model.Position;
import model.Table;
import model.entityImpl.asEnum.EntityImpl;
import model.positionImpl.IntPosition;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class TableImplTest {


    @Test
    void isObstacleOnPosition() {//Given
        Table table = new TableImpl();
        Entity goal = EntityImpl.GOAL;
        Position positionGoal = new IntPosition(2, 2);
        Entity player = EntityImpl.PLAYER;
        Position positionPlayer = new IntPosition(3,2);
        table.putOnPosition(player, positionPlayer);
        table.putOnPosition(goal, positionGoal);
        //When

        //Then
        assertTrue(table.isObstacleOnPosition(positionPlayer));
        assertFalse(table.isObstacleOnPosition(positionGoal));
    }

    @Test
    void isMoveAbleOnPosition() {
        //Given
        Table table = new TableImpl();
        Entity wall = EntityImpl.WALL;
        Position positionWall = new IntPosition(2, 2);
        Entity player = EntityImpl.PLAYER;
        Position positionPlayer = new IntPosition(3,2);
        table.putOnPosition(player, positionPlayer);
        table.putOnPosition(wall, positionWall);
        //When

        //Then
        assertTrue(table.isMoveAbleOnPosition(positionPlayer));
        assertFalse(table.isMoveAbleOnPosition(positionWall));
    }

    @Test
    void isEmptyOnPosition() {
        //Given
        Table table = new TableImpl();
        Entity wall = EntityImpl.WALL;
        Position position = new IntPosition(2, 2);
        table.putOnPosition(wall, position);
        //When

        //Then
        assertTrue(table.isEmptyOnPosition(new IntPosition(0,0)));
        assertFalse(table.isEmptyOnPosition(position));
    }

    @Test
    void getEntityFromPosition() {
        //Given
        Table table = new TableImpl();
        Entity player = EntityImpl.PLAYER;
        Position position = new IntPosition(2, 2);
        table.putOnPosition(player, position);
        //When
        Entity actual = table.getEntityFromPosition(position);
        //Then
        assertEquals(actual, player);
    }

    @Test
    void testWhenPositionIsOutOfBoundaryShouldThrowIllegalArgumentException() {

        Table table = new TableImpl();
        Entity player = EntityImpl.PLAYER;
        Position position = new IntPosition(Integer.MAX_VALUE, Integer.MAX_VALUE);


        assertThrows(IllegalArgumentException.class, () -> table.isObstacleOnPosition(position));
        assertThrows(IllegalArgumentException.class, () -> table.isMoveAbleOnPosition(position));
        assertThrows(IllegalArgumentException.class, () -> table.isEmptyOnPosition(position));
        assertThrows(IllegalArgumentException.class, () -> table.getEntityFromPosition(position));
        assertThrows(IllegalArgumentException.class, () -> table.putOnPosition(player, position));
        assertThrows(IllegalArgumentException.class, () -> table.removeFromPosition(position));

    }

    @Test
    void testWhenPositionIsNegativeShouldThrowIllegalArgumentException() {

        Table table = new TableImpl();
        Entity player = EntityImpl.PLAYER;
        Position position = new IntPosition(-1, -1);


        assertThrows(IllegalArgumentException.class, () -> table.isObstacleOnPosition(position));
        assertThrows(IllegalArgumentException.class, () -> table.isMoveAbleOnPosition(position));
        assertThrows(IllegalArgumentException.class, () -> table.isEmptyOnPosition(position));
        assertThrows(IllegalArgumentException.class, () -> table.getEntityFromPosition(position));
        assertThrows(IllegalArgumentException.class, () -> table.putOnPosition(player, position));
        assertThrows(IllegalArgumentException.class, () -> table.removeFromPosition(position));

    }

    @Test
    void testWhenPositionSecondCoordinateOutOfBoundaryShouldThrowIllegalArgumentException() {

        Table table = new TableImpl();
        Entity player = EntityImpl.PLAYER;
        Position position = new IntPosition(0, -1);


        assertThrows(IllegalArgumentException.class, () -> table.isObstacleOnPosition(position));
        assertThrows(IllegalArgumentException.class, () -> table.isMoveAbleOnPosition(position));
        assertThrows(IllegalArgumentException.class, () -> table.isEmptyOnPosition(position));
        assertThrows(IllegalArgumentException.class, () -> table.getEntityFromPosition(position));
        assertThrows(IllegalArgumentException.class, () -> table.putOnPosition(player, position));
        assertThrows(IllegalArgumentException.class, () -> table.removeFromPosition(position));

    }

    @Test
    void testWhenPositionFirstCoordinateOutOfBoundaryShouldThrowIllegalArgumentException() {

        Table table = new TableImpl();
        Entity player = EntityImpl.PLAYER;
        Position position = new IntPosition(Integer.MAX_VALUE, 0);


        assertThrows(IllegalArgumentException.class, () -> table.isObstacleOnPosition(position));
        assertThrows(IllegalArgumentException.class, () -> table.isMoveAbleOnPosition(position));
        assertThrows(IllegalArgumentException.class, () -> table.isEmptyOnPosition(position));
        assertThrows(IllegalArgumentException.class, () -> table.getEntityFromPosition(position));
        assertThrows(IllegalArgumentException.class, () -> table.putOnPosition(player, position));
        assertThrows(IllegalArgumentException.class, () -> table.removeFromPosition(position));

    }

    @Test
    void putOnPosition() {
        //Given
        Table table = new TableImpl();
        Entity player = EntityImpl.PLAYER;
        Position position = new IntPosition(2, 2);
        //When
        table.putOnPosition(player, position);
        //Then
        assertEquals(table.getEntityFromPosition(position), player);

    }

    @Test
    void removeFromPosition() {
        //Given
        Table table = new TableImpl();
        Entity ball = EntityImpl.BALL;
        Position position = new IntPosition(2, 2);
        table.putOnPosition(ball, position);
        Entity excepted = EntityImpl.NONE;
        //When
        table.removeFromPosition(position);
        //Then
        assertEquals(excepted, table.getEntityFromPosition(position));
    }

    @Test
    void getPlayerPosition() {
        //Given
        Table table = new TableImpl();
        Entity player = EntityImpl.PLAYER;
        Position position = new IntPosition(2, 2);
        table.putOnPosition(player, position);
        //When

        //Then
        assertEquals(table.getPlayerPosition(), position);

    }

    @Test
    void getBallPositions() {
        //Given
        Table table = new TableImpl();
        Entity wall = EntityImpl.WALL;
        Position positionWall = new IntPosition(2, 2);
        Entity player = EntityImpl.PLAYER;
        Position positionPlayer = new IntPosition(3,2);
        Position positionBall1 = new IntPosition(0,0);
        Position positionBall2 = new IntPosition(4,3);
        Position positionBall3 = new IntPosition(2,3);
        table.putOnPosition(player, positionPlayer);
        table.putOnPosition(wall, positionWall);
        table.putOnPosition(EntityImpl.GOAL, new IntPosition(4,4));
        table.putOnPosition(EntityImpl.BALL, positionBall1);
        table.putOnPosition(EntityImpl.BALL, positionBall2);
        table.putOnPosition(EntityImpl.BALL, positionBall3);

        Set<Position> excepted = new HashSet<>();
        excepted.add(positionBall1);
        excepted.add(positionBall2);
        excepted.add(positionBall3);
        //When
        Set<Position> actual = table.getBallPositions();
        //Then
        assertEquals(excepted,actual);
    }

    @Test
    void getGoalPositions() {
        //Given
        Table table = new TableImpl();
        Entity wall = EntityImpl.WALL;
        Position positionWall = new IntPosition(2, 2);
        Entity player = EntityImpl.PLAYER;
        Position positionPlayer = new IntPosition(3,2);
        Position positionGoal1 = new IntPosition(0,0);
        Position positionGoal2 = new IntPosition(4,3);
        Position positionGoal3 = new IntPosition(2,3);
        table.putOnPosition(player, positionPlayer);
        table.putOnPosition(wall, positionWall);
        table.putOnPosition(EntityImpl.BALL, new IntPosition(4,4));
        table.putOnPosition(EntityImpl.GOAL, positionGoal1);
        table.putOnPosition(EntityImpl.GOAL, positionGoal2);
        table.putOnPosition(EntityImpl.GOAL, positionGoal3);

        Set<Position> excepted = new HashSet<>();
        excepted.add(positionGoal1);
        excepted.add(positionGoal2);
        excepted.add(positionGoal3);
        //When
        Set<Position> actual = table.getGoalPositions();
        //Then
        assertEquals(excepted,actual);
    }
}