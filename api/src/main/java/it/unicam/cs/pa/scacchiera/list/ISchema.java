package it.unicam.cs.pa.scacchiera.list;

import java.util.List;
import java.util.Map;

public interface ISchema<P> {

    /**
     * Ritorna una lista di elementi.
     * @return
     */
    List<P> getCurrentSchema( );

}
