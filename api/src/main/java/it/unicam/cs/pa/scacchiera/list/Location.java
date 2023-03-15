package it.unicam.cs.pa.scacchiera.list;

import java.util.Optional;

public interface  Location {
    /**
     * @return row
     */
    int getColumn();

    /**
     * @return column
     */
    int getRow();

    /**
     * @return true se la location in questione è occupata
     */
    boolean isFree();


    /**
     * @return il pezzo presente, se presente.
     */
    Optional<Piece> getPiece();

    /**
     * setta il pezzo specificato nella posizione corrente
     * @param piece pezzo da settare
     */
    void setPiece(Piece piece);
}
