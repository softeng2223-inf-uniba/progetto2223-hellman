package it.uniba.app.util;

public class Pair {
    private Character first;
    private Integer second;

    private Pair(Character first, Integer second) {
        this.first = first;
        this.second = second;
    }

    public Character getFirst() {
        return this.first;
    }

    public Integer getSecond() {
        return this.second;
    }

    public int[] toArray() {
        return new int[]{(int) this.second, ((char) this.first) - 'A'};
    }

    class Builder {
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
