package it.unicam.cs.pa.scacchiera.list;

import java.util.Objects;

public class Move {

    private Location start, destination;
    private boolean capture;
    //TODO: per implementare il comportamento di dover mangiare obbligatoriamente quando c'è n'è la possibilità,
    //implmentare un booleano implicito all'oogetto che tiene conto se la mossa è di cattura.

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

    public boolean belongsToBoard(Board board) {
        return (board != null &&
                (board.isInsideBoard(start) && board.isInsideBoard(destination)));
    }

    public boolean isCapture() {
        return capture==true ? true : false;
    }

    public void becomeCaptureMove() {
        this.capture = true;
    }
}
