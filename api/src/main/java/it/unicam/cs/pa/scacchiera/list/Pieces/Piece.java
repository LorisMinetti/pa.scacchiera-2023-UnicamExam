package it.unicam.cs.pa.scacchiera.list.pieces;

import it.unicam.cs.pa.scacchiera.list.Board;
import it.unicam.cs.pa.scacchiera.list.Colour;
import it.unicam.cs.pa.scacchiera.list.Location;
import it.unicam.cs.pa.scacchiera.list.Move;

import java.util.List;

/**
 * @author Loris Minetti
 * Questa classe è la classe base che definisce un pezzo. Ovvero l'entità principale per poter
 * giocare a qualsiasi gioco da scacchiera. Qesto avrà un valore, in alcuni casi serve per determinare quando un pezzo
 * vale più di un'altro, una posizione nella scacchiera
 */
public interface Piece{

    /**
     * @return Piece's Location
     */
    Location getLocation();

    /**
     * Setta la Locazione passata in input al pezzo in questione
     * @param loc location
     */
    void setLocatoion(Location loc);

    /**
     * @return Piece's player colour
     */
    Colour getColour();

}
