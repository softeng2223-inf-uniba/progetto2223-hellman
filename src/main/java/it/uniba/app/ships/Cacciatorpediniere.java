package it.uniba.app.ships;

import it.uniba.app.exceptions.IllegalPositionException;
import it.uniba.app.util.Pair;

/**
 * Classe che rappresenta il cacciatorpediniere (nave da 2 caselle).
 * Estende la classe astratta Ship.
 *
 * @see Ship
 */
public final class Cacciatorpediniere extends Ship {
    private static final int LENGTH = 2;

    /**
     * Costruttore della classe Cacciatorpediniere.
     *
     * @param orientation      orientamento della nave
     * @param startingPosition cella di partenza della nave
     * @param grid             griglia di gioco
     * @throws IllegalPositionException se la nave esce dalla griglia
     */
    public Cacciatorpediniere(final Orientation orientation, final Pair startingPosition,
                              final boolean[][] grid) throws IllegalPositionException {
        super(LENGTH, orientation, startingPosition, grid);
    }
}
