package it.unicam.cs.pa.scacchiera.list.player;

import it.unicam.cs.pa.scacchiera.list.GameFrame;
import it.unicam.cs.pa.scacchiera.list.Location;
import it.unicam.cs.pa.scacchiera.list.Move;
import it.unicam.cs.pa.scacchiera.list.Piece;
import it.unicam.cs.pa.scacchiera.list.util.Colour;

import java.util.Objects;

/**
 * @author Loris Minetti
 * Questa classe identifica un giocatore generico con un nome.
 */
public class Player {
    private final String name;
    private final Colour colour;


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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Player)) return false;
        Player player = (Player) o;
        return Objects.equals(name, player.name) && colour == player.colour;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, colour);
    }
}
