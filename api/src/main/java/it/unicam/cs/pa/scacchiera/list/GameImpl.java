package it.unicam.cs.pa.scacchiera.list;

import it.unicam.cs.pa.scacchiera.list.player.Player;

import java.util.LinkedList;

public class GameImpl implements Game{

    private Player player1;
    private Player player2;
    private enum GameState { RUNNING, DRAW, PLAYER_1_WINS, PLAYER_2_WINS, ERROR}
    private BoardImpl board;
    private LinkedList<GameFrameImpl> gameFrames;

    public GameImpl(Player player1, Player player2, BoardImpl board){

    }


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
