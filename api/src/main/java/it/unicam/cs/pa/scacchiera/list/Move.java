package it.unicam.cs.pa.scacchiera.list;

import java.util.Objects;

/**
 * @author Loris Minetti
 * Classe che ha la responsabilità di rappresentare il concetto di mossa, inteso come un semplice movimento da una posizione iniziale a una posizione finale.
 * Può avere anche l'effetto di generare un cambiamento nello stato del gioco, nel nostro caso una cattura.
 */
public class Move {

    private final Location start, destination;
    private boolean capture;

    public Move(Location start, Location dest) {
        this.start = start;
        destination = dest;
        capture = false;
    }


    public Move(Location start, Location dest, boolean capture) {
        this.start = start;
        destination = dest;
        this.capture = capture;
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


    public Location getStart() {
        return this.start;
    }

    public Location getDestination() {
        return this.destination;
    }

    /**
     * Verifica che la mossa sia nei limiti massimi della scacchiera.
     * @param board scacchiera
     * @return true if move is valid
     */
    public boolean belongsToBoard(Board<Piece, Location> board) {
        return (board != null &&
                (board.isInsideBoard(start) && board.isInsideBoard(destination)));
    }

    public boolean isCapture() {
        return capture;
    }

    /**
     * Metodo che aggiorna il flag capture, se una mossa effettua una cattura oltre che il suo movimento stesso
     */
    public void becomeCaptureMove() {
        this.capture = true;
    }
}
