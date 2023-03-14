package it.unicam.cs.pa.scacchiera.list;

import it.unicam.cs.pa.scacchiera.list.pieces.Piece;
import it.unicam.cs.pa.scacchiera.list.util.BackgroundColor;

import java.util.Objects;
import java.util.Optional;

/**
 * Classe che rappresenta una locazione all'interno della damiera, con le sue caratteristiche.
 * Concepibile anche come una coordinata.
 */
public class LocationImpl implements Location{

    private final BackgroundColor bgColor;
    private final int column;
    private final int row;
    private boolean free;
    private Piece piece;

    public LocationImpl(int row, int column, BackgroundColor color) {
        this.column = column;
        this.row = row;
        bgColor = color;
        piece = null;
        free = true;
    }

    /**
     * @return row
     */
    @Override
    public int getColumn() {
        return this.column;
    }

    /**
     * @return column
     */
    @Override
    public int getRow() {
        return this.row;
    }

    @Override
    public BackgroundColor getBgColor() {
        return bgColor;
    }

    /**
     * @return true se la location in questione è occupata da un pezzo di gioco
     */
    @Override
    public boolean isFree() {
        return getPiece().isEmpty();
    }


    /**
     * Get per il pezzo nella locazione in questione
     * @return the piece value if non-null
     */
    @Override
    public Optional<Piece> getPiece(){
        return Optional.ofNullable(piece);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LocationImpl)) return false;
        LocationImpl location = (LocationImpl) o;
        return column == location.column && row == location.row && free == location.free && bgColor == location.bgColor && Objects.equals(piece, location.piece);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bgColor, column, row, free, piece);
    }

    /**
     * Configurazione del pezzo contenuto nella locazione
     * @param piece pezzo da settare
     */
    public void setPiece(Piece piece){
        if(piece != null){
            this.piece = piece;
            free = false;
        } else {
            this.piece = null;
            free = true;
        }
    }


    @Override
    public String toString() {
        return row + ", " + column + " - ";
    }

}
