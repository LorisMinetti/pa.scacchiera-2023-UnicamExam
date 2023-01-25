package it.unicam.cs.pa.scacchiera.list;

import it.unicam.cs.pa.scacchiera.list.Pieces.Piece;
import it.unicam.cs.pa.scacchiera.list.player.Player;

import java.util.List;
import java.util.Optional;

/**
 * Gestisce la scacchiera. Ci saranno già determinati pezzi in determinate posizioni una volta scelto a
 * che gioco giocare.
 */
public interface Board {

    /**
     * Metodo che controlla se una determinata cella rientra nelle coordinate della scacchiera.
     * @param c cella da controllare
     * @return true se la cella rientra nelle coordinate della scacchiera, false oppure
     */
    boolean isValid(Cell c);

    /**
     * Dato un giocatore, il suo schema momentaneo dei pezzi che ha, ed il pezzo che vuole muovere, questo
     * metodo restituisce la lista di tutte le celle che possono essere occupate, deve quindi verificare
     * le possibilità di movimento del pezzo e che la cella di destinazione sia libera.
     * @param actaul schema dei pezzi momentaneo
     * @param pz pezzo da muovere
     * @param p giocatore
     * @return Lista di possibili celle di destinazione
     */
//    List<Cell> possibleMoves(Schema actaul, Piece pz, Player p);
//
    /**
     * Restituisce il pezzo presente in una determinata casella.
     * @param cell la cella data
     * @return P pezzo desiderato
     */
    Optional<Piece> getP(Cell cell);



}
