package it.unicam.cs.pa.scacchiera.list.pieces;

public enum Colour {
    BLACK, WHITE;
    public Colour opponent() {
        return this == BLACK ? WHITE : BLACK;
    }
}
