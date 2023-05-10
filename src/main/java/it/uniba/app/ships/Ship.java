package it.uniba.app.ships;

import it.uniba.app.util.Pair;

public abstract class Ship {
    private final int length;
    private int hits;
    private boolean sunk;
    private final Orientation orientation;
    private final Pair startingPosition;

    public Ship(int length, Orientation orientation, Pair startingPosition) {
        this.length = length;
        this.hits = 0;
        this.sunk = false;
        this.orientation = orientation;
        this.startingPosition = startingPosition;

        //TODO: controllare che la nave non intersechi altre navi nella griglia
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

    public Pair getStartingPosition() {
        return startingPosition;
    }

    public void hit() {
        hits++;
        if (hits == length) {
            sunk = true;
        }
    }
}
