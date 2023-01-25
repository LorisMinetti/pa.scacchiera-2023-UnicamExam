package it.unicam.cs.pa.scacchiera.list;

import java.util.List;
import java.util.Optional;

/**
 * Responsabilità per la gestione della regola.
 * Descrive la regola che permette il movimento di una cella data la sua posizione
 * @param <C>
 */
@FunctionalInterface
public interface Rule<C> {

    /**
     * Metodo che applica una regola ad una cella considerata la lista delle sue vicine e ritorna uno schema della scacchiera, che (se andata a buon fine) rappresenterà
     * lo schema aggiornato dopo la mossa
     * @param cell cella al quale applicare la regola
     * @param neighboorsCell lista di celle vicine
     * @return the status of the cell after the method is applied, it can return null if the rule is not applyible
     */
    Optional<Schema> apply(C cell, List<C> neighboorsCell);
}
