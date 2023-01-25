package it.unicam.cs.pa.scacchiera.list.player;

/**
 * @author Loris Minetti
 * Questa classe identifica un giocatore generico con un nome.
 */
public abstract class Player {
    private final String name;
    private static int score;

    /**
    * Costruttore della classe Player.
     * @param name Nome del giocatore.
     * Il punteggio viene inizializzato a 0.
     */
    public Player(String name) {
        this.name = name;
        score = 0;
    }

    public String getName() {
        return name;
    }

    public int getScore() { return score; }

    public void updateScore(int s) {
        score += s;
    }
}
