package app.ships;

import it.uniba.app.exceptions.IllegalPositionException;
import it.uniba.app.util.Pair;
import it.uniba.app.ships.Cacciatorpediniere;
import it.uniba.app.ships.Orientation;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TestCacciatorpediniere {

    @Test
    public void testConstructor() {
        boolean[][] grid = new boolean[10][10];
        Pair startingPosition = new Pair('a', 0);

        try {
            Cacciatorpediniere cacciatorpediniere = new Cacciatorpediniere(Orientation.HORIZONTAL, startingPosition, grid);
            assertNotNull(cacciatorpediniere);
        } catch (IllegalPositionException e) {
            fail("Unexpected IllegalPositionException");
        }
    }

    @Test
    public void testConstructorIllegalPosition() {
        boolean[][] grid = new boolean[10][10];
        Pair startingPosition = new Pair('i', 9);

        assertThrows(IllegalPositionException.class, () -> {
            Cacciatorpediniere cacciatorpediniere = new Cacciatorpediniere(Orientation.HORIZONTAL, startingPosition, grid);
        });
    }
}
