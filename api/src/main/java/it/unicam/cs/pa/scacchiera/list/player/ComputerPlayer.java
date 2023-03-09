package it.unicam.cs.pa.scacchiera.list.player;

import it.unicam.cs.pa.scacchiera.list.Board;
import it.unicam.cs.pa.scacchiera.list.GameFrame;
import it.unicam.cs.pa.scacchiera.list.Location;
import it.unicam.cs.pa.scacchiera.list.Move;
import it.unicam.cs.pa.scacchiera.list.pieces.Piece;
import it.unicam.cs.pa.scacchiera.list.util.Colour;

import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class ComputerPlayer extends Player{

    public ComputerPlayer(String name, Colour colour) {
        super(name, colour);
    }

    /*
    * Voglio implementare il comportamento del computer in modo che:
    * Faccia una mossa random tra tutte le mosse disponibili.
    */
    public Move play(GameFrame<Piece, Location> frame) {
        List<Move> moves = frame.allPossibleMoves(this.getColour());
//        Random random = new Random();
//        int randomIndex = random.nextInt(0, moves.size());
        return moves.get(ThreadLocalRandom.current().nextInt(0, moves.size()));

    }
}
