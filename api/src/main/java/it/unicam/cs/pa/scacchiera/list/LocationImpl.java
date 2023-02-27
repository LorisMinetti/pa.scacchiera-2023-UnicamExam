package it.unicam.cs.pa.scacchiera.list;

import it.unicam.cs.pa.scacchiera.list.pieces.Piece;
import it.unicam.cs.pa.scacchiera.list.util.BackgroundColor;


public class LocationImpl implements Location{

    private BackgroundColor bgColor;
    private final int x;
    private final int y;
    private boolean free;
    private Piece piece;

    public LocationImpl(int x, int y) {
        this.x = x;
        this.y = y;
        piece = null;
        free = true;
    }
    public LocationImpl(int x, int y, BackgroundColor color) {
        this.x = x;
        this.y = y;
        bgColor = color;
        piece = null;
        free = true;
    }
    public LocationImpl(int x, int y, Piece piece) {
        this.x = x;
        this.y = y;
        this.setPiece(piece);
    }

    /**
     * @return row
     */
    @Override
    public int getX() {
        return this.x;
    }

    /**
     * @return column
     */
    @Override
    public int getY() {
        return this.y;
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
        return true;
    }


    /**
     * Get the piece inside this location
     * @return the piece value if non-null
     */
    @Override
    public Piece getPiece(){
        return piece;
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
                "x=" + x +
                ", y=" + y +
                ", free=" + free +
                ", piece=" + piece +
                '}';
    }

}
