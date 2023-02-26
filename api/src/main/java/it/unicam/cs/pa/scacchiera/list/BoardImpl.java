package it.unicam.cs.pa.scacchiera.list;

import it.unicam.cs.pa.scacchiera.list.pieces.Piece;
import java.util.*;
import java.util.List;


public class BoardImpl implements Board<Piece, Location> {
    private final Location[][] schema;
    private final int ROW_VALUE, COLUMN_VALUE;

    public BoardImpl(int row, int column) throws Exception {
        if (row < 0 || column < 0) {
            throw new Exception("Dimensioni della tabella negative.");
        }
        ROW_VALUE = row;
        COLUMN_VALUE = column;

        schema = new Location[ROW_VALUE][COLUMN_VALUE];
    }


    /**
     * Metodo che controlla se una determinata locazione rientra nelle coordinate della scacchiera.
     *
     * @param location locazione da controllare
     * @return true se la locazione rientra nelle coordinate della scacchiera, false oppure
     */
    @Override
    public boolean isInsideBoard(Location location) {
        return location.getX() < ROW_VALUE && location.getY() < COLUMN_VALUE && location.getX() >= 0 && location.getY() >= 0;
    }


    /**
     * Tutte le locazioni della board.
     */
    @Override
    public List<Location> allLocations() {
        List<Location> locations = new ArrayList<>();
        for (int x = 0; x < ROW_VALUE; x++) {
            for (int y = 0; y < COLUMN_VALUE; y++) {
                locations.add(new LocationImpl(x, y));
            }
        }
        return locations;
    }


    /**
     * Lista delle locazioni contenenti pezzi di uno specifico giocatore.
     * @param colour il giocatore desiderato
     * @return lista delle locazioni
     */
    public List<Location> getAllLocationsOfPlayer(Colour colour) {
        List<Location> locations = new ArrayList<>();
        for (Location loc : allLocations()) {
            Piece piece = loc.getPiece();
            if (piece.getColour() == colour) {
                locations.add(loc);
            }
        }
        return locations;
    }


    /**
     * Locazione intermedia in un movimento con un displacement diagonale uguale a 2.
     * @param loc1 locazione di partenza
     * @param loc2 locazione di destinazione distante 2
     * @return locazione intermedia
     */
    @Override
    public Location getIntermediateLocation(Location loc1, Location loc2) {
        if(!isDiagonal(loc1, loc2)){
            throw new IllegalArgumentException("Le due posizioni fornite non sono diagonalmente adiacenti tra loro.");
        }
        int dx = Math.abs(loc2.getX() - loc1.getX());
        int dy = Math.abs(loc2.getY() - loc1.getY());

        //TODO: considerare il caso di dover rimuovere questa eccezione. Potrebbe bastare controllare il caso opposto
        if(dx != 2 || dy != 2){
            throw new IllegalArgumentException("Le due posizioni fornite non hanno un displacement diagonale di 2");
        }

        int x = (loc1.getX() + loc2.getX()) / 2;
        int y = (loc1.getY() + loc2.getY()) / 2;

        return new LocationImpl(x, y);
    }

    /**
     * Locazione è diagonalmente adiacente ad un'altra locazione
     * @param loc locazione
     * @param check locazione su cui verificare adiacenza
     * @return true se locazioni sono diagonali
     */
    @Override
    public boolean isDiagonal(Location loc, Location check){
        int xDiff = Math.abs(loc.getX() - check.getX());
        int yDiff = Math.abs(loc.getY() - check.getY());
        return xDiff == yDiff;
    }

    /**
     * Lista delle posizioni diagonali ed adiacenti ad uno specifico pezzo.
     * @param piece
     * @return Lista
     */
    @Override
    public List<Location> getDiagonalAdjacentLocationsOfPiece(Piece piece){
        List<Location> adjacents = new ArrayList<>();
        for(Location loc : allLocations()) {
            Location pieceLocum = piece.getLocation();
            if(isDiagonal(pieceLocum, loc) && isInsideBoard(loc)){
                adjacents.add(pieceLocum);
            }
        }
        return adjacents;
    }


//    /**
//     * Metodo di comodo per definire l'adiacenza diagonale di una cella rispetto ad un'altra, tenendo conto della
//     * sua appartenenza all'interno delle coordinate della scacchiera.
//     * @param piece
//     * @param adjacents
//     * @param loc
//     */
//    private void isDiagonallyAdjacent(Piece piece, List<Location> adjacents, Location loc) {
//        if (piece == loc.getPiece()) {
//            if (isInsideBoard(this.schema[loc.getX() + 1][loc.getY() + 1])) {
//                adjacents.add(this.schema[loc.getX() + 1][loc.getY() + 1]);
//            }
//            if (isInsideBoard(this.schema[loc.getX() + 1][loc.getY() - 1])) {
//                adjacents.add(this.schema[loc.getX() + 1][loc.getY() - 1]);
//            }
//            if (isInsideBoard(this.schema[loc.getX() - 1][loc.getY() + 1])) {
//                adjacents.add(this.schema[loc.getX() - 1][loc.getY() + 1]);
//            }
//            if (isInsideBoard(this.schema[loc.getX() - 1][loc.getY() - 1])) {
//                adjacents.add(this.schema[loc.getX() - 1][loc.getY() - 1]);
//            }
//        }
//    }


