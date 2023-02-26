package it.unicam.cs.pa.scacchiera.list;

import it.unicam.cs.pa.scacchiera.list.pieces.Piece;


import java.util.ArrayList;
import java.util.List;


public abstract class GameFrameImpl implements GameFrame<Piece, Location> {
    private GameFrame future, previous;
    private Colour actualTurn;
    private List<Piece> whitePieces, blackPieces;
    private Board<Piece, Location> theBoard;

    public GameFrameImpl(GameFrame<Piece, Location> prev, Colour turn, Board<Piece, Location> board){
        actualTurn = turn;
        previous = prev;
        whitePieces = fillPieceList(Colour.WHITE);
        blackPieces = fillPieceList(Colour.BLACK);
        theBoard = board;
    }

    /**
     * Crea una lista di pezzi di un certo colore
     * @param colour
     * @return
     */
    public List<Piece> fillPieceList(Colour colour){
        List<Piece> result = new ArrayList<>();
        for(Location loc : theBoard.allLocations()){
            if(loc.getPiece() != null && loc.getPiece().getColour()==colour){
                result.add(loc.getPiece());
            }
        }
        return result;
    }


    public GameFrame<Piece, Location> getFuture() {
        return future;
    }

    /**
     * Lista di tutte le posibili mosse che un pezzo, appartenente ad un giocatore specifico, può effettuare.
     *
     * @param col
     * @param piece
     * @return Lista delle mosse effettuabili da un pezzo.
     */
    @Override
    public abstract List<Move> allPieceMoves(GameFrame<Piece, Location> state, Colour col, Piece piece);

    /**
     * Tutte le possibili mosse per un giocatore durante un determinato momento della partita.
     * @param state
     * @param colour
     * @return lista delle mosse possibili.
     */
    @Override
    public abstract List<Move> allPossibleMoves(GameFrame<Piece, Location> state, Colour colour);


    public void setFuture(GameFrame<Piece, Location> future) {
        this.future = future;
    }

    public GameFrame<Piece, Location> getPrevious() {
        return previous;
    }

    public void setPrevious(GameFrame<Piece, Location> previous) {
        this.previous = previous;
    }

    public Colour getActualTurn() {
        return actualTurn;
    }

    public void setActualTurn(Colour actualTurn) {
        this.actualTurn = actualTurn;
    }

    public List<Piece> getBlackPieces() {
        return blackPieces;
    }

    public List<Piece> getWhitePieces() {
        return whitePieces;
    }

    public Board<Piece, Location> getTheBoard() {
        return theBoard;
    }

    public void setTheBoard(Board<Piece, Location> theBoard) {
        this.theBoard = theBoard;
    }
}
