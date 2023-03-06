package it.unicam.cs.pa.scacchiera.list;

import it.unicam.cs.pa.scacchiera.list.pieces.Piece;
import it.unicam.cs.pa.scacchiera.list.util.BackgroundColor;

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

    /**
     * @return il colore di backGround di quella casella.
     */
    BackgroundColor getBgColor();
}
