/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package it.unicam.cs.pa.scacchiera.app;


import it.unicam.cs.pa.scacchiera.list.Board;
import it.unicam.cs.pa.scacchiera.list.BoardImpl;

public class App {
    public static void main(String[] args) throws Exception {
        Board b = new BoardImpl(8,8);
        System.out.println("000-------------------------000");
        System.out.println(b);



    }


}
