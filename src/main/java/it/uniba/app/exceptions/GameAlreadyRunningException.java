package it.uniba.app.exceptions;

/**
 * Eccezione che viene lanciata quando si tenta di iniziare una nuova partita quando un altra è gia in corso.
 */
public class GameAlreadyRunningException extends Exception {
    /**
     * Costruttore dell'eccezione con messaggio.
     *
     * @param msg il messaggio
     */
    public GameAlreadyRunningException(final String msg) {
        super(msg);
    }
}
