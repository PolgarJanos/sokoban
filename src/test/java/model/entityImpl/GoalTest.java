package model.entityImpl;

import model.Entity;
import model.entityImpl.asEnum.EntityImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class GoalTest {

    public static Entity entity;

    @BeforeEach
    void init() {
        entity = EntityImpl.GOAL;
    }

    @Test
    void isPlayer() {
        assertFalse(entity.isPlayer());
    }

    @Test
    void isGoal() {
        assertTrue(entity.isGoal());
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
}
