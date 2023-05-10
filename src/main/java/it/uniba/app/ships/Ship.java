package it.uniba.app.ships;

import it.uniba.app.util.Pair;

/**
 * Classe astratta che rappresenta una nave.
 */

public abstract class Ship {
    private final int length;
    private int hits;
    private boolean sunk;
    private final Orientation orientation;
    private final Pair startingPosition;

    /**
     * Costruttore della classe Ship.
     *
     * @param shipLength           lunghezza della nave
     *                             (numero di caselle occupate sulla griglia)
     * @param shipOrientation      orientamento della nave
     * @param shipStartingPosition cella di partenza della nave
     */
    public Ship(final int shipLength, final Orientation shipOrientation, final Pair shipStartingPosition) {
        this.length = shipLength;
        this.hits = 0;
        this.sunk = false;
        this.orientation = shipOrientation;
        this.startingPosition = shipStartingPosition;

        //controllare che la nave non intersechi altre navi nella griglia.
    }

    /**
     * Restituisce la lunghezza dela nave.
     *
     * @return lunghezza della nave
     */
    public final int getLength() {
        return length;
    }


    /**
     * Restituisce il numero di colpi ricevuti dalla nave.
     *
     * @return numero di colpi ricevuti dalla nave
     */
    public final int getHits() {
        return hits;
    }

    /**
     * Restituisce se la nave è affondata.
     *
     * @return true se la nave è affondata, false altrimenti
     */
    public final boolean isSunk() {
        return sunk;
    }

    /**
     * Restituisce l'orientamento della nave.
     *
     * @return orientamento della nave
     */
    public final Orientation getOrientation() {
        return orientation;
    }

    /**
     * Restituisce la cella di partenza della nave.
     *
     * @return cella di partenza della nave
     */
    public final Pair getStartingPosition() {
        return startingPosition;
    }

    /**
     * Incrementa il numero di colpi ricevuti dalla nave.
     * Se il numero di colpi ricevuti è uguale alla lunghezza della nave,
     * la nave viene affondata.
     */
    public final void hit() {
        hits++;
        if (hits == length) {
            sunk = true;
        }
    }
}
