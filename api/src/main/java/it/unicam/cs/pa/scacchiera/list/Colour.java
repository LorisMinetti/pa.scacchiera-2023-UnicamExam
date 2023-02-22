package it.unicam.cs.pa.scacchiera.list;

public enum Colour {
    WHITE, DARK;

    public Colour opponent(){
        return Colour.WHITE == this ? Colour.DARK : Colour.WHITE;
    }
}
