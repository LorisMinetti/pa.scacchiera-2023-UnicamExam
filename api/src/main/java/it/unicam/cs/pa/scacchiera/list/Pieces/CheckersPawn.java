package it.unicam.cs.pa.scacchiera.list.pieces;

import it.unicam.cs.pa.scacchiera.list.ILocation;
import it.unicam.cs.pa.scacchiera.list.Location;
import it.unicam.cs.pa.scacchiera.list.player.Player;

import java.util.HashSet;
import java.util.Set;

public class CheckersPawn implements Piece{

    private final Colour colour;
    private Location location;
    private final Player player;

    public CheckersPawn(Location location, Colour colour, Player player) {
        this.location = location;
        this.colour = colour;
        this.player = player;
    }

    /**
     * Ritorna il possibile movimento di un pezzo.
     * Questo però non terrà conto di tutte le reali destinazioni. Di questo dovrà occuparsene una futura scacchiera poichè le dimensioni e le regole di un gioco
     * sono streattamente dipendenti dal gioco e dalle dimensioni della scacchiera.
      //TODO: riconsiderare se utilizzare un Piece in input come parametro
     * @return Insieme delle destinazioni.
     */
    @Override
    public Set<ILocation> possibleMoves() {
        Set<ILocation> moves = new HashSet<>();
        int row = this.getLocation().getX();
        int column = this.getLocation().getY();
        moves.add(new Location(row -1, column +1));
        moves.add(new Location(row +1, column +1));
        moves.add(new Location(row +1, column -1));
        moves.add(new Location(row -1, column -1));
        return moves;
    }

    /**
     * @return Colore del pezzo [Black or White]
     */
    @Override
    public Colour getColour() {
        return this.colour;
    }

    /**
     * @return il giocatore propietario del pezzo
     */
    @Override
    public Player getPlayer() {
        return this.player;
    }

    /**
     * @return la locazione del pezzo in questione.
     */
    @Override
    public ILocation getLocation() {
        return this.location;
    }


}
