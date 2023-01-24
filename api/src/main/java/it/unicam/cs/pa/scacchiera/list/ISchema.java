package it.unicam.cs.pa.scacchiera.list;

import java.util.List;
import java.util.Map;

public interface ISchema {

    /**
     * Ritorna una schema completo di gioco partendo da due schemi.
     * @param first giocatore 1
     * @param second giocatore 2
     * @return schema completo di pezzi
     */
    Schema buildStage(Schema first, Schema second);


}
