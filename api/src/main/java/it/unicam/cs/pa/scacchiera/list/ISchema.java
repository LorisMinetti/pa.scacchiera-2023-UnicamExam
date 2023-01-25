package it.unicam.cs.pa.scacchiera.list;

import it.unicam.cs.pa.scacchiera.list.Pieces.Piece;

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

    /**
     * Metodo che date due coordinate mi ritorna il pezzo in quel punto
     * @param x ascisse
     * @param y ordinate
     * @return Pezzo in quella coordinata
     */
//    Piece getPiece(int x, int y);


}
