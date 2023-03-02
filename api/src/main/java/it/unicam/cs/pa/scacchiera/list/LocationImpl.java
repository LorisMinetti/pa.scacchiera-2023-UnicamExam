package it.unicam.cs.pa.scacchiera.list;

import it.unicam.cs.pa.scacchiera.list.pieces.Piece;
import it.unicam.cs.pa.scacchiera.list.util.BackgroundColor;

import java.util.Optional;


public class LocationImpl implements Location{

    private BackgroundColor bgColor;
    private final int column;
    private final int row;
    private boolean free;
    private Piece piece;

    public LocationImpl(int row, int column) {
        this.column = column;
        this.row = row;
        piece = null;
        free = true;
    }
    public LocationImpl(int row, int column, BackgroundColor color) {
        this.column = column;
        this.row = row;
        bgColor = color;
        piece = null;
        free = true;
    }
    public LocationImpl(int row, int column, Piece piece) {
        this.column = column;
        this.row = row;
        this.setPiece(piece);
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
     * @return true se la location in questione è occupata
     */
    @Override
    public boolean isFree() {
        return getPiece().isPresent() ? false : true;
    }


    /**
     * Get the piece inside this location
     * @return the piece value if non-null
     */
    @Override
    public Optional<Piece> getPiece(){
        return Optional.ofNullable(this.piece);
    }


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
        return "LocationImpl{" +
                "column=" + column +
                ", row=" + row +
                ", free=" + free +
                ", piece=" + piece +
                '}';
    }

}
