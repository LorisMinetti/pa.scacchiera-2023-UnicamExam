package it.unicam.cs.pa.scacchiera.list;

import it.unicam.cs.pa.scacchiera.list.pieces.Piece;

import java.util.List;

public interface GameFrame<P, L> {

    /**
     * Tutte le mosse di uno specifico pezzo di un determinato giocatore.
     * @param state momento attuale del gioco
     * @param col giocatore
     * @param piece pezzo considerato
     * @return lista delle mosse che può eseguire quel pezzo
     */
    List<Move> allPieceMoves(GameFrame<P, L> state, Colour col, P piece);

    /**
     * Tutte le possibili mosse per un giocatore durante un determinato momento della partita.
     * @param state, colour
     * @return lista delle mosse possibili.
     */
    List<Move> allPossibleMoves(GameFrame<P, L> state, Colour colour);

    GameFrame getFuture();
    void setFuture(GameFrame<P, L> future);
    GameFrame getPrevious();
    void setPrevious(GameFrame<P, L> previous);
    Colour getActualTurn();
    void setActualTurn(Colour actualTurn);
    List<Piece> getBlackPieces();
    List<Piece> getWhitePieces();
    Board<Piece, Location> getTheBoard();
    void setTheBoard(Board<Piece, Location> theBoard);
}
