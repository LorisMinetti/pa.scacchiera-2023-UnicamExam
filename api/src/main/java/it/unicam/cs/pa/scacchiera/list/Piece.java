package it.unicam.cs.pa.scacchiera.list;

import it.unicam.cs.pa.scacchiera.list.util.Colour;

/**
 * Interfaccia che rappresenta un pezzo generico e i suoi comportamenti.
 */
public interface Piece {

    /**
     * @return il colore del pezzo
     */
    Location getLocation();

    /**
     * @return la location del pezzo
     */
    Colour getColour();

    /**
     * Setta la location del pezzo
     */
    void setLocation(Location loc);

    /**
     * Setta il colore del pezzo
     */
    void setColour(Colour colour);
}
