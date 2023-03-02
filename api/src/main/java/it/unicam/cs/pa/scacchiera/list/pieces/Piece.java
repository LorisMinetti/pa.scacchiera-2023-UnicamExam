package it.unicam.cs.pa.scacchiera.list.pieces;

import it.unicam.cs.pa.scacchiera.list.util.Colour;
import it.unicam.cs.pa.scacchiera.list.Location;

public interface Piece {

    Location getLocation();

    Colour getColour();

    void setLocation(Location loc);

    void setColour(Colour colour);
}
