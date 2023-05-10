package it.uniba.app.util;

/**
 * Classe che modella una coppia carattere-intero per rappresentare le coordinate di una cella della griglia.
 * La coppia è immutabile e per istanziarla si usa il metodo statico createPair della classe Builder.
 * @see Builder
 */
public class Pair {
    private final Character first;
    private final Integer second;

    /**
     * Costruttore della coppia
     * @param first il carattere
     * @param second l'intero
     */
    private Pair(Character first, Integer second) {
        this.first = first;
        this.second = second;
    }

    /**
     * Restituisce il carattere della coppia
     * @return il carattere
     */
    public Character getFirst() {
        return this.first;
    }

    /**
     * Restituisce l'intero della coppia
     * @return l'intero
     */
    public Integer getSecond() {
        return this.second;
    }

    /**
     * Restituisce la coppia come array di interi. Il primo valore indica la posizione verticale, il secondo la posizione orizzontale.
     * @return l'array degli indici
     */
    public int[] toArray() {
        return new int[]{(int) this.second, ((char) this.first) - 'A'};
    }

    /**
     * Restituisce la coppia come stringa, nel formato "A-1"
     * @return la stringa
     */
    @Override
    public String toString() {
        return first + "-" + second;
    }

    /**
     * Classe utilitaria per la creazione di una coppia carattere-intero.
     *
     * @see Pair
     */
    class Builder {
        /**
         * Metodo statico per la creazione di una coppia carattere-intero.
         * @param first il carattere
         * @param second l'intero
         * @return la coppia
         * @throws IllegalArgumentException se il carattere non è compreso tra A e J o l'intero non è compreso tra 1 e 10
         */
        public static Pair createPair(char first, int second) throws IllegalArgumentException {
            if (first == 'A' || first == 'B' || first == 'C' || first == 'D' || first == 'E' || first == 'F' || first == 'G' || first == 'H' || first == 'I' || first == 'J')
                if (second >= 1 && second <= 10)
                    return new Pair(first, second);
                else
                    throw new IllegalArgumentException("L'indice deve essere compreso tra 1 e 10 e la lettera tra A e J");
            else throw new IllegalArgumentException("L'indice deve essere compreso tra 1 e 10 e la lettera tra A e J");
        }
    }
}
