package it.unicam.cs.pa.scacchiera.list;
import it.unicam.cs.pa.scacchiera.list.pieces.Piece;
import it.unicam.cs.pa.scacchiera.list.util.Colour;
import org.junit.jupiter.api.Test;
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

        assertEquals(Colour.WHITE, checkersFrame.getTheBoard().getPiece(5, 1).getColour());
        assertEquals(Colour.WHITE, checkersFrame.getTheBoard().getPiece(6, 2).getColour());
        assertEquals(Colour.BLACK, checkersFrame.getTheBoard().getPiece(0, 0).getColour());
        assertEquals(Colour.BLACK, checkersFrame.getTheBoard().getPiece(1, 1).getColour());

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

        // Verify number of moves
        assertEquals(2, moves1.size());
        assertEquals(2, moves4.size());
        assertEquals(1, moves2.size());
        assertEquals(1, moves3.size());

        Location loc = checkersFrame.getTheBoard().getSchema()[5][1];
        Location loc1 = checkersFrame.getTheBoard().getSchema()[5][7];
        Location loc2 = checkersFrame.getTheBoard().getSchema()[2][0];
        Location loc3 = checkersFrame.getTheBoard().getSchema()[2][4];

        //Verify all moves
        assertTrue(moves1.contains( new Move(loc, board.getSchema()[4][0] )));
        assertTrue(moves1.contains( new Move(loc, board.getSchema()[4][2] )));
        assertTrue(moves2.contains( new Move(loc1, board.getSchema()[4][6] )));
        assertTrue(moves3.contains( new Move(loc2, board.getSchema()[3][1] )));
        assertTrue(moves4.contains( new Move(loc3, board.getSchema()[3][3] )));
        assertTrue(moves4.contains( new Move(loc3, board.getSchema()[3][5] )));


    }

    @Test
    public void allPossibleMoves() throws Exception {
        // Setup
        Board<Piece, Location> board = new CheckersBoard(8, 8);
        CheckersFrame checkersFrame = new CheckersFrame(null, Colour.WHITE, board);

        // Test
        List<Move> whitemoves = checkersFrame.allPossibleMoves(Colour.WHITE);
        List<Move>  blackMoves = checkersFrame.allPossibleMoves(Colour.BLACK);
        // Verify
        assertEquals(7, whitemoves.size());
        assertEquals(7, blackMoves.size());

    }


}
