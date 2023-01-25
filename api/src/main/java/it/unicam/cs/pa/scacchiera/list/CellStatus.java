package it.unicam.cs.pa.scacchiera.list;

public enum CellStatus {

    EMPTY,
    OCCUPIED;

    /**
     *
     * @return true if the cell is empty, false otherwise
     */
    public boolean isEmpty() {  return this == EMPTY;  }

    public boolean isOccupied() {  return this == OCCUPIED;  }

    public CellStatus getValue(){
        return this;
    }

    /**
     * Metodo per aggiornare lo stato la cella.
     * @return the status of the cell that has been changed to the opposite.
     */
    private CellStatus changeState(){
        CellStatus newState;
        newState = (this == EMPTY)? OCCUPIED : EMPTY;
        return newState;
    }
}
