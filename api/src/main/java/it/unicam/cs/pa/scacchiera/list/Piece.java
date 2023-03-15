package it.unicam.cs.pa.scacchiera.list;

import it.unicam.cs.pa.scacchiera.list.util.Colour;

public interface Piece {

    Location getLocation();

    Colour getColour();

    void setLocation(Location loc);

    void setColour(Colour colour);
}
