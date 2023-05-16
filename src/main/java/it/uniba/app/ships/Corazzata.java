package it.uniba.app.ships;

import it.uniba.app.exceptions.IllegalPositionException;
import it.uniba.app.util.Pair;

/**
 * Classe che rappresenta la corazzata (nave da 4 caselle).
 * Estende la classe astratta Ship.
 *
 * @see Ship
 */
public final class Corazzata extends Ship {
    private static final int LENGTH = 4;

    /**
     * Costruttore della classe Corazzata.
     *
     * @param orientation      orientamento della nave
     * @param startingPosition cella di partenza della nave
     * @param grid             griglia di gioco
     * @throws IllegalPositionException se la nave esce dalla griglia
     */
    public Corazzata(final Orientation orientation, final Pair startingPosition,
                     final boolean[][] grid) throws IllegalPositionException {
        super(LENGTH, orientation, startingPosition, grid);
    }
}
