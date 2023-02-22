package it.unicam.cs.pa.scacchiera.list;

import it.unicam.cs.pa.scacchiera.list.pieces.Piece;

import java.util.List;

public interface GameFrame {

    /**
     * Tutte le mosse di uno specifico pezzo di un determinato giocatore.
     * @param state momento attuale del gioco
     * @param col giocatore
     * @param piece pezzo considerato
     * @return lista delle mosse che può eseguire quel pezzo
     */
    List<Move> allPieceMoves(GameFrame state, Colour col, Piece piece);

    /**
     * Tutte le possibili mosse per un giocatore durante un determinato momento della partita.
     * @param state, colour
     * @return lista delle mosse possibili.
     */
    List<Move> allPossibleMoves(GameFrame state, Colour colour);

    GameFrame getFuture();
    void setFuture(GameFrame future);
    GameFrame getPrevious();
    void setPrevious(GameFrame previous);
    Colour getActualTurn();
    void setActualTurn(Colour actualTurn);
    List<Piece> getPiecesList();
    void setPiecesList(List<Piece> piecesList);
    Board getTheBoard();
    void setTheBoard(BoardImpl theBoard);
}
