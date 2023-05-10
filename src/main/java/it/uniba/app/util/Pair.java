package it.uniba.app.util;

/**
 * Classe che modella una coppia carattere-intero per rappresentare
 * le coordinate di una cella della griglia.
 * La coppia è immutabile e per istanziarla si usa il metodo statico
 * createPair della classe Builder.
 */
public final class Pair {
    private final Character first;
    private final Integer second;
    private static final int MIN_INT = 1;
    private static final int MAX_INT = 10;
    private static final String ERR_MSG =
            "Il secondo valore deve essere compreso tra 1 e 10";

    /**
     * Costruttore della coppia.
     *
     * @param firstItem  il carattere
     * @param secondItem l'intero
     * @throws IllegalArgumentException se il carattere
     *                                  non è compreso tra A e J,
     *                                  o se l'intero non è compreso tra 1 e 10
     */
    public Pair(final Character firstItem, final Integer secondItem) throws IllegalArgumentException {
        if (firstItem == 'A' || firstItem == 'B' || firstItem == 'C'
                || firstItem == 'D' || firstItem == 'E' || firstItem == 'F'
                || firstItem == 'G' || firstItem == 'H' || firstItem == 'I'
                || firstItem == 'J') {
            if (secondItem >= MIN_INT && secondItem <= MAX_INT) {
                first = firstItem;
                second = secondItem;
            } else {
                throw new IllegalArgumentException(ERR_MSG);
            }
        } else {
            throw new IllegalArgumentException(ERR_MSG);
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
        return new int[]{(int) this.second, ((char) this.first) - 'A'};
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
