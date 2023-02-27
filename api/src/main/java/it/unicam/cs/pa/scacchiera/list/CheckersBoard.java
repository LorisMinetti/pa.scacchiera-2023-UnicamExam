package it.unicam.cs.pa.scacchiera.list;

import it.unicam.cs.pa.scacchiera.list.Checkers.Pawn;
import it.unicam.cs.pa.scacchiera.list.pieces.Piece;
import it.unicam.cs.pa.scacchiera.list.util.BackgroundColor;
import it.unicam.cs.pa.scacchiera.list.util.Colour;

import java.util.*;
import java.util.List;


public class CheckersBoard implements Board<Piece, Location> {
    private final Location[][] schema;
    private final int ROW_VALUE, COLUMN_VALUE;

    /**
     * Damiera che inizializza già un campo da gioco abile di avere le caselle con il colore di
     * background adatto al gioco della dama italiana.
     */
    public CheckersBoard(int row, int column) throws Exception {
        if (row < 0 || column < 0) {
            throw new Exception("Dimensioni della tabella negative.");
        }
        ROW_VALUE = row;
        COLUMN_VALUE = column;

        schema = new Location[ROW_VALUE][COLUMN_VALUE];

        /* Creare la scacchiera della Dama con colori alternati */
        boolean lastColor = false;
        for(int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {

                boolean darkRowsPlayer = i == 0 || i == 1 || i==2;
                boolean lightRowsPlayer = i == 5 || i == 6 || i == 7;
                if (lastColor) {
                    schema[i][j] = new LocationImpl(i, j, BackgroundColor.DARK);       /* Nelle celle di colore scuro ci saranno tutti i pezzi*/
                    if(darkRowsPlayer){
                        schema[i][j].setPiece(new Pawn( schema[i][j], Colour.BLACK));     //I pezzi scuri dell'avversario saranno nelle prime tre righe dall'alto
                    }
                    if(lightRowsPlayer){
                        schema[i][j].setPiece(new Pawn( schema[i][j], Colour.WHITE));     // I pezzi chiari del giocatore principale saranno nelle prime tre righe dal basso.
                    }
                } else {
                    schema[i][j] = new LocationImpl(i, j, BackgroundColor.LIGHT);    // Celle bianche che non verranno mai occupate durante la partita.
                }
                /* Toggle per il colore della casella adiacente */
                lastColor = !lastColor;
            }
            /*Shifta di 1 il colore per la riga successiva*/
            lastColor = !lastColor;
        }
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
     *
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
     *
     * @param loc1 locazione di partenza
     * @param loc2 locazione di destinazione distante 2
     * @return locazione intermedia
     */
    @Override
    public Location getIntermediateLocation(Location loc1, Location loc2) {
        if (!isDiagonal(loc1, loc2)) {
            throw new IllegalArgumentException("Le due posizioni fornite non sono diagonalmente adiacenti tra loro.");
        }
        int dx = Math.abs(loc2.getX() - loc1.getX());
        int dy = Math.abs(loc2.getY() - loc1.getY());

        //TODO: considerare il caso di dover rimuovere questa eccezione. Potrebbe bastare controllare il caso opposto
        if (dx != 2 || dy != 2) {
            throw new IllegalArgumentException("Le due posizioni fornite non hanno un displacement diagonale di 2");
        }

        int x = (loc1.getX() + loc2.getX()) / 2;
        int y = (loc1.getY() + loc2.getY()) / 2;

        return new LocationImpl(x, y);
    }

    /**
     * Locazione è diagonalmente adiacente ad un'altra locazione
     *
     * @param loc   locazione
     * @param check locazione su cui verificare adiacenza
     * @return true se locazioni sono diagonali
     */
    @Override
    public boolean isDiagonal(Location loc, Location check) {
        int xDiff = Math.abs(loc.getX() - check.getX());
        int yDiff = Math.abs(loc.getY() - check.getY());
        return xDiff == yDiff;
    }

    /**
     * Lista delle posizioni diagonali ed adiacenti ad uno specifico pezzo.
     *
     * @param piece
     * @return Lista
     */
    @Override
    public List<Location> getDiagonalAdjacentLocationsOfPiece(Piece piece) {
        List<Location> adjacents = new ArrayList<>();
        for (Location loc : allLocations()) {
            Location pieceLocum = piece.getLocation();
            if (isDiagonal(pieceLocum, loc) && isInsideBoard(loc)) {
                adjacents.add(pieceLocum);
            }
        }
        return adjacents;
    }

    /**
     * Restituisce il pezzo presente in una determinata casella.
     *
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
     * @return locazione diagonalmente successiva alla diagonale considerata
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

    /**
     * Applica una determinata mossa.
     *
     * @param move mossa da applicare.
     * @return true se la mossa ha un displacement maggiore di 1.
     * @throws Exception se la mossa non può essere applicabile
     */
    public boolean apply(Move move) throws Exception {
        boolean captureMove = false;
        if (move != null && move.belongsToBoard(this)) {
            /* Controllo se il pezzo che voglio muovere esiste */
            if (move.getStart().getPiece() != null) {
                setPiece(move.getDestination(), move.getStart().getPiece());
            }
            if (this.getMoveDisplacement(move) > 1) {
                captureMove = true;
            }
        }
        return captureMove;
    }

    /**
     * Metodo che ritorna il massimo displacement di una mossa.
     *
     * @param move mossa da considerare
     * @return displacement massimo dello spostamento tra due locazioni
     */
    public int getMoveDisplacement(Move move) {
        int startRow = move.getStart().getX();
        int endRow = move.getDestination().getX();
        int startColumn = move.getStart().getY();
        int endColumn = move.getDestination().getY();

        int displacementRow = Math.abs(endRow - startRow);
        int displacementColumn = Math.abs(endColumn - startColumn);

        return Math.max(displacementRow, displacementColumn);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CheckersBoard)) return false;
        CheckersBoard board = (CheckersBoard) o;
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
    public Piece getPiece(int row, int col) {
        return this.schema[row][col].getPiece();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("   1 2 3 4 5 6 7 8\n");  // Indica le colonne
        sb.append(" +-----------------+\n");

        // Ciclo sulle righe della scacchiera
        for (int row = 0; row < ROW_VALUE; row++) {
            sb.append(row + 1 + "| ");  // Indica la riga

            // Ciclo sulle colonne della scacchiera
            for (int col = 0; col < COLUMN_VALUE; col++) {
                Piece piece = getPiece(row, col);

                // Aggiunge alla stringa il simbolo della pedina (X per le pedine nere, O per le pedine bianche)
                if (piece != null) {
                    sb.append(piece.getColour() == Colour.BLACK ? "X " : "O ");
                } else {
                    sb.append("  ");
                }
            }

            sb.append("|\n");
        }

        sb.append(" +-----------------+\n");

        return sb.toString();
    }
}




