package it.uniba.app;

import it.uniba.app.exceptions.GameAlreadyRunningException;
import it.uniba.app.exceptions.UnsetDifficultyException;
import it.uniba.app.ships.Ship;

import java.util.List;

/**
 * Classe che rappresenta il gioco.
 */
public final class BattleshipGame {
    private Difficulty currentDifficulty;
    private List<Ship> ships;

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
