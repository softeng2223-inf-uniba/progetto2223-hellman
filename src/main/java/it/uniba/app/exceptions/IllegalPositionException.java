package it.uniba.app.exceptions;

/**
 * Eccezione che viene lanciata quando si tenta di inserire una nave in una posizione non valida.
 */
public class IllegalPositionException extends Exception {

    /**
     * Costruttore dell'eccezione con messaggio.
     *
     * @param msg il messaggio
     */
    public IllegalPositionException(final String msg) {
        super(msg);
    }
}
