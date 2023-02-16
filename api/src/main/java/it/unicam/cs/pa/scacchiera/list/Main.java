package it.unicam.cs.pa.scacchiera.list;

import it.unicam.cs.pa.scacchiera.list.player.HumanPlayer;
import it.unicam.cs.pa.scacchiera.list.player.Player;

public class Main {
    public static void main(String[] args) throws Exception {
        Board board = new Board(8,8);
        Player player1 = new HumanPlayer("jack");
        Player player2 = new HumanPlayer("Bog");
        Player[] players = new Player[4];
        players[0] = player1;
        players[1] = player2;
        Game game = new Game(players, board);

        System.out.println("Ciao");
        game.toString();
        System.out.println("Ciao");
    }
}
