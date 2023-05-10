package it.uniba.app.ships;

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
     */
    public Cacciatorpediniere(final Orientation orientation, final Pair startingPosition) {
        super(LENGTH, orientation, startingPosition);
    }
}
