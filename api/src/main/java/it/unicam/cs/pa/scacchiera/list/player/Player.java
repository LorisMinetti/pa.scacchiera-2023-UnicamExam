package it.unicam.cs.pa.scacchiera.list.player;

/**
 * @author Loris Minetti
 * Questa classe identifica un giocatore generico con un nome.
 */
public abstract class Player {
    private String name;
    private int score;

    /**
    * Costruttore della classe Player.
     * @param name Nome del giocatore.
     * Il punteggio viene inizializzato a 0.
     */
    public Player(String name) {
        this.name = name;
        this.score = 0;
    }

    public String getName() {
        return name;
    }

    public int getScore() { return score; }
}
