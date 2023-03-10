package it.unicam.cs.pa.scacchiera.list;

import it.unicam.cs.pa.scacchiera.list.pieces.Piece;
import it.unicam.cs.pa.scacchiera.list.util.GameState;
import it.unicam.cs.pa.scacchiera.list.util.MoveResult;

public interface Game {

    /**
     * Check sulla presenza di almeno un pezzo per uno dei due giocatori.
     * Un giocatore senza pezzi, equivale al termine della partita con conseguente vittoria del giocatore avversario.
     * @return true se uno dei due giocatori non ha più pezzi.
     */
    boolean isTerminal();

    /**
     * Aggiornamento del gioco che cambia turno se lo stato non è ancora terminale.
     */
    void updateStatus();


    /**
     * Frazione di gioco corrente.
     * @return
     */
    GameFrame<Piece, Location> getGameFrame();

    /**
     * Muove una pedina e ne gestisce tutte le possibili conseguenze.
     * @param move mossa da effettuare
     * @throws Exception errore
     */
    MoveResult move(Move move) throws Exception;

    /**
     * Ritorna lo stato del gioco
     * @return stato del gioco attuale
     */
    GameState getStatus();
}
