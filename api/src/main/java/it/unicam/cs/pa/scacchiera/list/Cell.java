package it.unicam.cs.pa.scacchiera.list;


import it.unicam.cs.pa.scacchiera.list.pieces.Piece;

import java.util.Objects;
import java.util.Optional;

/**
 * Responsabilità: Creare una singola cella della scacchiera, che avrà una coppia di coordinate
 * ed anche la caratteristica di essere occupata, da un pezzo del gioco, o vuota.
 */
public class Cell {

    private final int row;
    private final int column;
    private final CellStatus status;   //EMPTY, OCCUPIED
    private Piece piece;
    /**
     * Create board cell at specific row and column.
     * @param row riga
     * @param column colonna
     *
     *         TODO: riconsiderare se passare come paramentro anche lo status, poichè in futuro
     *               se la logica sarà quella di costruire pezzo per pezzo (dunque anche quelli pieni), servirà
     *               specificare lo stato del pezzo pieno e di quello vuoto singolarmente.
     */

    public Cell(int row, int column, CellStatus status, Piece piece) {
        this.row=row;
        this.column=column;
        /*
        inzialmente viene inizializzata a vuota. La responsabilità di assegnare un pezzo durante una partita
        verrà lasciata altrove
         */
        this.status=status;
        this.piece=piece;
    }
    public Cell(int row, int column){
        this.row=row;
        this.column=column;
        this.status=CellStatus.EMPTY;
        this.piece=null;
    }

    /**
     * Getter per la riga
     * @return int riga
     */
    public int getRow(){
        return row;
    }

    /**
     * Getter per la colonna
     * @return int colonna
     */
    public int getColumn(){
        return column;
    }

    /**
     * Metodo che restituisce il pezzo della cella.
     * @return Ritorna l'oggetto se questo esiste altrimenti un optional NUll se questo è empty
     */
    public Optional<Piece> getPiece(){
        return Optional.ofNullable(piece);
    }

    public void setPiece(Piece piece){
        this.piece=piece;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cell cell = (Cell) o;
        return row == cell.row &&
                column == cell.column;
    }

    @Override
    public int hashCode() { return Objects.hash(row, column); }

    @Override
    public String toString() {
        return "Box{" +
                "row=" + row +
                ", column=" + column +
                '}';
    }

    /**
     * Method for getting the cell status information
     * @return the EMPTY or OCCUPIED status of this cell
     */
    public CellStatus getStatus() {
        return this.status;
    }



}
