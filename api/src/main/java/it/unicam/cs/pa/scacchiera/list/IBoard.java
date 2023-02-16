package it.unicam.cs.pa.scacchiera.list;

import it.unicam.cs.pa.scacchiera.list.pieces.Piece;
import it.unicam.cs.pa.scacchiera.list.player.Player;

import javax.xml.validation.Schema;
import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * Gestisce la scacchiera. Ci saranno già determinati pezzi in determinate posizioni una volta scelto a
 * che gioco giocare.
 */
public interface IBoard<Move, Piece, ILocation> {

    /**
     * Metodo che controlla se una determinata cella rientra nelle coordinate della scacchiera.
     * @param loc cella da controllare
     * @return true se la cella rientra nelle coordinate della scacchiera, false oppure
     */
    boolean isValid(ILocation loc);

    /**
     * Restituisce il pezzo presente in una determinata casella.
     * @param loc la cella data
     * @return P pezzo desiderato
     */
    Optional<Piece> getP(ILocation loc);

    /**
     * Restituisce l'insieme di tutte le possibili mosse che un pezzo può fare.
     * @param piece pezzo da prendere in considerazione.
     * @return Set delle locazioni possibili nel quale il pezzo può finire.
     */
    Set<ILocation> getAvailableMovesOfPiece(Piece piece) throws Exception;

    /**
     * Restituisce true se il pezzo viene rimosso dalla cella, false altrimenti.
     * @param location locazione dal quale eliminare il pezzo
     * @return true se l'eliminazione và a buon fine, false altrimenti.
     */
    boolean removePieceFrom(ILocation location);

}
