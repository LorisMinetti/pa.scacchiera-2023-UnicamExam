package it.unicam.cs.pa.scacchiera.list.Errors;

public class IllegalMovementException extends BoardGameException {

    private static final long serialVersionUID = 1L;

    /**
     * Genera un eccezione personalizzata per il contesto della scacchiera. Generata dal giocatore <code>player</code> e con messaggio d'errore <code>errMessage</code>
     *
     * @param player     giocatore che ha fatto l'errore'
     * @param errMessage messaggio d'errore
     */
    IllegalMovementException(int player, String errMessage) {
        super(player, errMessage);
    }
}
