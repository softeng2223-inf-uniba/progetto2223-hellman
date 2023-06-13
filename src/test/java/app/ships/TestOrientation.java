package app.ships;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import it.uniba.app.ships.Orientation;

public class TestOrientation {

    @Test
    public void testValues() {
        Orientation[] orientations = Orientation.values();
        assertEquals(2, orientations.length);
        assertEquals(Orientation.HORIZONTAL, orientations[0]);
        assertEquals(Orientation.VERTICAL, orientations[1]);
    }

    @Test
    public void testToString() {
        assertEquals("HORIZONTAL", Orientation.HORIZONTAL.toString());
        assertEquals("VERTICAL", Orientation.VERTICAL.toString());
    }

    @Test
    public void testValueOf() {
        assertEquals(Orientation.HORIZONTAL, Orientation.valueOf("HORIZONTAL"));
        assertEquals(Orientation.VERTICAL, Orientation.valueOf("VERTICAL"));
    }
}



