package it.uniba.app.ships;

import it.uniba.app.util.Pair;

/**
 * Classe che rappresenta la portaerei (nave da 5 caselle).
 * Estende la classe astratta Ship.
 *
 * @see Ship
 */
public class Portaerei extends Ship {
    private static final int LENGTH = 5;
    /**
     * Costruttore della classe Portaerei.
     *
     * @param orientation      orientamento della nave
     * @param startingPosition cella di partenza della nave
     */
    public Portaerei(final Orientation orientation, final Pair startingPosition) {
        super(LENGTH, orientation, startingPosition);
    }
}
