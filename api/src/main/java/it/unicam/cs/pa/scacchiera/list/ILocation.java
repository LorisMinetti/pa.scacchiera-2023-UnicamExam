package it.unicam.cs.pa.scacchiera.list;

import it.unicam.cs.pa.scacchiera.list.pieces.Colour;
import it.unicam.cs.pa.scacchiera.list.pieces.Piece;

import java.util.Optional;

public interface ILocation {
    /**
     * @return lo stato dello cella presa in consiederazione.
     */
    LocationStatus getStatus();

    /**
     * @return la riga della cella
     */
    int getX();

    /**
     * @return la colonna della cella
     */
    int getY();

    /**
     * @return il colore della cella. Bianco o nero.
     */
    Colour getColour();

    /**
     * @return Il pezzo, o nessun pezzo, in una locazione
     */
    Optional<Piece> getPiece();

}
