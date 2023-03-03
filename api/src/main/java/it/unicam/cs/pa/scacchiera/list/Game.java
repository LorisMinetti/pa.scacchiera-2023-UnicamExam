package it.unicam.cs.pa.scacchiera.list;

import it.unicam.cs.pa.scacchiera.list.pieces.Piece;

public interface Game {

    enum MoveResult {
        OK,
        START_LOCATION_EMPTY, // posizione di partenza non valida, la pedina non esiste
        START_LOCATION_OTHER_PLAYER, // posizione di partenza non valida, la pedina non è del giocatore corrente
        END_LOCATION_OCCUPIED, // posizione di arrivo non valida, se è occupata
        END_LOCATION_OUTSIDE, // posizione di arrivo non valida, se è fuori dalla scacchiera
        END_LOCATION_NOT_REACHABLE, // posizione di arrivo non valida, se non è raggiungibile dalla posizione di partenza'
        MOVE_NOT_VALID // mossa non valida, se non è consentita dalla pedina
    }

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
     * @param move
     * @throws Exception
     */
    MoveResult move(Move move) throws Exception;
}
