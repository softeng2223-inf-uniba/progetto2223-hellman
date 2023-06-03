package it.uniba.app.exceptions;

/**
 * Eccezione che viene lanciata quando si tenta di mostrare le navi ma la partita non è ancora iniziata.
 */
public class GameNotReadyException extends Exception {
    /**
     * Costruttore dell'eccezione con messaggio.
     *
     * @param msg il messaggio
     */
    public GameNotReadyException(final String msg) {
        super(msg);
    }
}
