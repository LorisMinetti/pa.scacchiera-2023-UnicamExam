package it.unicam.cs.pa.scacchiera.list;

import it.unicam.cs.pa.scacchiera.list.pieces.Piece;
import it.unicam.cs.pa.scacchiera.list.player.Player;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Game {

    private final Player[] players;
    private final Board gameBoard;
    private int turn;
    private HashMap<Player, List<Piece>> gameFrames;

    public Game(Player[] players, Board gameBoard) {
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


    @Override
    public String toString() {
        return "Game{" +
                "players=" + Arrays.toString(players) +
                ", gameBoard=" + gameBoard +
                ", turn=" + turn +
                ", gameFrames=" + gameFrames +
                '}';
    }

}
