package it.unicam.cs.pa.scacchiera.list;

import it.unicam.cs.pa.scacchiera.list.pieces.Piece;

public class Move {

    private Location start, destination;

    public Move(Location start, Location dest) {
        this.start = start;
        destination = dest;
    }

    Location getStart() {
        return this.start;
    }

    Location getDestination() {
        return this.destination;
    }

    public boolean belongsToBoard(Board board) {
        return (board != null &&
                (board.isInsideBoard(start) && board.isInsideBoard(destination)));
    }
}
