package it.unicam.cs.pa.scacchiera.list;

import it.unicam.cs.pa.scacchiera.list.util.Colour;

import java.util.List;

/**
 * Interfaccia che rappresenta un frame generico di gioco.
 * @param <P> pezzo
 * @param <L> location
 */
public interface GameFrame<P extends Piece, L> {

    /**
     * Tutte le mosse di uno specifico pezzo di un determinato giocatore.
     *
     * @param state momento attuale del gioco
     * @param col   giocatore
     * @param piece pezzo considerato
     * @return lista delle mosse che può eseguire quel pezzo
     */
    List<Move> allPieceMoves(GameFrame<P, L> state, Colour col, P piece);

    /**
     * Tutte le possibili mosse per un giocatore durante un determinato momento della partita.
     *
     * @param colour giocatore
     * @return lista delle mosse possibili.
     */
    List<Move> allPossibleMoves(Colour colour);

    /**
     * Stampa a console la scacchiera.
     * @return scacchiera stampata
     */
    String printBoardFrame();

    void setFuture(GameFrame<P, L> future);

    Colour getActualTurn();

    void setActualTurn(Colour actualTurn);

    List<Piece> getBlackPieces();

    List<Piece> getWhitePieces();

    Board<Piece, Location> getTheBoard();

    int getFrameNumber();
}
