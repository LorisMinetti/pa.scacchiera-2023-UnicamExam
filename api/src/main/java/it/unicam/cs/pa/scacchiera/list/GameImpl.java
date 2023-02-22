package it.unicam.cs.pa.scacchiera.list;

import java.util.LinkedList;

public class GameImpl implements Game<GameState>{

    private LinkedList<GameStateImpl> gameSnapshots;

//    /**
//     * Check sulla presenza di almeno un pezzo per uno dei due giocatori.
//     * Un giocatore senza pezzi, equivale al termine della partita con conseguente vittoria del giocatore avversario.
//     *
//     * @return true se uno dei due giocatori non ha più pezzi.
//     */
//    @Override
//    public boolean isTerminal() {
//        boolean whiteFound = false;
//        boolean blackFound = false;
//        for (Location location : allLocations()) {
//            if (location.getPiece() != null && location.getPiece().getColour() == Colour.DARK) {
//                blackFound = true;
//            } else if (location.getPiece() != null && location.getPiece().getColour() == Colour.WHITE) {
//                whiteFound = true;
//            }
//        }
//        return !whiteFound || !blackFound;    }
}
