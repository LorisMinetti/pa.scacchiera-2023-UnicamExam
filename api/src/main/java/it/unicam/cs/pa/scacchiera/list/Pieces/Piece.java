package it.unicam.cs.pa.scacchiera.list.pieces;

import it.unicam.cs.pa.scacchiera.list.ILocation;
import it.unicam.cs.pa.scacchiera.list.player.Player;

import java.util.List;
import java.util.Set;

public interface Piece {

    /**
     * @return Colore del pezzo [Black or White]
     */
    Colour getColour();

    /**
     * @return il giocatore propietario del pezzo
     */
    Player getPlayer();

    /**
     * @return la locazione del pezzo in questione.
     */
    ILocation getLocation();

    /**
     * Ritorna il possibile movimento di un pezzo.
     * Questo però non terrà conto di tutte le reali destinazioni. Di questo dovrà occuparsene una futura scacchiera poichè le dimensioni e le regole di un gioco
     * sono streattamente dipendenti dal gioco e dalle dimensioni della scacchiera.
     * @return Insieme delle destinazioni.
     */
    Set<ILocation> possibleMoves();

}
