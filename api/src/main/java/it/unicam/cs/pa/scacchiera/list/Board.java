package it.unicam.cs.pa.scacchiera.list;

import it.unicam.cs.pa.scacchiera.list.pieces.Colour;
import it.unicam.cs.pa.scacchiera.list.pieces.Piece;

import javax.swing.text.BadLocationException;
import java.util.*;
import java.util.stream.Collectors;

import static it.unicam.cs.pa.scacchiera.list.LocationStatus.OCCUPIED;

public class Board implements IBoard<Move, Piece, ILocation> {

    private final int DEFAULT_BOARD_ROW_SIZE;
    private final int DEFAULT_BOARD_COLUMN_SIZE;
    private final ILocation[][] locations;
    private List<Piece> onBoardPiece;     //pezzi presenti sulla scacchiera

    /**
     * Costruttore di una scacchiera che prende in input il valore che andrà a definire
     * numero di righe e numero di colonne.
     */
    public Board(int row, int column) {
        DEFAULT_BOARD_ROW_SIZE = row;          //Assegno un valore sia a row che a column nel caso in cui la scacchiera possa essere anche NON quadrata.
        DEFAULT_BOARD_COLUMN_SIZE = column;
        this.locations = initializeBoardLocations();
    }

    public ILocation[][] getLocations() {
        return this.locations;
    }
    public int getDEFAULT_BOARD_ROW_SIZE(){
        return this.DEFAULT_BOARD_ROW_SIZE;
    }
    public int getDEFAULT_BOARD_COLUMN_SIZE() {
        return this.DEFAULT_BOARD_COLUMN_SIZE;
    }
    /**
     * Metodo che controlla se una determinata cella rientra nelle coordinate della scacchiera.
     *
     * @param loc@return true se la cella rientra nelle coordinate della scacchiera, false oppure
     */
    @Override
    public boolean isValid(ILocation loc) {
        return (loc.getX() < DEFAULT_BOARD_ROW_SIZE && loc.getX() > 0) && ((loc.getY() < DEFAULT_BOARD_COLUMN_SIZE && loc.getY() > 0));
    }

    /**
     * Restituisce il pezzo presente in una determinata casella.
     *
     * @return P pezzo desiderato
     */
    @Override
    public Optional<Piece> getP(ILocation loc) {
        return loc.getPiece();
    }


    /**
     * Restituisce l'insieme di tutte le possibili mosse che un pezzo può fare.
     *
     * @param piece pezzo da prendere in considerazione.
     * @return Set delle locazioni possibili nel quale il pezzo può finire.
     */
    @Override
    public Set<ILocation> getAvailableMovesOfPiece(Piece piece) throws Exception {
        Set<ILocation> availableMoves = new HashSet<>();
        if(piece != null){
            for(ILocation loc : piece.possibleMoves()){
                if(isValid(loc)){
                    availableMoves.add(loc);
                } else {
                    throw new Exception("Invalid move");
                }
            }
            return availableMoves;
        } else
            throw new NullPointerException("Pezzo nullo.");
    }

    //TODO: rimossi i metodi getALlPieces e 'eat' probabilmente vanno assegnati come responsabilità al controllore della
    //scacchiera.

    /**
     * Restituisce true se il pezzo viene rimosso dalla cella, false altrimenti.
     *
     * @param iLocation locazione dal quale eliminare il pezzo
     * @return true se l'eliminazione và a buon fine, false altrimenti.
     */
    @Override
    public boolean removePieceFrom(ILocation iLocation) {
        if (iLocation.getStatus() == OCCUPIED) {
            iLocation.getStatus().swapStatus();
            return true;
        } else return false;
    }


    /**
     * Meotodo che inzializza le celle della Board. Tiene conto della coordianta ed imposta in base a quella il colore.
     * i.e. una coordinata con entrambe i numeri pari ( 0,0  0,2  6,4  etc..) nella dama e negli scacchi avranno un colore opposto.
     * Ma la certezza è chè questa caratteristica della coordinata (la coppia di indici), ovvero se entrambe pari o entrambe dispari può esseere applicabile
     * come pattern per assegnare i colori di ogni cella.
     *
     * @return tutte le celle necessarie.
     */
    //TODO: fare in modo che l'inizializzazione del colore venga gestita in base al gioco considerato (scacchi, dama, reversi).
    // Basterebbe inserire un costruttore di Location che non inizializza il colore. e poi estenderlo al controller del game in questione.
    // in modo da poter sovraccaricare questo stesso metodo e customizzarlo in base al gioco.
    public ILocation[][] initializeBoardLocations() {
        ILocation[][] locations = new ILocation[DEFAULT_BOARD_ROW_SIZE][DEFAULT_BOARD_COLUMN_SIZE];
        for (int i = 0; i < DEFAULT_BOARD_ROW_SIZE; i++) {
            for (int j = 0; j < DEFAULT_BOARD_COLUMN_SIZE; j++) {
                new Location(i, j);
            }
        }
        return locations;
    }

    static void initializeLocation(HashSet<ILocation> locations, int i, int j) {
        if (i % 2 == 0 && j % 2 == 0) {
            locations.add(
                    new Location(i, j, LocationStatus.FREE, Colour.BLACK));
        }
        if (i % 2 != 0 && j % 2 != 0) {
            locations.add(
                    new Location(i, j, LocationStatus.FREE, Colour.WHITE));
        }
    }


    @Override
    public String toString() {
        StringBuilder a = new StringBuilder();
        for(int i = 0; i < getDEFAULT_BOARD_COLUMN_SIZE(); i++){
            for(int j = 0; j < getDEFAULT_BOARD_ROW_SIZE(); j++){
                a.append(locations[i][j]);
            }
            System.out.println();
        }
        return a.toString();
    }
}
