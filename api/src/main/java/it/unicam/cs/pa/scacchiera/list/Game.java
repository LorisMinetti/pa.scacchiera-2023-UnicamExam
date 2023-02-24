package it.unicam.cs.pa.scacchiera.list;

public interface Game {


    /**
     * Check sulla presenza di almeno un pezzo per uno dei due giocatori.
     * Un giocatore senza pezzi, equivale al termine della partita con conseguente vittoria del giocatore avversario.
     * @return true se uno dei due giocatori non ha più pezzi.
     */
    boolean isTerminal();

    /**
     * Aggiornamento del gioco che cambia turno se lo stato non è ancora terminale.
     */
    void updateStatus();
}
