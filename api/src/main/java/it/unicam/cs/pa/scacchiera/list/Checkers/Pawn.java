package it.unicam.cs.pa.scacchiera.list.Checkers;

import it.unicam.cs.pa.scacchiera.list.Board;
import it.unicam.cs.pa.scacchiera.list.Colour;
import it.unicam.cs.pa.scacchiera.list.Location;
import it.unicam.cs.pa.scacchiera.list.Move;
import it.unicam.cs.pa.scacchiera.list.pieces.Piece;

import java.util.ArrayList;
import java.util.List;

public class Pawn implements Piece {


    private Location location;
    private Colour playerColour;
    boolean isKing;

    public Pawn(Location location, Colour colour){
        this.location = location;
        playerColour = colour;
        isKing = false;
    }


    /**
     * @return Piece's Location
     */
    @Override
    public Location getLocation() {
        return null;
    }

    /**
     * Setta la Locazione passata in input al pezzo in questione
     * @param loc locazione da settare
     */
    @Override
    public void setLocatoion(Location loc) {

    }

    /**
     * @return Piece's player colour
     */
    @Override
    public Colour getColour() {
        return null;
    }

}
