package it.unicam.cs.pa.scacchiera.list;

import it.unicam.cs.pa.scacchiera.list.Checkers.CheckersBoard;
import it.unicam.cs.pa.scacchiera.list.Checkers.CheckersFrame;
import it.unicam.cs.pa.scacchiera.list.Checkers.CheckersGame;
import it.unicam.cs.pa.scacchiera.list.player.Player;
import it.unicam.cs.pa.scacchiera.list.util.Colour;
import it.unicam.cs.pa.scacchiera.list.util.MoveResult;
import org.junit.jupiter.api.Test;

import static it.unicam.cs.pa.scacchiera.list.util.Colour.WHITE;
import static org.junit.jupiter.api.Assertions.*;

class CheckersGameTest {

    @Test
    void isTerminal() throws Exception {
        Board<Piece, Location> board = new CheckersBoard(8, 8);
        Player player1 = new Player("Jack", WHITE);
        Player player2 = new Player("Jones", Colour.BLACK);
        GameFrame<Piece, Location> frame = new CheckersFrame(null, WHITE, board);
        CheckersGame game = new CheckersGame(player1, player2, board, frame);
        assertFalse(game.isTerminal());
        // Rimuovo tutti i pezzi bianchi dalla scacchiera
        for (int i = 0; i < board.getROW_VALUE(); i++) {
            for (int j = 0; j < board.getCOLUMN_VALUE(); j++) {
                Location location = board.getSchema()[i][j];
                if (location.getPiece().isPresent() && location.getPiece().orElse(null).getColour() == WHITE) {
                    location.setPiece(null);
                }
            }
        }
        assertTrue(game.isTerminal());
        if(frame.allPossibleMoves(WHITE).isEmpty() || frame.allPossibleMoves(Colour.BLACK).isEmpty()) {
            assertTrue(game.isTerminal());
        }

    }

    @Test
    void move() throws Exception {
        Board<Piece, Location> board = new CheckersBoard(8, 8);
        Player player1 = new Player("Jack", WHITE);
        Player player2 = new Player("Jones", Colour.BLACK);
        GameFrame<Piece, Location> frame = new CheckersFrame(null, WHITE, board);
        CheckersGame game = new CheckersGame(player1, player2, board, frame);
        MoveResult result;
        Location[][] schema = frame.getTheBoard().getSchema();
        // Eseguo una mossa non valida inizialmente (pedina nera del giocatore bianco)
        result = game.move(new Move(schema[2][0], schema[3][1]));
        assertEquals(MoveResult.START_LOCATION_OTHER_PLAYER, result);
        // Eseguo una mossa non valida inizialmente (nessuna pedina iniziale)
        result = game.move(new Move(schema[3][3], schema[4][4]));
        assertEquals(MoveResult.START_LOCATION_EMPTY, result);
        // Eseguo una mossa valida
        result = game.move(new Move(schema[6][4], schema[5][3]));
        assertEquals(MoveResult.OK, result);
    }

}