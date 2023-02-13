package it.unicam.cs.pa.scacchiera.list;

import java.util.Optional;

public enum LocationStatus {

    FREE,
    OCCUPIED;

    /**
     *
     * @return true if the cell is empty, false otherwise
     */
    public boolean isEmpty() {  return this == FREE;  }

    public boolean isOccupied() {  return this == OCCUPIED;  }

    public LocationStatus getValue(){
        return this;
    }

    /**
     * Metodo per aggiornare lo stato la cella.
     * @return the status of the cell that has been changed to the opposite.
     */
    public LocationStatus swapStatus(){
        return (this == FREE) ? OCCUPIED : FREE;
    }
}
