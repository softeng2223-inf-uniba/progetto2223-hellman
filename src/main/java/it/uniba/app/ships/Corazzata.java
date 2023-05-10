package it.uniba.app.ships;

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
     */
    public Corazzata(final Orientation orientation, final Pair startingPosition) {
        super(LENGTH, orientation, startingPosition);
    }
}
