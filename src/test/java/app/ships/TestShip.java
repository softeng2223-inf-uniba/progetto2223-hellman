package app.ships;

import it.uniba.app.ships.Orientation;
import it.uniba.app.ships.Ship;
import it.uniba.app.exceptions.IllegalPositionException;
import it.uniba.app.util.Pair;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TestShip {

    @Test
    void shipInitialization_shouldSetPropertiesCorrectly() {
        try {
            int shipLength = 4;
            Orientation shipOrientation = Orientation.HORIZONTAL;
            Pair shipStartingPosition = new Pair('a', 3);
            boolean[][] grid = new boolean[10][10];

            Ship ship = new TestShip1(shipLength, shipOrientation, shipStartingPosition, grid);

            assertEquals(shipLength, ship.getLength());
            assertEquals(0, ship.getHits());
            assertFalse(ship.isSunk());
            assertEquals(shipOrientation, ship.getOrientation());
            assertEquals(shipStartingPosition, ship.getStartingPosition());
        } catch (IllegalPositionException e) {
            fail("Unexpected exception: " + e.getMessage());
        }
    }

    @Test
    void shipHit_shouldIncrementHitsAndSinkWhenFullyHit() {
        try {
            int shipLength = 3;
            Orientation shipOrientation = Orientation.VERTICAL;
            Pair shipStartingPosition = new Pair('c', 5);
            boolean[][] grid = new boolean[10][10];

            Ship ship = new TestShip1(shipLength, shipOrientation, shipStartingPosition, grid);

            ship.hit();
            assertEquals(1, ship.getHits());
            assertFalse(ship.isSunk());

            ship.hit();
            assertEquals(2, ship.getHits());
            assertFalse(ship.isSunk());

            ship.hit();
            assertEquals(3, ship.getHits());
            assertTrue(ship.isSunk());
        } catch (IllegalPositionException e) {
            fail("Unexpected exception: " + e.getMessage());
        }
    }

    @Test
    void shipHit_shouldNotSinkWhenNotFullyHit() {
        try {
            int shipLength = 4;
            Orientation shipOrientation = Orientation.HORIZONTAL;
            Pair shipStartingPosition = new Pair('e', 2);
            boolean[][] grid = new boolean[10][10];

            Ship ship = new TestShip1(shipLength, shipOrientation, shipStartingPosition, grid);

            ship.hit();
            assertEquals(1, ship.getHits());
            assertFalse(ship.isSunk());

            ship.hit();
            assertEquals(2, ship.getHits());
            assertFalse(ship.isSunk());

            ship.hit();
            assertEquals(3, ship.getHits());
            assertFalse(ship.isSunk());
        } catch (IllegalPositionException e) {
            fail("Unexpected exception: " + e.getMessage());
        }
    }

    // TestShip1 class used for testing Ship
    private static class TestShip1 extends Ship {
        public TestShip1(int shipLength, Orientation shipOrientation, Pair shipStartingPosition, boolean[][] grid) throws IllegalPositionException {
            super(shipLength, shipOrientation, shipStartingPosition, grid);
        }
    }
}
