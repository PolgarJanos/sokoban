package model.entityImpl;

import sokoban.model.Entity;
import sokoban.model.entityImpl.asEnum.EntityImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class NoneTest {

    public static Entity entity;

    @BeforeEach
    void init() {
        entity = EntityImpl.NONE;
    }

    @Test
    void isPlayer() {
        assertFalse(entity.isPlayer());
    }

    @Test
    void isGoal() {
        assertFalse(entity.isGoal());
    }

    @Test
    void isBall() {
        assertFalse(entity.isBall());
    }

    @Test
    void isObstacle() {
        assertFalse(entity.isObstacle());
    }

    @Test
    void isMoveAble() {
        assertFalse(entity.isMoveAble());
    }

    @Test
    void isNothing() {
        assertTrue(entity.isNothing());
    }

    @Test
    void giveBackNone(){
        assertEquals(entity,entity.giveBackNone());
    }
}

