package it.unicam.cs.pa.scacchiera.list;

public enum Colour {
    WHITE, BLACK;

    public Colour opponent(){
        return Colour.WHITE == this ? Colour.BLACK : Colour.WHITE;
    }
}
