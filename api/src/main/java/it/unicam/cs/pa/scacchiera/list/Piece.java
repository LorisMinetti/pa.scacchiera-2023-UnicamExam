package it.unicam.cs.pa.scacchiera.list;

import it.unicam.cs.pa.scacchiera.list.player.Player;

/**
 * @author Loris Minetti
 * Questa classe è la classe base che definisce un pezzo. Ovvero l'entità principale per poter
 * giocare a qualsiasi gioco da scacchiera. Qesto avrà un valore, in alcuni casi serve per determinare quando un pezzo
 * vale più di un'altro, una posizione nella scacchiera
 */
public abstract class Piece{

    private int value;             //  Potrebbe non essere utile
    private Cell cellLocation;     // Posizione del pezzo nella scacchiera
    private final Player owner;          // un pezzo è di uno specifico giocatore

    public Piece(Player owner, Cell cellLocation) {
        this.cellLocation = cellLocation;
        this.owner = owner;
    }

    public Cell getCellLocation() {
        return this.cellLocation;
    }

    public Player getOwner() { return this.owner; }

}
