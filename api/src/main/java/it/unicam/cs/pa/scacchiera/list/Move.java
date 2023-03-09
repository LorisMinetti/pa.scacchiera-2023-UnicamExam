package it.unicam.cs.pa.scacchiera.list;

import it.unicam.cs.pa.scacchiera.list.pieces.Piece;

import java.util.Objects;

public class Move {

    private final Location start, destination;
    private boolean capture;

    public Move(Location start, Location dest) {
        this.start = start;
        destination = dest;
        capture = false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Move move = (Move) o;
        return start.equals(move.start) && destination.equals(move.destination);
    }

    @Override
    public int hashCode() {
        return Objects.hash(start, destination);
    }

    public Move(Location start, Location dest, boolean capture) {
        this.start = start;
        destination = dest;
        this.capture = capture;
    }

    Location getStart() {
        return this.start;
    }

    Location getDestination() {
        return this.destination;
    }

    public boolean belongsToBoard(Board<Piece, Location> board) {
        return (board != null &&
                (board.isInsideBoard(start) && board.isInsideBoard(destination)));
    }

    public boolean isCapture() {
        return capture;
    }

    public void becomeCaptureMove() {
        this.capture = true;
    }
}
