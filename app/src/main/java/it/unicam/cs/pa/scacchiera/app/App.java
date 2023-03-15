/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package it.unicam.cs.pa.scacchiera.app;


import it.unicam.cs.pa.scacchiera.list.*;
import it.unicam.cs.pa.scacchiera.list.Checkers.CheckersBoard;
import it.unicam.cs.pa.scacchiera.list.Checkers.CheckersFrame;
import it.unicam.cs.pa.scacchiera.list.Checkers.CheckersGame;
import it.unicam.cs.pa.scacchiera.list.Checkers.Pawn;
import it.unicam.cs.pa.scacchiera.list.Piece;
import it.unicam.cs.pa.scacchiera.list.player.ComputerPlayer;
import it.unicam.cs.pa.scacchiera.list.player.Player;
import it.unicam.cs.pa.scacchiera.list.util.Colour;
import it.unicam.cs.pa.scacchiera.list.util.GameState;
import it.unicam.cs.pa.scacchiera.list.util.MoveResult;

import java.util.Scanner;

import static it.unicam.cs.pa.scacchiera.list.util.Colour.WHITE;

public class App {
    static final int ROW = 8;
    static final int COLUMN = 8;

    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        boolean playAgain = true;
        System.out.println("\n\nDAMA ITALIANA -\nBuona fortuna!\n\n");

        /*
        Partita completa
         */
        while (playAgain) {
            Board<Piece, Location> board = new CheckersBoard(ROW, COLUMN);
            Player player1 = new Player("Player 1", Colour.WHITE);
            Player computer = new ComputerPlayer("Computer", Colour.BLACK);
            GameFrame<Piece, Location> first = new CheckersFrame(null, WHITE, board);
            Game game = new CheckersGame(player1, computer, board, first);

            game.getGameFrame().printBoardFrame();
            Scanner sc = new Scanner(System.in);

            //Inizio la partita, fino al suo termine
            gaming(computer, game, sc);

            if (game.getStatus() == GameState.PLAYER_1_WINS) {
                System.out.println("Vittoria! Complimenti giocatore 1");
            } else if (game.getStatus() == GameState.PLAYER_2_WINS) {
                System.out.println("Vittoria! Complimenti giocatore 2");
            } else if (game.getStatus() == GameState.DRAW) {
                System.out.println("Pareggio!");
            }

            if (scanner.nextLine().equals("no") || scanner.nextLine().equals("NO") || scanner.nextLine().equals("SI") || scanner.nextLine().equals("si")) {
                if(scanner.nextLine().equals("no") || scanner.nextLine().equals("NO")) {
                    playAgain = false;
                }
            } else {
                System.out.println("Risposta non valida. La partita termina.");
                playAgain = false;
            }
        }


    }

    /**
     * Costruzione di ogni gameFrame della partita. Mossa del giocatore, mossa del computer. Fino al terminare della partita.
     * @param computer computer player
     * @param game game
     * @param sc scanner
     * @throws Exception exc
     */
    private static void gaming(Player computer, Game game, Scanner sc) throws Exception {
        while (!game.isTerminal()) {
            System.out.print("Turno di " + game.getGameFrame().getActualTurn() + "  |  ");
            System.out.println("----------------------");
            System.out.println(game.getGameFrame().printBoardFrame());
            System.out.println("----------------------");

            if (game.getGameFrame().getActualTurn() == WHITE) {
                requestMoveToUser(sc, game, game.getGameFrame());
            } else {
                Move computerMove = computer.play(game.getGameFrame());
                game.move(computerMove);
            }
        }
    }

    /**
     * Presa in carico della mossa dopo aver inserito le due locazioni. Restituzione a console di un risultato causato dalla mossa.
     * @param sc scanner
     * @param game game
     * @param frame gameframe
     * @throws Exception exc
     */
    private static void requestMoveToUser(Scanner sc, Game game, GameFrame<Piece, Location> frame) throws
            Exception {
        Location startLocation = askForLocation(sc, frame, "Inserisci la posizione di partenza");
        Location endLocation = askForLocation(sc, frame, "Inserisci la posizione di arrivo");
        MoveResult result = game.move(new Move(startLocation, endLocation));

        switch (result) {
            case OK -> System.out.println("Mossa effettuata con successo");
            case START_LOCATION_EMPTY -> System.out.println("Nessuna pedina nella posizione di partenza");
            case START_LOCATION_OTHER_PLAYER -> System.out.println("il pezzo che stai cercando di muovere non è tuo");
            case MOVE_NOT_VALID -> System.out.println("Mossa non valida");
        }
    }


    /**
     * Richiesta del valore numerico collegato alla casella della forma 'x y'
     * @param sc scanner
     * @param frame gameframe
     * @param message messaggio di partenza
     * @return locazione selezionata
     */
    public static Location askForLocation(Scanner sc, GameFrame<Piece, Location> frame, String message) {
        while (true) {
            System.out.println(message);
            String input = sc.nextLine();
            // l'input è del tipo "x y"
            String[] parts = input.split(" ");

            if (parts.length != 2) {
                System.out.println("Input non valido, scrivi la posizione nel formato 'x y'");
                continue;
            }
            int x, y;
            try {
                x = Integer.parseInt(parts[0]) - 1;
                y = Integer.parseInt(parts[1]) - 1;
            } catch (NumberFormatException e) {
                System.out.println("Input non valido, scrivi la posizione nel formato 'x y'");
                continue;
            }

            if (isOneToEight(x, y)) continue;
            //pezzo che voglio muovere
            Piece pawn = new Pawn(frame.getTheBoard().getSchema()[x][y], frame.getActualTurn());
            Colour colour = pawn.getColour();
            //il pezzo che voglio muovere deve essere del colore del giocatore che sta giocando
            if (colour == frame.getActualTurn()) {
                return frame.getTheBoard().getSchema()[x][y];
            } else {
                System.out.println("Non puoi muovere questo pezzo");
            }

        }
    }

    /**
     * Configurazione "umana" degli input di scacchiera.
     * Controllo sulla numerazione delle caselle da 1 a 8, invece che da 0 a 7
     * @param x righe
     * @param y colonne
     * @return true
     */
    private static boolean isOneToEight(int x, int y) {

        if (x < 0 || x >= ROW) {
            System.out.println("Valore di x non valido");
            return true;
        }
        if (y < 0 || y >= COLUMN) {
            System.out.println("Valore di y non valido");
            return true;
        }
        return false;
    }
}

