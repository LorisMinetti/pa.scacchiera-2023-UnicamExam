package it.unicam.cs.pa.scacchiera.list;

import it.unicam.cs.pa.scacchiera.list.player.Player;

import java.util.List;

/**
 * @author Loris Minetti
 * Responsabilità di fornire i comportamenti di una mossa. Possibilità di farla e il risultato che la mossa può generare.
 * Implementa anche il metodo neighboor dell'interfaccia Neighborhood almeno ha anche la possibilità
 * di tenere traccia de tutte le celle vicine.
 */
public interface Move<C> extends Neighboorhood<C> {

    /**
     * Metodo che permette di capire se una mossa è valida o no.
     * @param cell
     * @param neighborsCells
     * @return
     */
    boolean isLegal(C cell, List<C> neighborsCells);

    /**
     * Metodo che restituisce il giocatore che ha effettuato la mossa
     * @param move Mossa da effettuare
     * @return Giocatore che ha effettuato la mossa.
     */
    Player movedBy(Move move);

    boolean addSomeCOdells(List<C> cells);
}
