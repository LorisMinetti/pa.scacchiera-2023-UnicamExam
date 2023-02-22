package it.unicam.cs.pa.scacchiera.list;

import it.unicam.cs.pa.scacchiera.list.pieces.Piece;


import java.util.ArrayList;
import java.util.List;


public class GameFrameImpl implements GameFrame {
    private GameFrame future, previous;
    private Colour actualTurn;
    private List<Piece> piecesList;
    private BoardImpl theBoard;

    private GameFrameImpl(GameFrame next, GameFrame prev, Colour turn){
        actualTurn = turn;
        future = next;
        previous = prev;
        piecesList = fill();
    }

    private List<Piece> fill() {
        List<Piece> result = new ArrayList<Piece>();
        for(Object loc : theBoard.allLocations()){
            if(loc instanceof Location){
                if(((Location) loc).getPiece() != null){
                    result.add(((Location) loc).getPiece());
                }
            }
        }
        return result;
    }

    public GameFrame getFuture() {
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
    public List<Move> allPieceMoves(GameFrame state, Colour col, Piece piece) {
        List<Move> moveList = new ArrayList<>();
        List<Location> diagonalSpots = theBoard.getDiagonalAdjacentLocationsOfPiece(piece);
        for (Location loc : diagonalSpots) {
            Piece pz = loc.getPiece();
            /* Check nella cell destinataria c'è un pezzo avversario */
            if (pz != null && pz.getColour() != col) {
                Location nextDiagonalSpot = theBoard.getNextDiagonalSpot(piece.getLocation(), loc);
                moveList.add(new Move(piece.getLocation(), nextDiagonalSpot));
            }
            /* Check se la casella destinataria è vuota ed è nella scacchiera */
            else if (pz == null && theBoard.isInsideBoard(loc)) {
                moveList.add(new Move(piece.getLocation(), loc));
            }
        }
        return moveList;
    }

    /**
     * Tutte le possibili mosse per un giocatore durante un determinato momento della partita.
     * @param state
     * @param colour
     * @return lista delle mosse possibili.
     */
    @Override
    public List<Move> allPossibleMoves(GameFrame state, Colour colour) {
        List<Move> possibleMoves = new ArrayList<>();
        for (Location loc : theBoard.getAllLocationsOfPlayer(colour)) {
            Piece pz = loc.getPiece();
            /* Concateno tutte le mosse per ogni pezzo per ottenere tutte le mosse di uno specifico giocatore */
            possibleMoves.addAll(allPieceMoves(this, colour, pz));
        }
        return possibleMoves;
    }

    public void setFuture(GameFrame future) {
        this.future = future;
    }

    public GameFrame getPrevious() {
        return previous;
    }

    public void setPrevious(GameFrame previous) {
        this.previous = previous;
    }

    public Colour getActualTurn() {
        return actualTurn;
    }

    public void setActualTurn(Colour actualTurn) {
        this.actualTurn = actualTurn;
    }

    public List<Piece> getPiecesList() {
        return piecesList;
    }

    public void setPiecesList(List<Piece> piecesList) {
        this.piecesList = piecesList;
    }

    public Board getTheBoard() {
        return theBoard;
    }

    public void setTheBoard(BoardImpl theBoard) {
        this.theBoard = theBoard;
    }
}
