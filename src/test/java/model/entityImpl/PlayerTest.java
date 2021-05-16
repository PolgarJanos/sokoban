package model.entityImpl;

import sokoban.model.Entity;
import sokoban.model.entityImpl.asEnum.EntityImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PlayerTest {

    public static Entity entity;

    @BeforeEach
    void init() {
        entity = EntityImpl.PLAYER;
    }

    @Test
    void isPlayer() {
        assertTrue(entity.isPlayer());
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
        assertTrue(entity.isObstacle());
    }

    @Test
    void isMoveAble() {
        assertTrue(entity.isMoveAble());
    }

    @Test
    void isNothing() {
        assertFalse(entity.isNothing());
    }
}

