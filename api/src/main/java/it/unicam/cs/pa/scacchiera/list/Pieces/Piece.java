package it.unicam.cs.pa.scacchiera.list.pieces;

/**
 * @author Loris Minetti
 * Questa classe è la classe base che definisce un pezzo. Ovvero l'entità principale per poter
 * giocare a qualsiasi gioco da scacchiera. Qesto avrà un valore, in alcuni casi serve per determinare quando un pezzo
 * vale più di un'altro, una posizione nella scacchiera
 */
public abstract class Piece{

    private int value;             //  Potrebbe non essere utile
    private final int owner;          // un pezzo è di uno specifico giocatore

    public Piece(int owner) {
        this.owner = owner;
    }

    public int getOwner() { return this.owner; }

}
