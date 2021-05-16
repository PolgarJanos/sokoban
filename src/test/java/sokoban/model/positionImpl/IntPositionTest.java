package sokoban.model.positionImpl;

import org.junit.jupiter.api.Test;
import sokoban.model.Position;

import static org.junit.jupiter.api.Assertions.*;

class IntPositionTest {

    @Test
    void testEquals() {
        //Given
        Position actual= new IntPosition(1,1);;
        Position equalToActual = new IntPosition(1,1);
        Position notEqualedToActual = new IntPosition(2,1);
        Position notEqualToActual2 = new IntPosition(1,2);
        Position notEqualToActual3 = new IntPosition(4,10);
        //When
        //Then
        assertTrue(actual.equals(equalToActual));
        assertFalse(actual.equals(notEqualedToActual));
        assertFalse(actual.equals(notEqualToActual2));
        assertFalse(actual.equals(notEqualToActual3));
    }

    @Test
    void testHashCode() {
        //Given
        Position actual= new IntPosition(1,1);;
        Position excepted = new IntPosition(1,1);
        //When
        //Then
        assertEquals(excepted.hashCode(),actual.hashCode());
    }
}