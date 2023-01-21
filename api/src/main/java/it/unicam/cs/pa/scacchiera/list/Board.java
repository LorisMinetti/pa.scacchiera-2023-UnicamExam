package it.unicam.cs.pa.scacchiera.list;

import java.util.List;
import java.util.Map;

/**
 * Gestisce la scacchiera. Ci saranno già determinati pezzi in determinate posizioni una volta scelto a
 * che gioco giocare.
 * @param <C>
 * @param <P>
 */
public interface Board<C, P> {


    boolean isValid(Cell c);

    /**
     * Restituisce il pezzo presente in una determinata casella.
     * @param c la cella data
     * @return P pezzo desiderato
     */
    P pieceAt(C c);

    /**
     * Restituisce la lista di pezzi presenti nella loro casella.
     * @return
     */
    Schema getCurrentSchema();



}
