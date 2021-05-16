package model.tableImpl;

import javafx.beans.property.ReadOnlyObjectWrapper;
import sokoban.model.Entity;
import sokoban.model.Position;
import sokoban.model.Table;
import sokoban.model.entityImpl.asEnum.EntityImpl;
import sokoban.model.positionImpl.IntPosition;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sokoban.model.tableImpl.TableImpl;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class TableImplTest {

    Table table;
    Table table1;
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
        table = new TableImpl(boardSize, table2d, entity);
        table1 = new TableImpl(boardSize, table2d1, entity);
    }


    @Test
    void isObstacleOnPosition() {//Given

        Entity goal = EntityImpl.GOAL;
        Position positionGoal = new IntPosition(2, 2);
        Entity player = EntityImpl.PLAYER;
        Position positionPlayer = new IntPosition(3, 2);
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
        Entity wall = EntityImpl.WALL;
        Position positionWall = new IntPosition(2, 2);
        Entity player = EntityImpl.PLAYER;
        Position positionPlayer = new IntPosition(3, 2);
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

        Entity wall = EntityImpl.WALL;
        Position position = new IntPosition(2, 2);
        table.putOnPosition(wall, position);
        //When

        //Then
        assertTrue(table.isEmptyOnPosition(new IntPosition(0, 0)));
        assertFalse(table.isEmptyOnPosition(position));
    }

    @Test
    void getEntityFromPosition() {
        //Given

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

        Entity wall = EntityImpl.WALL;
        Position positionWall = new IntPosition(2, 2);
        Entity player = EntityImpl.PLAYER;
        Position positionPlayer = new IntPosition(3, 2);
        Position positionBall1 = new IntPosition(0, 0);
        Position positionBall2 = new IntPosition(4, 3);
        Position positionBall3 = new IntPosition(2, 3);
        table.putOnPosition(player, positionPlayer);
        table.putOnPosition(wall, positionWall);
        table.putOnPosition(EntityImpl.GOAL, new IntPosition(4, 4));
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
        assertEquals(excepted, actual);
    }

    @Test
    void getGoalPositions() {
        //Given

        Entity wall = EntityImpl.WALL;
        Position positionWall = new IntPosition(2, 2);
        Entity player = EntityImpl.PLAYER;
        Position positionPlayer = new IntPosition(3, 2);
        Position positionGoal1 = new IntPosition(0, 0);
        Position positionGoal2 = new IntPosition(4, 3);
        Position positionGoal3 = new IntPosition(2, 3);
        table.putOnPosition(player, positionPlayer);
        table.putOnPosition(wall, positionWall);
        table.putOnPosition(EntityImpl.BALL, new IntPosition(4, 4));
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
        assertEquals(excepted, actual);
    }

    @Test
    void testEqualsWhenTheTwoTableIsEquals() {
        //Given

        Entity wall = EntityImpl.WALL;
        Position positionWall = new IntPosition(2, 2);
        Entity player = EntityImpl.PLAYER;
        Position positionPlayer = new IntPosition(3, 2);
        Position positionBall1 = new IntPosition(0, 0);
        Position positionBall2 = new IntPosition(4, 3);
        Position positionBall3 = new IntPosition(2, 3);
        table1.putOnPosition(player, positionPlayer);
        table1.putOnPosition(wall, positionWall);
        table1.putOnPosition(EntityImpl.GOAL, new IntPosition(4, 4));
        table1.putOnPosition(EntityImpl.BALL, positionBall1);
        table1.putOnPosition(EntityImpl.BALL, positionBall2);
        table1.putOnPosition(EntityImpl.BALL, positionBall3);


        table.putOnPosition(player, positionPlayer);
        table.putOnPosition(wall, positionWall);
        table.putOnPosition(EntityImpl.GOAL, new IntPosition(4, 4));
        table.putOnPosition(EntityImpl.BALL, positionBall1);
        table.putOnPosition(EntityImpl.BALL, positionBall2);
        table.putOnPosition(EntityImpl.BALL, positionBall3);

        //When

        //Then
        assertTrue(table.equals(table1));
        assertEquals(table, table1);

    }

    @Test
    void testEqualsWhenTheTwoTableIsNotEquals() {
        //Given
        Entity wall = EntityImpl.WALL;
        Position positionWall = new IntPosition(2, 2);
        Entity player = EntityImpl.PLAYER;
        Position positionPlayer = new IntPosition(3, 2);
        Position positionBall1 = new IntPosition(0, 0);
        Position positionBall2 = new IntPosition(1, 3);
        Position positionBall3 = new IntPosition(2, 3);
        table1.putOnPosition(player, positionPlayer);
        table1.putOnPosition(wall, positionWall);
        table1.putOnPosition(EntityImpl.GOAL, new IntPosition(3, 4));
        table1.putOnPosition(EntityImpl.BALL, positionBall1);
        table1.putOnPosition(EntityImpl.BALL, positionBall2);
        table1.putOnPosition(EntityImpl.BALL, positionBall3);


        table.putOnPosition(player, positionPlayer);
        table.putOnPosition(wall, positionWall);
        table.putOnPosition(EntityImpl.GOAL, new IntPosition(4, 4));
        table.putOnPosition(EntityImpl.BALL, positionBall1);
        table.putOnPosition(EntityImpl.BALL, positionBall2);
        table.putOnPosition(EntityImpl.BALL, positionBall3);

        //When

        //Then
        assertFalse(table.equals(table1));
        assertNotEquals(table, table1);

    }

    @Test
    void putOnPositionWhitPushDown() {
        //Given
        table.putOnPosition(EntityImpl.GOAL, new IntPosition(4, 4));
        //When
        table.putOnPosition(EntityImpl.BALL, new IntPosition(4, 4));
        //Then
        assertEquals(table.getEntityFromPosition(new IntPosition(4, 4)), EntityImpl.BALL);


    }

    @Test
    void removingFromPushDownPosition() {
        //Given
        table.putOnPosition(EntityImpl.GOAL, new IntPosition(4, 4));
        table1.putOnPosition(EntityImpl.GOAL, new IntPosition(4, 4));
        table.putOnPosition(EntityImpl.BALL, new IntPosition(4, 4));
        //When
        table.removeFromPosition(new IntPosition(4, 4));
        //Then
        assertEquals(table, table1);

    }

    @Test
    void testGetGoalPositionsWhitPushDown() {
        //Given

        Entity wall = EntityImpl.WALL;
        Position positionWall = new IntPosition(2, 2);
        Entity player = EntityImpl.PLAYER;
        Position positionPlayer = new IntPosition(3, 2);
        Position positionGoal1 = new IntPosition(0, 0);
        Position positionGoal2 = new IntPosition(4, 3);
        Position positionGoal3 = new IntPosition(2, 3);
        table.putOnPosition(player, positionPlayer);
        table.putOnPosition(wall, positionWall);
        table.putOnPosition(EntityImpl.BALL, new IntPosition(4, 3));
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
        assertEquals(excepted, actual);

    }

    @Test
    void testCreat2DRepresentationISWorkingRight() {

        Position position = new IntPosition(2, 3);
        table.putOnPosition(EntityImpl.GOAL, position);

        assertEquals(EntityImpl.GOAL, table2d[position.getXCoordinate()][position.getYCoordinate()].getValue());

        table.putOnPosition(EntityImpl.BALL, position);
        assertEquals(EntityImpl.BALL, table2d[position.getXCoordinate()][position.getYCoordinate()].getValue());

        table.removeFromPosition(position);
        assertEquals(EntityImpl.GOAL, table2d[position.getXCoordinate()][position.getYCoordinate()].getValue());

        table.removeFromPosition(position);
        assertEquals(EntityImpl.NONE, table2d[position.getXCoordinate()][position.getYCoordinate()].getValue());
    }
}