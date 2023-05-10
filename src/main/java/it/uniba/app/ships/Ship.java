package it.uniba.app.ships;

public abstract class Ship {
    private final int length;
    private int hits;
    private boolean sunk;
    private final Orientation orientation;

    public Ship(int length, Orientation orientation) {
        this.length = length;
        this.hits = 0;
        this.sunk = false;
        this.orientation = orientation;
    }

    public int getLength() {
        return length;
    }

    public int getHits() {
        return hits;
    }

    public boolean isSunk() {
        return sunk;
    }

    public Orientation getOrientation() {
        return orientation;
    }

    public void hit() {
        hits++;
        if (hits == length) {
            sunk = true;
        }
    }
}
