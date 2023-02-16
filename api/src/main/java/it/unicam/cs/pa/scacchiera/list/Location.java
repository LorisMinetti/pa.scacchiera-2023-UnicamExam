package it.unicam.cs.pa.scacchiera.list;

import it.unicam.cs.pa.scacchiera.list.pieces.Colour;
import it.unicam.cs.pa.scacchiera.list.pieces.Piece;

import java.util.Optional;

public class Location implements ILocation{

    private final int x;
    private final int y;
    private LocationStatus status;
    private Colour colour;
    private Piece piece;

    public Location(int x, int y, Colour colour){
        this.x = x;
        this.y = y;
        this.status = LocationStatus.FREE;
        this.colour = colour;
    }

    public Location(int x, int y, LocationStatus status, Colour colour){
        this.x = x;
        this.y = y;
        this.status = status;
        this.colour = colour;
    }

    public Location(int x, int y, LocationStatus status, Colour colour, Piece piece){
        this.x = x;
        this.y = y;
        this.status = status;
        this.colour = colour;
        this.piece = piece;
    }

    public Location(int x, int y){
        this.x = x;
        this.y = y;
        this.status = LocationStatus.FREE;
    }



    /**
     * @return lo stato della cella presa in consiederazione.
     */
    @Override
    public LocationStatus getStatus() {
        return this.status;
    }

    public void setStatus(LocationStatus status){
        this.status = status;
    }

    /**
     * @return la riga della cella
     */
    @Override
    public int getX() {
        return this.x;
    }

    /**
     * @return la colonna della cella
     */
    @Override
    public int getY() {
        return this.y;
    }

    /**
     * @return il colore della cella. Bianco o nero.
     */
    @Override
    public Colour getColour() {
        return this.colour;
    }

    /**
     * @return Il pezzo, o nessun pezzo, in una locazione
     */
    @Override
    public Optional<Piece> getPiece() {
        return this.status==LocationStatus.FREE ? Optional.empty() : Optional.of(this.piece);
    }

    public void setPiece(Piece piece) {
        if(piece != null){
            this.piece = piece;
        }
    }

    @Override
    public String toString() {
        return "[" +x+", "+y+" ] ";
    }

}
