package it.unicam.cs.pa.scacchiera.list;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Responsabilità per la gestione della regola.
 * Descrive la regola che permette il movimento di una cella data la sua posizione
 * @param <C>
 */
@FunctionalInterface
public interface Rule<C> {

    /**
     *
     * @param cell
     * @param neighboorsCell
     * @return the status of the cell after the method is applied, it can return null if the rule is not applyible
     */
    Optional<GameState> apply(C cell, List<C> neighboorsCell);
}
