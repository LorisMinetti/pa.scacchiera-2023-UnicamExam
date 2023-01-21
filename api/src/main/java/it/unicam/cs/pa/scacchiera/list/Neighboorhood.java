package it.unicam.cs.pa.scacchiera.list;

import java.util.*;

@FunctionalInterface
public interface Neighboorhood<C> {

    /**
     * @return Adjacent cell list.
     */
    List<C> neighboors();

}
