package it.uniba.app;
/**
 * Enumerazione che rappresenta la dimensione della taglia della griglia.
 */
public enum GridSize {
    STANDARD(10),
    LARGE(18),
    EXTRA_LARGE(26);

    private final int size;

    GridSize(final int value) {
        this.size = value;
    }

    public int getSize() {
        return size;
    }
}
