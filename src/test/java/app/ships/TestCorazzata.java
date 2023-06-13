package app.ships;

import it.uniba.app.exceptions.IllegalPositionException;
import it.uniba.app.util.Pair;
import it.uniba.app.ships.Corazzata;
import it.uniba.app.ships.Orientation;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TestCorazzata {

    @Test
    public void testConstructor() {
        boolean[][] grid = new boolean[10][10];
        Pair startingPosition = new Pair('a', 0);

        try {
            Corazzata corazzata = new Corazzata(Orientation.HORIZONTAL, startingPosition, grid);
            assertNotNull(corazzata);
        } catch (IllegalPositionException e) {
            fail("Unexpected IllegalPositionException");
        }
    }

    @Test
    public void testConstructorIllegalPosition() {
        boolean[][] grid = new boolean[10][10];
        Pair startingPosition = new Pair('i', 9);

        assertThrows(IllegalPositionException.class, () -> {
            Corazzata corazzata = new Corazzata(Orientation.HORIZONTAL, startingPosition, grid);
        });
    }
}
