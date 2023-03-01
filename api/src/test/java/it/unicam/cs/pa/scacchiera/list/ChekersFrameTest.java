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
        Piece whitePiece = new Pawn( board.getSchema()[0][0], Colour.WHITE);
        board.setPiece(whitePiece.getLocation(), whitePiece);
        Piece blackPiece = new Pawn(new LocationImpl(1, 1), Colour.BLACK);
        board.setPiece(blackPiece.getLocation(), blackPiece);

        CheckersFrame checkersFrame = new CheckersFrame(null, Colour.WHITE, board);

        // Test
        List<Piece> whitePieces = checkersFrame.fillPieceList(Colour.WHITE);
        List<Piece> blackPieces = checkersFrame.fillPieceList(Colour.BLACK);

        // Verify
        assertEquals(1, whitePieces.size());
        assertEquals(1, blackPieces.size());
        assertTrue(whitePieces.contains(whitePiece));
        assertTrue(blackPieces.contains(blackPiece));
    }

    @Test
    public void allPieceMoves() throws Exception {
        // Setup
        Board<Piece, Location> board = new CheckersBoard(8, 8);
        Piece whitePiece = new Pawn( board.getSchema()[1][1], Colour.WHITE);
        board.setPiece(whitePiece.getLocation(), whitePiece);

        CheckersFrame checkersFrame = new CheckersFrame(null, Colour.WHITE, board);

        // Test
        List<Move> moves = checkersFrame.allPieceMoves(checkersFrame, Colour.WHITE, whitePiece);

        // Verify
        assertEquals(2, moves.size());
        assertTrue(moves.contains(new Move(whitePiece.getLocation(), board.getSchema()[0][0])));
        assertTrue(moves.contains(new Move(whitePiece.getLocation(),  board.getSchema()[2][0])));
    }

    @Test
    public void allPossibleMoves() throws Exception {
        // Setup
        Board<Piece, Location> board = new CheckersBoard(8, 8);
        Piece whitePiece = new Pawn( board.getSchema()[1][1], Colour.WHITE);
        board.setPiece(whitePiece.getLocation(), whitePiece);

        CheckersFrame checkersFrame = new CheckersFrame(null, Colour.WHITE, board);

        // Test
        List<Move> moves = checkersFrame.allPossibleMoves(checkersFrame, Colour.WHITE);

        // Verify
        assertEquals(2, moves.size());
        assertTrue(moves.contains(new Move(whitePiece.getLocation(), board.getSchema()[0][0])));
        assertTrue(moves.contains(new Move(whitePiece.getLocation(),  board.getSchema()[2][0])));
    }
}
