package model.entityImpl;

import model.Entity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BallTest {

    public static Entity entity;

    @BeforeEach
    void init() {
        entity = EntityImpl.Ball;
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
        assertTrue(entity.isBall());
    }

    @Test
    void isObstacle() {
        assertTrue(entity.isObstacle());
    }

    @Test
    void isMoveAble() {
        assertTrue(entity.isMoveAble());
    }
}
