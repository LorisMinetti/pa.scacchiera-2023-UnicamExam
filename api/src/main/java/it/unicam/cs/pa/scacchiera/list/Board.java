package it.unicam.cs.pa.scacchiera.list;

import it.unicam.cs.pa.scacchiera.list.util.Colour;

import java.util.List;

/**
 * Gestisce la scacchiera. Ci saranno già determinati pezzi in determinate posizioni una volta scelto a
 * che gioco giocare.
 */
public interface Board<P extends Piece, L extends Location> {

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
     * Le quattro locazioni diagonali al pezzo passato in input
     * @param piece pezzo considerato
     * @return locazioni diagonali
     */
    List<Location> getDiagonalAdjacentLocationsOfPiece(Piece piece);

    /**
     * Ritorna la locazione diagonale subito dopo la locazione diagonale trovata rispetto ad una locazione.
     * Si fa dunque un salto distanza 2 dalla locazione 'current'.
     * @param current locazione di partenza
     * @param diagonallyAdjacent locazione diagonale adiacente a 'current'
     * @return locazione diagonale subito dopo 'diagonallyAdjacent'
     */
    Location getNextDiagonalSpot(Location current, Location diagonallyAdjacent);

    /**
     * Restituisce tutte le locazioni di uno specifico giocatore nel quale è presente un pezzo.
     * @param colour colore del giocatore
     * @return lista di locazioni
     */
    List<Location> getAllLocationsOfPlayer(Colour colour);

    /**
     * Locazione intermedia in un movimento con un displacement diagonale uguale a 2.
     * @param loc1 locazione di partenza
     * @param loc2 locazione di destinazione distante 2
     * @return locazione intermedia
     */
    Location getIntermediateLocation(Location loc1, Location loc2);


    /**
     * Locazione è diagonalmente adiacente ad un'altra locazione
     * @param loc locazione
     * @param check locazione su cui verificare adiacenza
     * @return true se locazioni sono diagonali
     */
    boolean isDiagonal(Location loc, Location check);


    /**
     * Applica una mossa.
     * @param move mossa
     * @return true se la mossa mangia una pedina avversaria, false altrimenti
     */
    boolean apply(Move move) throws Exception;

    /**
     *
     * @param location locazione
     * @param newPiece nuovo pezzo
     */
    void setPiece(L location, P newPiece) throws Exception;

    Location[][] getSchema();

    int getROW_VALUE();
    int getCOLUMN_VALUE();

    P getPiece(int row, int col);
}
