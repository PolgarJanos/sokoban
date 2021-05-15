package model;

import model.entityImpl.asEnum.EntityImpl;
import model.positionImpl.IntPosition;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EntityWrapperTest {

    EntityWrapper actual;
    Position actualPosition;
    Entity actualEntity;
    @BeforeEach
    void init(){
        actualEntity= EntityImpl.BALL;
        actualPosition = new IntPosition(1,2);
        actual =new EntityWrapper(actualEntity,actualPosition);
    }


    @Test
    void getXCoordinate() {
        assertEquals(actual.getXCoordinate(),actualPosition.getXCoordinate());
    }

    @Test
    void getYCoordinate() {
        assertEquals(actual.getYCoordinate(),actualPosition.getYCoordinate());
    }

    @Test
    void getEntity() {
        assertEquals(actual.getEntity(),actualEntity);
    }

    @Test
    void testEquals() {
        EntityWrapper other = new EntityWrapper(actualEntity,actualPosition);

        assertTrue(actual.equals(other));
    }

    @Test
    void testHashCode() {
        EntityWrapper other = new EntityWrapper(actualEntity,actualPosition);
        assertEquals(actual.hashCode(),other.hashCode());
    }
}