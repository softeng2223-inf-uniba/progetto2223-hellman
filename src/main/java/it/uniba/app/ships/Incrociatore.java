package it.uniba.app.ships;

import it.uniba.app.util.Pair;

/**
 * Classe che rappresenta l'incrociatore (nave da 3 caselle).
 * Estende la classe astratta Ship.
 *
 * @see Ship
 */
public final class Incrociatore extends Ship {
    private static final int LENGTH = 3;

    /**
     * Costruttore della classe Incrociatore.
     *
     * @param orientation      orientamento della nave
     * @param startingPosition cella di partenza della nave
     */
    public Incrociatore(final Orientation orientation, final Pair startingPosition) {
        super(LENGTH, orientation, startingPosition);
    }
}
