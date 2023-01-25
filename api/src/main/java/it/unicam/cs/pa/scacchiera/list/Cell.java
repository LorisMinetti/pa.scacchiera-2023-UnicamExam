package it.unicam.cs.pa.scacchiera.list;


import java.util.Objects;

/**
 * Responsabilità: Creare una singola cella della scacchiera, che avrà una coppia di coordinate
 * ed anche la caratteristica di essere occupata, da un pezzo del gioco, o vuota.
 */
public class Cell {

    private int row;
    private int column;
    private CellStatus status;   //EMPTY, OCCUPIED

    /**
     * Create board cell at specific row and column.
     * @param row riga
     * @param column colonna
     *
     *         TODO: riconsiderare se passare come paramentro anche lo status, poichè in futuro
     *               se la logica sarà quella di costruire pezzo per pezzo (dunque anche quelli pieni), servirà
     *               specificare lo stato del pezzo pieno e di quello vuoto singolarmente.
     */
    public Cell(int row, int column, CellStatus status) {
        this.row=row;
        this.column=column;
        /*
        inzialmente viene inizializzata a vuota. La responsabilità di assegnare un pezzo durante una partita
        verrà lasciata altrove
         */
        this.status=status;
    }

    /**
     *
     * @return
     */
    public int getRow(){
        return row;
    }

    /**
     *
     * @return
     */
    public int getColumn(){
        return column;
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
