package it.unicam.cs.pa.scacchiera.list;

import it.unicam.cs.pa.scacchiera.list.pieces.Piece;

import static it.unicam.cs.pa.scacchiera.list.LocationStatus.FREE;

/**
 * L'interfaccia funzionale Moveable dà la caratteristica ad un oggetto,
 * in base alla sua natura e alle sue caratteristiche, di essere movibile dal suo posto, locazione o spazio.
 */
@FunctionalInterface
public interface Moveable {
    /**
     * Metodo che gestisce il movimento di un oggetto verso una locazione nuova.
     * @param moved, end
     */
    void move(Piece moved, Location end);

    /**
     * Metodo che gestisce l'effetto di fare una mossa. In ogni possibile caso.
     */
    default void moveEffect(Location startPosition, Location endPosition) {
        Piece pezzo = startPosition.getPiece().get();
        startPosition.setStatus(FREE);
        if(endPosition.getStatus() == FREE) {
            endPosition.getStatus().swapStatus();
            endPosition.setPiece(pezzo);
        } else {
            //TODO: implementare in seguito il metodo che mangia un pezzo
            //pezzoMangiato(endPosition);
            endPosition.setPiece(pezzo);
        }
    }
}
