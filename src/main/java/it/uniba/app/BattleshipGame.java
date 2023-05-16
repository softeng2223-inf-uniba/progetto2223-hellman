package it.uniba.app;

import it.uniba.app.exceptions.GameAlreadyRunningException;
import it.uniba.app.exceptions.UnsetDifficultyException;
import it.uniba.app.ships.Ship;

import java.util.List;

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

    void newGame() throws UnsetDifficultyException, GameAlreadyRunningException {
        if (currentDifficulty == Difficulty.UNSET) {
            throw new UnsetDifficultyException("Devi impostare la difficoltà prima di iniziare una nuova partita.");
        }
        if (ships != null) {
            throw new GameAlreadyRunningException("C'è già una partita in corso.");
        }
    }
}
