package model.moverImpl;

import model.*;
import model.directionImpl.EnumDirection;
import model.entityImpl.asEnum.EntityImpl;
import model.positionImpl.IntPosition;
import model.tableImpl.TableImpl;
import org.junit.jupiter.api.Test;
import org.tinylog.Logger;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MoverImplementationTest {

    @Test
    void testMoveOnePlayerUp() throws cantBeMovedException {
        //Given
        Direction upDirection = EnumDirection.UP;
        Entity movingEntity = EntityImpl.PLAYER;
        int xCoordinate = 2;
        int yCoordinate = 2;
        Position positionBeforeMove = new IntPosition(xCoordinate, yCoordinate);
        Position positionAfterMove = new IntPosition(xCoordinate + upDirection.getXChange(), yCoordinate + upDirection.getYChange());

        Table excepted = new TableImpl();
        excepted.putOnPosition(movingEntity, positionAfterMove);
        Table actual = new TableImpl();
        actual.putOnPosition(movingEntity, positionBeforeMove);

        Mover mover = new MoverImplementation();
        //When

       // mover.move(positionBeforeMove, upDirection, actual);

        //Then
        Logger.info("actual:{} ,{}",actual.getPlayerPosition().getXCoordinate(),actual.getPlayerPosition().getYCoordinate());
        Logger.info("excepted:{} ,{}",excepted.getPlayerPosition().getXCoordinate(),excepted.getPlayerPosition().getYCoordinate());
        assertEquals(actual,excepted);


    }
}