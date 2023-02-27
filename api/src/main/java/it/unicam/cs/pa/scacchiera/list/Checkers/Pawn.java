package it.unicam.cs.pa.scacchiera.list.Checkers;

import it.unicam.cs.pa.scacchiera.list.util.Colour;
import it.unicam.cs.pa.scacchiera.list.Location;
import it.unicam.cs.pa.scacchiera.list.pieces.Piece;

public class Pawn implements Piece {

    private Location location;
    private Colour colour;
    boolean king;

    public Pawn(Location location, Colour colour){
        this.location = location;
        this.colour = colour;
        king = false;
    }

    /* Pedina della dama può essere anche una Dama */
    public boolean isKing() {
        return king;
    }

    /**
     * Cambia lo stato della pedina facendola diventare Dama.
     */
    public void becomeKing() {
        if(!king){
            this.king = true;
        }
    }

    @Override
    public Location getLocation() {
        return this.location;
    }

    @Override
    public Colour getColour() {
        return this.colour;
    }

    @Override
    public void setLocation(Location loc) {
        this.location=loc;
    }

    @Override
    public void setColour(Colour colour) {
        this.colour=colour;
    }
}
