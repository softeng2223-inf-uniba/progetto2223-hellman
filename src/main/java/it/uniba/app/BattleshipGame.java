package it.uniba.app;

import it.uniba.app.exceptions.GameAlreadyRunningException;
import it.uniba.app.exceptions.IllegalPositionException;
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
    /**
     * La difficoltà del gioco.
     */
    private Difficulty currentDifficulty = Difficulty.UNSET;
    /**
     * Lista delle navi.
     */
    private List<Ship> ships = null;
    /**
     * Numero di tentativi errati massimi.
     */
    private int maxFaliedAttempts;
    /**
     * Numero di tentativi errati effettuati.
     */
    private int failedAttempts;
    /**
     * Numero di colpi andati a segno.
     */
    private int hits;
    /**
     * La griglia delle navi. Contiene true nelle caselle occupate dalle navi.
     */
    private boolean[][] grid = new boolean[GRID_SIZE][GRID_SIZE];
    /**
     * La griglia dei colpi. Contiene 0 nelle caselle non colpite, 1 nelle caselle colpite e 2 nelle caselle mancate.
     */
    private int[][] hitsGrid = new int[GRID_SIZE][GRID_SIZE];

    BattleshipGame() {
        currentDifficulty = Difficulty.UNSET;
    }

    void setDifficulty(final String command) {
        final int easyAttempts = 50;
        final int mediumAttempts = 30;
        final int hardAttempts = 10;
        switch (command.toLowerCase()) {
            case "/facile":
                currentDifficulty = Difficulty.EASY;
                maxFaliedAttempts = easyAttempts;
                break;
            case "/medio":
                currentDifficulty = Difficulty.MEDIUM;
                maxFaliedAttempts = mediumAttempts;
                break;
            case "/difficile":
                currentDifficulty = Difficulty.HARD;
                maxFaliedAttempts = hardAttempts;
                break;
            default:
                break;
        }
    }

    void showDifficulty() {
        System.out.println("Difficoltà attuale: " + currentDifficulty.toString());
    }

    void showShips() {

    }

    void revealHitsGrid() {

    }

    /**
     * Mostra la griglia dei colpi.
     * La griglia sarà di questo tipo:
     * <pre>
     *     1  2  3  4  5  6  7  8  9  10
     *   |------------------------------|
     * A | 0  0  0  0  0  0  0  0  0  0 |
     * B | 0  0  0  0  0  0  0  0  0  0 |
     * C | 0  0  0  0  0  0  0  0  0  0 |
     * D | 0  0  0  0  0  0  0  0  0  0 |
     * E | 0  0  0  0  0  0  0  0  0  0 |
     * F | 0  0  0  0  0  0  0  0  0  0 |
     * G | 0  0  0  0  0  0  0  0  0  0 |
     * H | 0  0  0  0  0  0  0  0  0  0 |
     * I | 0  0  0  0  0  0  0  0  0  0 |
     * J | 0  0  0  0  0  0  0  0  0  0 |
     *   </pre>
     */
    void revealShipGrid() {
        String gridOutput = "   1  2  3  4  5  6  7  8  9  10\n";
        gridOutput += " |------------------------------\n";
        for (int i = 0; i < GRID_SIZE; i++) {
            gridOutput += (char) ('A' + i) + "|";
            for (int j = 0; j < GRID_SIZE; j++) {
                if (grid[i][j]) {
                    gridOutput += " X ";
                } else {
                    gridOutput += "   ";
                }
            }
            gridOutput += "\n";
        }

        System.out.println(gridOutput);
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
                Random r = new Random();
                Orientation orientation = r.nextBoolean() ? Orientation.HORIZONTAL : Orientation.VERTICAL;
                Ship portaerei = new Portaerei(orientation, position, grid);
                updateGrid(tempGrid, position, orientation, portaerei);
                //corazzate
                position = getRandomPosition(tempGrid);
                orientation = r.nextBoolean() ? Orientation.HORIZONTAL : Orientation.VERTICAL;
                Ship corazzata1 = new Corazzata(orientation, position, grid);
                updateGrid(tempGrid, position, orientation, corazzata1);
                position = getRandomPosition(tempGrid);
                orientation = r.nextBoolean() ? Orientation.HORIZONTAL : Orientation.VERTICAL;
                Ship corazzata2 = new Corazzata(orientation, position, grid);
                updateGrid(tempGrid, position, orientation, corazzata2);
                //incrociatori
                position = getRandomPosition(tempGrid);
                orientation = r.nextBoolean() ? Orientation.HORIZONTAL : Orientation.VERTICAL;
                Ship incrociatore1 = new Incrociatore(orientation, position, grid);
                updateGrid(tempGrid, position, orientation, incrociatore1);
                position = getRandomPosition(tempGrid);
                orientation = r.nextBoolean() ? Orientation.HORIZONTAL : Orientation.VERTICAL;
                Ship incrociatore2 = new Incrociatore(orientation, position, grid);
                updateGrid(tempGrid, position, orientation, incrociatore2);
                position = getRandomPosition(tempGrid);
                orientation = r.nextBoolean() ? Orientation.HORIZONTAL : Orientation.VERTICAL;
                Ship incrociatore3 = new Incrociatore(orientation, position, grid);
                updateGrid(tempGrid, position, orientation, incrociatore3);
                //cacciatorpediniere
                position = getRandomPosition(tempGrid);
                orientation = r.nextBoolean() ? Orientation.HORIZONTAL : Orientation.VERTICAL;
                Ship cacciatorpediniere1 = new Cacciatorpediniere(orientation, position, grid);
                updateGrid(tempGrid, position, orientation, cacciatorpediniere1);
                position = getRandomPosition(tempGrid);
                orientation = r.nextBoolean() ? Orientation.HORIZONTAL : Orientation.VERTICAL;
                Ship cacciatorpediniere2 = new Cacciatorpediniere(orientation, position, grid);
                updateGrid(tempGrid, position, orientation, cacciatorpediniere2);
                position = getRandomPosition(tempGrid);
                orientation = r.nextBoolean() ? Orientation.HORIZONTAL : Orientation.VERTICAL;
                Ship cacciatorpediniere3 = new Cacciatorpediniere(orientation, position, grid);
                updateGrid(tempGrid, position, orientation, cacciatorpediniere3);
                position = getRandomPosition(tempGrid);
                orientation = r.nextBoolean() ? Orientation.HORIZONTAL : Orientation.VERTICAL;
                Ship cacciatorpediniere4 = new Cacciatorpediniere(orientation, position, grid);
                updateGrid(tempGrid, position, orientation, cacciatorpediniere4);
            } catch (IllegalPositionException e) {
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
        Random r = new Random();
        do {
            err = false;
            String alphabet = "ABCDEFGHIJ";
            int x = r.nextInt(GRID_SIZE);
            int y = r.nextInt(GRID_SIZE);
            p = new Pair(alphabet.charAt(x), y);
            int[] coordinates = p.toArray();
            if (checkGrid[coordinates[0]][coordinates[1]]) {
                err = true;
            }
        } while (err);
        return p;
    }
}
