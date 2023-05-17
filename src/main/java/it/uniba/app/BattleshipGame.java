package it.uniba.app;

/**
 * Classe che rappresenta il gioco.
 */
public final class BattleshipGame {
    private enum Difficulty { UNSET, EASY, MEDIUM, HARD }
    private Difficulty difficulty;
    private final int tentativiFalliti;

    BattleshipGame() {
        difficulty = Difficulty.UNSET;
        tentativiFalliti = 0;
    }

    void setDifficulty() {

    }

    void showDifficulty() {
        if (difficulty == Difficulty.UNSET) {
            System.out.println("Non è stato impostato nessun livello di difficoltà");
        } else {
            System.out.println("Il livello di difficoltà impostato è : " + difficulty);
            System.out.println("Il numero di tentativi falliti corrispondente è : " + tentativiFalliti);
        }
    }

    void showShips() {

    }

    void revealGrid() {

    }

    void newGame() {

    }

}
