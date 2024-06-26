package it.uniba.app.util;

import java.util.Objects;

import it.uniba.app.BattleshipGame;

/**
 * Classe che modella una coppia carattere-intero per rappresentare
 * le coordinate di una cella della griglia.
 */
public final class Pair {
    private final Character first;
    private final Integer second;
    private static final int MIN_INT = 1;
    private static final String FRST_ERR_MSG =
            "Il primo valore deve essere compreso tra A e " + (char) ('A' + BattleshipGame.GRID_SIZE - 1);
    private static final String SECND_ERR_MSG =
            "Il secondo valore deve essere compreso tra 1 e " + BattleshipGame.GRID_SIZE;

    /**
     * Costruttore della coppia.
     *
     * @param firstItem  il carattere
     * @param secondItem l'intero
     * @throws IllegalArgumentException se il carattere
     *                                  non è compreso tra A e il massimo valore della griglia,
     *                                  o se l'intero non è compreso tra 0 e BattleShipGame.GRID_SIZE - 1
     */
    public Pair(final Character firstItem, final Integer secondItem) throws IllegalArgumentException {
        String allowedValues = "";
        for (int i = 0; i < BattleshipGame.GRID_SIZE; i++) {
            allowedValues += (char) ('A' + i);
        }
        if (allowedValues.indexOf(firstItem) != -1) {
            if (secondItem >= MIN_INT && secondItem <= BattleshipGame.GRID_SIZE) {
                first = firstItem;
                second = secondItem;
            } else {
                throw new IllegalArgumentException(SECND_ERR_MSG);
            }
        } else {
            throw new IllegalArgumentException(FRST_ERR_MSG);
        }
    }

    /**
     * Restituisce il carattere della coppia.
     *
     * @return il carattere
     */
    public Character getFirst() {
        return this.first;
    }

    /**
     * Restituisce l'intero della coppia.
     *
     * @return l'intero
     */
    public Integer getSecond() {
        return this.second;
    }

    /**
     * Restituisce la coppia come array di interi.
     * Il primo valore indica la posizione verticale,
     * il secondo la posizione orizzontale.
     *
     * @return l'array degli indici
     */
    public int[] toArray() {
        return new int[]{((char) this.first) - 'A', (int) this.second - 1};
    }

    /**
     * Metodo equals.
     * Due oggetti Pair sono uguali se hanno gli stessi valori.
     */
    @Override
    public boolean equals(final Object obj) {
        if (obj instanceof Pair) {
            Pair p = (Pair) obj;
            return this.hashCode() == p.hashCode();
        }
        return false;
    }

    /**
     * Metodo hashCode.
     * Due oggetti Pair hanno lo stesso hashcode se hanno gli stessi valori.
     */
    @Override
    public int hashCode() {
        return Objects.hash(first, second);
    }

    /**
     * Restituisce la coppia come stringa, nel formato "A-1".
     *
     * @return la stringa
     */
    @Override
    public String toString() {
        return first + "-" + second;
    }
}
