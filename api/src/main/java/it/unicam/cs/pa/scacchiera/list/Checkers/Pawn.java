package it.unicam.cs.pa.scacchiera.list.Checkers;

import it.unicam.cs.pa.scacchiera.list.Board;
import it.unicam.cs.pa.scacchiera.list.Colour;
import it.unicam.cs.pa.scacchiera.list.Location;
import it.unicam.cs.pa.scacchiera.list.Move;
import it.unicam.cs.pa.scacchiera.list.pieces.Piece;

import java.util.ArrayList;
import java.util.List;

public class Pawn extends Piece {


    private Location location;
    private Colour playerColour;
    boolean king;

    public Pawn(Location location, Colour colour){
        this.location = location;
        playerColour = colour;
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
}
