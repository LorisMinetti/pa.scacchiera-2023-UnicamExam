/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package it.unicam.cs.pa.scacchiera.app;

import it.unicam.cs.pa.scacchiera.list.Game;

public class App {
    public static void main(String[] args) {
        Game game = new Game(8);
        System.out.println(game);
        System.out.println(game.getBoardSize());
        System.out.println(game.getDefaultSchema());



    }


}
