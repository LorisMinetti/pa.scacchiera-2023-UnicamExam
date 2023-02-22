package it.unicam.cs.pa.scacchiera.list;

import it.unicam.cs.pa.scacchiera.list.pieces.Piece;

import java.util.Optional;

public class LocationImpl implements Location{

    private int x;
    private int y;
    private boolean free;
    private Piece piece;

    public LocationImpl(int x, int y) {
        this.x = x;
        this.y = y;
        piece = null;
        free = true;
    }
    public LocationImpl(int x, int y, Piece piece, boolean free) {
        this.x = x;
        this.y = y;
        this.piece = piece;
        this.free = free;
    }

    /**
     * @return row
     */
    @Override
    public int getX() {
        return this.x;
    }

    /**
     * @return column
     */
    @Override
    public int getY() {
        return this.y;
    }

    /**
     * @return true se la location in questione è occupata
     */
    @Override
    public boolean isFree() {
        return true;
    }

    /**
     * Setta il valore del flag nel caso cambiasse.
     * @param free
     */
    @Override
    public void setFree(boolean free) {
        this.free = free;
    }

    /**
     * Get the piece inside this location
     * @return the piece value if non-null
     */
    @Override
    public Piece getPiece(){
        return piece;
    }
}
