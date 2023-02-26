package it.unicam.cs.pa.scacchiera.list.Checkers;

import it.unicam.cs.pa.scacchiera.list.*;
import it.unicam.cs.pa.scacchiera.list.pieces.Piece;

import java.util.ArrayList;
import java.util.List;

public class CheckersGameFrame extends GameFrameImpl {

    public CheckersGameFrame(GameFrame<Piece, Location> prev, Colour turn, Board<Piece, Location> board) {
        super(prev, turn, board);
    }

    /**
     * Lista di tutte le posibili mosse che un pezzo, appartenente ad un giocatore specifico, può effettuare.
     *
     * @param state
     * @param col
     * @param piece
     * @return Lista delle mosse effettuabili da un pezzo.
     */
    @Override
    public List<Move> allPieceMoves(GameFrame<Piece, Location> state, Colour col, Piece piece) {
        List<Move> moveList = new ArrayList<>();
        List<Location> diagonalSpots = super.getTheBoard().getDiagonalAdjacentLocationsOfPiece(piece);
        for (Location loc : diagonalSpots) {
            Piece pz = loc.getPiece();
            /* Check nella cell destinataria c'è un pezzo avversario */
            if (pz != null && pz.getColour() != col) {

                Location nextDiagonalSpot = super.getTheBoard().getNextDiagonalSpot(piece.getLocation(), loc);
                moveList.add(new Move(piece.getLocation(), nextDiagonalSpot));
            }
            /* Check se la casella destinataria è vuota ed è nella scacchiera */
            else if (pz == null && super.getTheBoard().isInsideBoard(loc)) {
                moveList.add(new Move(piece.getLocation(), loc));
            }
        }
        return moveList;    }

    /**
     * Tutte le possibili mosse per un giocatore durante un determinato momento della partita.
     *
     * @param state
     * @param colour
     * @return lista delle mosse possibili.
     */
    @Override
    public List<Move> allPossibleMoves(GameFrame<Piece, Location> state, Colour colour) {
        List<Move> possibleMoves = new ArrayList<>();
        for (Location loc : super.getTheBoard().getAllLocationsOfPlayer(colour)) {
            Piece pz = loc.getPiece();
            /* Concateno tutte le mosse per ogni pezzo per ottenere tutte le mosse di uno specifico giocatore */
            possibleMoves.addAll(allPieceMoves(this, colour, pz));
        }
        return possibleMoves;
    }
}
