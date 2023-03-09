package it.unicam.cs.pa.scacchiera.list.player;

import it.unicam.cs.pa.scacchiera.list.GameFrame;
import it.unicam.cs.pa.scacchiera.list.Location;
import it.unicam.cs.pa.scacchiera.list.Move;
import it.unicam.cs.pa.scacchiera.list.pieces.Piece;
import it.unicam.cs.pa.scacchiera.list.util.Colour;

/**
 * @author Loris Minetti
 * Questa classe identifica un giocatore generico con un nome.
 */
public class Player {
    private final String name;
    private final Colour colour;
    /**
    * Costruttore della classe Player.
     * @param name Nome del giocatore.
     * Il punteggio viene inizializzato a 0.
     */
    public Player(String name, Colour colour) {
        this.name = name;
        this.colour = colour;
    }

    public Move play(GameFrame<Piece, Location> frame) {
        return null;
    }

    public String getName() {
        return name;
    }

    public Colour getColour() { return colour; }
}
