package it.uniba.app;

import it.uniba.app.exceptions.GameAlreadyRunningException;
import it.uniba.app.exceptions.UnsetDifficultyException;
import it.uniba.app.ships.Cacciatorpediniere;
import it.uniba.app.ships.Corazzata;
import it.uniba.app.ships.Incrociatore;
import it.uniba.app.ships.Orientation;
import it.uniba.app.ships.Portaerei;
import it.uniba.app.ships.Ship;
import it.uniba.app.util.Pair;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Classe che rappresenta il gioco.
 */
public final class BattleshipGame {
    private static final int GRID_SIZE = 10;
    private Difficulty currentDifficulty;
    private List<Ship> ships;
    private int maxFaliedAttempts;
    private int failedAttempts;
    private int hits;
    private boolean[][] grid = new boolean[GRID_SIZE][GRID_SIZE];
    private int[][] hitsGrid = new int[GRID_SIZE][GRID_SIZE];

    BattleshipGame() {
        currentDifficulty = Difficulty.UNSET;
    }

    void setDifficulty() {

    }

    void showDifficulty() {

    }

    void showShips() {

    }

    void revealGrid() {

    }

    /**
     * Inizializza la griglia assegnando alle navi posizione ed orientamento casuale.
     * Questo viene fatto attraverso "brute force", ovvero generando casualmente
     * posizione ed orientamento e verificando che non ci siano sovrapposizioni.
     *
     * @throws UnsetDifficultyException    se non è stata impostata la difficoltà
     * @throws GameAlreadyRunningException se c'è già una partita in corso
     */
    void newGame() throws UnsetDifficultyException, GameAlreadyRunningException {
        if (currentDifficulty == Difficulty.UNSET) {
            throw new UnsetDifficultyException("Devi impostare la difficoltà prima di iniziare una nuova partita.");
        }
        if (ships != null) {
            throw new GameAlreadyRunningException("C'è già una partita in corso.");
        }

        boolean err;
        boolean[][] tempGrid = null;
        do {
            err = false;
            try {
                tempGrid = new boolean[GRID_SIZE][GRID_SIZE];
                ships = new ArrayList<>();
                Pair position = getRandomPosition(tempGrid);
                Orientation orientation = new Random().nextBoolean() ? Orientation.HORIZONTAL : Orientation.VERTICAL;
                Ship portaerei = new Portaerei(orientation, position, grid);
                updateGrid(tempGrid, position, orientation, portaerei);
                //corazzate
                position = getRandomPosition(tempGrid);
                orientation = new Random().nextBoolean() ? Orientation.HORIZONTAL : Orientation.VERTICAL;
                Ship corazzata1 = new Corazzata(orientation, position, grid);
                updateGrid(tempGrid, position, orientation, corazzata1);
                position = getRandomPosition(tempGrid);
                orientation = new Random().nextBoolean() ? Orientation.HORIZONTAL : Orientation.VERTICAL;
                Ship corazzata2 = new Corazzata(orientation, position, grid);
                updateGrid(tempGrid, position, orientation, corazzata2);
                //incrociatori
                position = getRandomPosition(tempGrid);
                orientation = new Random().nextBoolean() ? Orientation.HORIZONTAL : Orientation.VERTICAL;
                Ship incrociatore1 = new Incrociatore(orientation, position, grid);
                updateGrid(tempGrid, position, orientation, incrociatore1);
                position = getRandomPosition(tempGrid);
                orientation = new Random().nextBoolean() ? Orientation.HORIZONTAL : Orientation.VERTICAL;
                Ship incrociatore2 = new Incrociatore(orientation, position, grid);
                updateGrid(tempGrid, position, orientation, incrociatore2);
                position = getRandomPosition(tempGrid);
                orientation = new Random().nextBoolean() ? Orientation.HORIZONTAL : Orientation.VERTICAL;
                Ship incrociatore3 = new Incrociatore(orientation, position, grid);
                updateGrid(tempGrid, position, orientation, incrociatore3);
                //cacciatorpediniere
                position = getRandomPosition(tempGrid);
                orientation = new Random().nextBoolean() ? Orientation.HORIZONTAL : Orientation.VERTICAL;
                Ship cacciatorpediniere1 = new Cacciatorpediniere(orientation, position, grid);
                updateGrid(tempGrid, position, orientation, cacciatorpediniere1);
                position = getRandomPosition(tempGrid);
                orientation = new Random().nextBoolean() ? Orientation.HORIZONTAL : Orientation.VERTICAL;
                Ship cacciatorpediniere2 = new Cacciatorpediniere(orientation, position, grid);
                updateGrid(tempGrid, position, orientation, cacciatorpediniere2);
                position = getRandomPosition(tempGrid);
                orientation = new Random().nextBoolean() ? Orientation.HORIZONTAL : Orientation.VERTICAL;
                Ship cacciatorpediniere3 = new Cacciatorpediniere(orientation, position, grid);
                updateGrid(tempGrid, position, orientation, cacciatorpediniere3);
                position = getRandomPosition(tempGrid);
                orientation = new Random().nextBoolean() ? Orientation.HORIZONTAL : Orientation.VERTICAL;
                Ship cacciatorpediniere4 = new Cacciatorpediniere(orientation, position, grid);
                updateGrid(tempGrid, position, orientation, cacciatorpediniere4);
            } catch (Exception e) {
                err = true;
            }
        } while (err);
        grid = tempGrid;
    }

    private void updateGrid(final boolean[][] tempGrid, final Pair position,
                            final Orientation orientation, final Ship ship) {
        ships.add(ship);
        for (int i = 0; i < ship.getLength(); i++) {
            int[] coordinates = position.toArray();
            if (orientation == Orientation.HORIZONTAL) {
                tempGrid[coordinates[0]][coordinates[1] + i] = true;
            } else {
                tempGrid[coordinates[0] + i][coordinates[1]] = true;
            }
        }
    }

    private Pair getRandomPosition(final boolean[][] checkGrid) {
        Pair p = null;
        boolean err;
        do {
            err = false;
            String alphabet = "ABCDEFGHIJ";
            int x = (int) (Math.random() * GRID_SIZE);
            int y = (int) (Math.random() * GRID_SIZE);
            p = new Pair(alphabet.charAt(x), y + 1);
            int[] coordinates = p.toArray();
            if (checkGrid[coordinates[0]][coordinates[1]]) {
                err = true;
            }
        } while (err);
        return p;
    }
}
