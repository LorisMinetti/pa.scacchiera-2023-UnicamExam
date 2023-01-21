package it.unicam.cs.pa.scacchiera.list;

import it.unicam.cs.pa.scacchiera.list.player.HumanPlayer;
import it.unicam.cs.pa.scacchiera.list.player.Player;

public class Game {
    private GameState previous;
    private GameState current;
    private GameState next;
    private int turn;
    /*
    TODO: capire come implementare il concetto di Scacchiera qui, un Game infatti deve avere una scacchiera sul quale
        poter essere performato.
     */

    //In futuro in base alla scelta di gioco
    // 1 vs 1 - 1 vs Bot - Bot vs Bot  generare uno switch che assegni ad ogni player il giusto tipo,
    // HumanPlayer o BotPlayer
    private Player player1;
    private Player player2;

    private Cell [][] board = new Cell[8][8];

    public Game(Player player1, Player player2, Board board) {
        this.player1 = player1;
        this.player2 = player2;
        this.turn = 0;
        this.previous = null;
    }

}
