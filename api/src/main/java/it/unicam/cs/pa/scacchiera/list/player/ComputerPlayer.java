package it.unicam.cs.pa.scacchiera.list.player;

import it.unicam.cs.pa.scacchiera.list.GameFrame;
import it.unicam.cs.pa.scacchiera.list.Location;
import it.unicam.cs.pa.scacchiera.list.Move;
import it.unicam.cs.pa.scacchiera.list.Piece;
import it.unicam.cs.pa.scacchiera.list.util.Colour;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class ComputerPlayer extends Player{

    public ComputerPlayer(String name, Colour colour) {
        super(name, colour);
    }

    public Move play(GameFrame<Piece, Location> frame) {
        List<Move> moves = frame.allPossibleMoves(this.getColour());
        if(moves.isEmpty()){
            return null;
        }
        return moves.get(ThreadLocalRandom.current().nextInt(0, moves.size()));

    }
}
