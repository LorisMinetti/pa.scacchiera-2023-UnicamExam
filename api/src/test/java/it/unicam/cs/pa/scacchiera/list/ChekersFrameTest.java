package it.unicam.cs.pa.scacchiera.list;
import it.unicam.cs.pa.scacchiera.list.Checkers.Pawn;
import it.unicam.cs.pa.scacchiera.list.pieces.Piece;
import it.unicam.cs.pa.scacchiera.list.util.BackgroundColor;
import it.unicam.cs.pa.scacchiera.list.util.Colour;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ChekersFrameTest {
    @Test
    public void fillPieceList() throws Exception {
        // Setup
        Board<Piece, Location> board = new CheckersBoard(8, 8);

        CheckersFrame checkersFrame = new CheckersFrame(null, Colour.WHITE, board);

        // Verify
        assertEquals(12, checkersFrame.getWhitePieces().size());
        assertEquals(12, checkersFrame.getBlackPieces().size());
    }


    @Test
    public void allPieceMoves() throws Exception {
        // Setup
        Board<Piece, Location> board = new CheckersBoard(8, 8);
        CheckersFrame checkersFrame = new CheckersFrame(null, Colour.WHITE, board);

        Piece pezzo1 = checkersFrame.getTheBoard().getPiece(5, 1);
        Piece pezzo2 = checkersFrame.getTheBoard().getPiece(5, 7);
        Piece pezzo3 = checkersFrame.getTheBoard().getPiece(2, 0);
        Piece pezzo4 = checkersFrame.getTheBoard().getPiece(2, 4);

        // Test
        List<Move> moves1 = checkersFrame.allPieceMoves(checkersFrame, Colour.WHITE, pezzo1);
        List<Move> moves2 = checkersFrame.allPieceMoves(checkersFrame, Colour.WHITE, pezzo2);
        List<Move> moves3 = checkersFrame.allPieceMoves(checkersFrame, Colour.WHITE, pezzo3);
        List<Move> moves4 = checkersFrame.allPieceMoves(checkersFrame, Colour.WHITE, pezzo4);

        // Verify
        assertEquals(2, moves1.size());
        assertEquals(2, moves4.size());
        assertEquals(1, moves2.size());
        assertEquals(1, moves3.size());

        Location l1 = new LocationImpl(5, 1);
        Location l2 = new LocationImpl(5, 7);
        Location l3 = new LocationImpl(2, 0);
        Location l4 = new LocationImpl(2, 4);
        assertTrue(moves1.contains( new Move(l1, board.getSchema()[4][0] )));
        assertTrue(moves1.contains( new Move(l1, board.getSchema()[4][2] )));

    }

    @Test
    public void allPossibleMoves() throws Exception {
        // Setup
        Board<Piece, Location> board = new CheckersBoard(8, 8);
        CheckersFrame checkersFrame = new CheckersFrame(null, Colour.WHITE, board);

        // Test
        List<Move> moves = checkersFrame.allPossibleMoves(Colour.WHITE);
        List<Move>  blackMoves = checkersFrame.allPossibleMoves(Colour.BLACK);

        // Verify
        assertEquals(7, moves.size());
        assertEquals(7, blackMoves.size());
    }
}
