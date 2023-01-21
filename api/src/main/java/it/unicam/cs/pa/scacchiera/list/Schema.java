package it.unicam.cs.pa.scacchiera.list;

import java.util.List;

/**
 * Questa classe ha la responsabilità di rappresentare un oggetto capace di poter
 * tenere traccia di ogni pezzo e della sua posizione in un momento specifico del gioco, ed anche del momento precedente.
 */
public class Schema {
    private List<Piece> currentSchema;


    public Schema(List<Piece> currentSchema){
        this.currentSchema = currentSchema;
    }

    public List<Piece> getCurrentSchema() {
        return this.currentSchema;
    }

}
