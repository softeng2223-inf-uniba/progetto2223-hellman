package app.ships;
import it.uniba.app.exceptions.IllegalPositionException;
import it.uniba.app.ships.Incrociatore;
import it.uniba.app.ships.Orientation;
import it.uniba.app.util.Pair;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TestIncrociatore{

    @Test
    public void testConstructor() {
        boolean[][] grid = new boolean[10][10];
        Pair startingPosition = new Pair('a', 0);

        try {
            Incrociatore incrociatore = new Incrociatore(Orientation.HORIZONTAL, startingPosition, grid);
            assertNotNull(incrociatore);
        } catch (IllegalPositionException e) {
            fail("Unexpected IllegalPositionException");
        }
    }

    @Test
    public void testConstructorIllegalPosition() {
        boolean[][] grid = new boolean[10][10];
        Pair startingPosition = new Pair('i', 9);

        assertThrows(IllegalPositionException.class, () -> {
            Incrociatore incrociatore = new Incrociatore(Orientation.HORIZONTAL, startingPosition, grid);
        });
    }
}
