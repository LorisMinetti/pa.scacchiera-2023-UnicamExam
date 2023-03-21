package it.unicam.cs.pa.scacchiera.list.player;

import it.unicam.cs.pa.scacchiera.list.GameFrame;
import it.unicam.cs.pa.scacchiera.list.Location;
import it.unicam.cs.pa.scacchiera.list.Move;
import it.unicam.cs.pa.scacchiera.list.Piece;
import it.unicam.cs.pa.scacchiera.list.util.Colour;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author Loris Minetti
 * Classe che rappresenta un giocatore bot che effettua mosse random.
 */
public class ComputerPlayer extends Player{

    public ComputerPlayer(String name, Colour colour) {
        super(name, colour);
    }

    /**
     * Metodo che effettua una mossa random tra quelle possibili.
     * @param frame frame di gioco
     * @return una mossa random
     */
    public Move play(GameFrame<Piece, Location> frame) {
        List<Move> moves = frame.allPossibleMoves(this.getColour());
        if(moves.isEmpty()){
            return null;
        }
        return moves.get(ThreadLocalRandom.current().nextInt(0, moves.size()));

    }
}
