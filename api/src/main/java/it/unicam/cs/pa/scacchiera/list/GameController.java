package it.unicam.cs.pa.scacchiera.list;

import it.unicam.cs.pa.scacchiera.list.pieces.Piece;
import it.unicam.cs.pa.scacchiera.list.player.Player;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class GameController {

    private final Player[] players;
    private final Board gameBoard;
    private int turn;
    private HashMap<Player, List<Piece>> gameFrames;

    public GameController(Player[] players, Board gameBoard) {
        this.players = players;
        this.gameBoard = gameBoard;
        this.turn = 0;
    }

    public void initializeGame() {
    }

    public int currentTurn() {
        return this.turn;
    }
    private int changeTurn(){
        return ( turn+1 ) % 2;
    }

}
