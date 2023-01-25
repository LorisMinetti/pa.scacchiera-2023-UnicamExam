package it.unicam.cs.pa.scacchiera.list;

import it.unicam.cs.pa.scacchiera.list.Pieces.Piece;

import java.util.List;
import java.util.Optional;

/**
 * Questa classe ha la responsabilità di rappresentare un oggetto capace di poter
 * tenere traccia di ogni pezzo e della sua posizione in un momento specifico del gioco, ed anche del momento precedente.
 */
public class Schema{
    /**
     * Lista contenente un numero di pezzi sulla scacchiera.
     */
    private List<Piece> currentSchema;

    public Schema(){}

    public Schema(List<Piece> currentSchema){
        this.currentSchema = currentSchema;
    }

    public List<Piece> getCurrentSchema() {
        return this.currentSchema;
    }

    public Piece getPiece(int x, int y){
        return currentSchema.stream()
                .distinct()
                .filter(p -> p.getCellLocation().getRow() == x && p.getCellLocation().getColumn() == y).findAny()
                .orElse(null);
    }

    public Schema setSchema(List<Piece> newSchema){
        this.currentSchema = newSchema;
        return this;
    }

    @Override
    public String toString() {
        String result = "";
        for(Piece p : currentSchema){
            result += "[ "+ p.getCellLocation().getRow() + ","+ p.getCellLocation().getColumn() +" ]";
        }
        result += "\n";
        return result;
    }

}
