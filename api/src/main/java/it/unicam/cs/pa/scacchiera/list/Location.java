package it.unicam.cs.pa.scacchiera.list;

import it.unicam.cs.pa.scacchiera.list.pieces.Piece;

import java.util.Optional;

public interface Location {
    /**
     * @return row
     */
    int getX();

    /**
     * @return column
     */
    int getY();

    /**
     * @return true se la location in questione è occupata
     */
    boolean isFree();

    void setFree(boolean free);

    /**
     * @return il pezzo presente, se presente.
     */
    Piece getPiece();
}
