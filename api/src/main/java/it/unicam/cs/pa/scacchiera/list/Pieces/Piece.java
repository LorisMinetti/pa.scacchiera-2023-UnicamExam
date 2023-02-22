package it.unicam.cs.pa.scacchiera.list.pieces;

import it.unicam.cs.pa.scacchiera.list.Colour;
import it.unicam.cs.pa.scacchiera.list.Location;

public abstract class Piece {

    private Location location;
    private Colour colour;

    public Piece(){};

    public Location getLocation(){
        return this.location;
    }
    public Colour getColour(){
        return this.colour;
    }

    public void setLocation(Location loc) {
        this.location = location;
    }

    public void setColour(Colour colour) {
        this.colour = colour;
    }
}
