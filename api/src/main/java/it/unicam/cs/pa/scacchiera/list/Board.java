package it.unicam.cs.pa.scacchiera.list;

import it.unicam.cs.pa.scacchiera.list.pieces.Piece;
import java.util.List;

/**
 * Gestisce la scacchiera. Ci saranno già determinati pezzi in determinate posizioni una volta scelto a
 * che gioco giocare.
 */
public interface Board<P, L, M> {

    /**
     * Metodo che controlla se una determinata locazione rientra nelle coordinate della scacchiera.
     * @param l locazione da controllare
     * @return true se la locazione rientra nelle coordinate della scacchiera, false oppure
     */
    boolean isInsideBoard(L l);

    /**
     * Lista di tutte le locazioni della board.
     */
    List<L> allLocations();




    /**
     * Restituisce il pezzo presente in una determinata casella.
     * @param location la locazione data
     * @return P pezzo desiderato
     */
    P getPiece(Location location);

    /**
     * @param piece pezzo
     * @return Locazione L
     */
    L getPieceLocation(P piece);

    /**
     *
     * @param location locazione
     * @param newPiece nuovo pezzo
     */
    void setPiece(L location, P newPiece) throws Exception;


}
