package it.uniba.app;

/**
 * Eccezione che viene lanciata quando si tenta di iniziare una nuova partita senza aver impostato la difficoltà.
 */
public class UnsetDifficultyException extends Exception {
    /**
     * Costruttore dell'eccezione con messaggio.
     *
     * @param msg il messaggio
     */
    public UnsetDifficultyException(final String msg) {
        super(msg);
    }
}