    /**
     * Restituisce il pezzo presente in una determinata casella.
     * @param location la locazione data
     * @return P pezzo desiderato
     */
    @Override
    public Piece getPiece(Location location) {
        return this.schema[location.getX()][location.getY()].getPiece();
    }


    /**
     * @param location
     * @param piece
     */
    @Override
    public void setPiece(Location location, Piece piece) throws Exception {
        Location old = piece.getLocation();
        if (location != null && isInsideBoard(location)) {

            /* Toglie il pezzo dalla posizione di partenza */
            this.schema[old.getX()][old.getY()] = null;
            /* Setta il pezzo nella nuova posizione */
            this.schema[location.getX()][location.getY()].setPiece(piece);
        } else
            throw new Exception("locazione non definita, o fuori i range della scacchiera.");
    }


    /**
     * Ritorna la locazione diagnalmente distante 2 posizioni. Questa locazione dovrà essere presente all'interno della scacchiera
     * per poter essere occupabile. Necessaria per le operazioni di "mangiata" o JUMP.
     *
     * @param current
     * @param diagonallyAdjacent
     * @return
     */
    public Location getNextDiagonalSpot(Location current, Location diagonallyAdjacent) {
        int x = diagonallyAdjacent.getX() - current.getX();
        int y = diagonallyAdjacent.getY() - current.getY();
        int deltaX = diagonallyAdjacent.getX() + x;
        int deltaY = diagonallyAdjacent.getY() + y;
        Location nextDiagonal = new LocationImpl(deltaX, deltaY);
        if (isInsideBoard(nextDiagonal)) {
            return this.schema[deltaX][deltaY];
        }
        return null;
    }

    public Location[][] getSchema() {
        return schema;
    }

    /**
     * Applica una determinata mossa.
     * @param move mossa da applicare.
     * @throws Exception se la mossa non può essere applicabile
     * @return true se la mossa ha un displacement maggiore di 1.
     */
    public boolean apply(Move move) throws Exception {
        boolean captureMove = false;
        if (move != null && move.belongsToBoard(this)) {
            /* Controllo se il pezzo che voglio muovere esiste */
            if (move.getStart().getPiece() != null) {
                setPiece(move.getDestination(), move.getStart().getPiece());
            }
            if(this.getMoveDisplacement(move) > 1){
                captureMove = true;
            }
        }
        return captureMove;
    }

    /**
     * Metodo che ritorna il massimo displacement di una mossa.
     * @param move mossa da considerare
     * @return displacement massimo dello spostamento tra due locazioni
     */
    public int getMoveDisplacement(Move move) {
        int startRow = move.getStart().getX();
        int endRow = move.getDestination().getX();
        int startColumn = move.getStart().getY();
        int endColumn = move.getDestination().getY();

        int displacementRow = Math.abs( endRow - startRow );
        int displacementColumn = Math.abs( endColumn - startColumn );

        return Math.max( displacementRow, displacementColumn );
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BoardImpl)) return false;
        BoardImpl board = (BoardImpl) o;
        return ROW_VALUE == board.ROW_VALUE &&
                COLUMN_VALUE == board.COLUMN_VALUE &&
                Arrays.equals(schema, board.schema);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(ROW_VALUE, COLUMN_VALUE);
        result = 31 * result + Arrays.hashCode(schema);
        return result;
    }

    @Override
    public int getROW_VALUE() {
        return ROW_VALUE;
    }
    @Override
    public int getCOLUMN_VALUE() {
        return COLUMN_VALUE;
    }

    @Override
    public String toString() {
        return "BoardImpl{" +
                "schema=" + Arrays.toString(schema) +
                ",\n ROW_VALUE=" + ROW_VALUE +
                ", COLUMN_VALUE=" + COLUMN_VALUE +
                '}';
    }
}




