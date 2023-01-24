package it.unicam.cs.pa.scacchiera.list.Errors;

public class BoardGameException extends Exception {

    private static final long serialVersionUID = 1L;

    /**
     * Giocatore che ha fatto l'errore
     */
    private final int palyerId;

    /**
     * Genera un eccezione personalizzata per il contesto della scacchiera. Generata dal giocatore <code>player</code> e con messaggio d'errore <code>errMessage</code>
     *
     * @param player     giocatore che ha fatto l'errore'
     * @param errMessage messaggio d'errore
     */
    BoardGameException(int player, String errMessage) {
        super(errMessage);
        this.palyerId = player;
    }

    /**
     * Id del giocatore che ha generato l'eccezione
     * @return numero identificativo
     */
    public int getPlayerId() {
        return this.palyerId;
    }
}
