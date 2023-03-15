package it.unicam.cs.pa.scacchiera.list.Checkers;

import it.unicam.cs.pa.scacchiera.list.*;
import it.unicam.cs.pa.scacchiera.list.Piece;
import it.unicam.cs.pa.scacchiera.list.player.Player;
import it.unicam.cs.pa.scacchiera.list.util.Colour;
import it.unicam.cs.pa.scacchiera.list.util.GameState;
import it.unicam.cs.pa.scacchiera.list.util.MoveResult;

import java.util.List;
import java.util.Objects;

import static it.unicam.cs.pa.scacchiera.list.util.Colour.BLACK;
import static it.unicam.cs.pa.scacchiera.list.util.Colour.WHITE;

/**
 * Questa classe identifica un gioco generico di Dama italiana.
 * @author Loris Minetti
 */
public class CheckersGame implements Game {

    private final Player player1, player2;
    private GameState status;
    private final Board<Piece, Location> board;
    private Colour turn;
    private GameFrame<Piece, Location> gameFrameCorrente;


    /* Quando creo un gioco lo faccio partire già da uno stato RUNNING */
    public CheckersGame(Player player1, Player player2, Board<Piece, Location> board, GameFrame<Piece, Location> frame){
        status= GameState.RUNNING;
        this.board = board;
        this.player1 = player1;
        this.player2 = player2;
        turn = WHITE;
        gameFrameCorrente = frame;

    }


    /**
     * Check sulla presenza di almeno un pezzo per uno dei due giocatori.
     * Un giocatore senza pezzi, equivale al termine della partita con conseguente vittoria del giocatore avversario.
     * @return true se uno dei due giocatori non ha più pezzi.
     */
    @Override
    public boolean isTerminal() {
        boolean whiteFound = false;
        boolean blackFound = false;

        if(gameFrameCorrente.allPossibleMoves(turn.opponent()).isEmpty()){
            return true;
        }

        for(int i=0; i< board.getROW_VALUE(); i++){
            for(int j=0; j< board.getCOLUMN_VALUE(); j++){
                Location location = board.getSchema()[i][j];
                if (location.getPiece().isPresent()
                        && location.getPiece().orElse(null).getColour() == BLACK) {
                    blackFound = true;
                } else if (location.getPiece().isPresent()
                        && location.getPiece().orElse(null).getColour() == WHITE) {
                    whiteFound = true;
                }
            }
        }
        return !whiteFound || !blackFound;
    }

    /**
     * Metodo che eseguirà una mossa.
     * @param move mossa da eseguire
     * @throws Exception se la mossa non è valida
     */
    @Override
    public MoveResult move(Move move) throws Exception {
        if(status != GameState.RUNNING){
            System.out.println("Partita terminata. Impossibile effettuare alcuna mossa.");
        }
        //Se la mossa sarà null equivale a dire che il giocatore attuale non avrà più alcuna mossa da effettuare.
        if(move == null){
            this.updateStatus();
        }
        else {
            Pawn pezzo = null;
            if(move.getStart().getPiece().isPresent()){
                pezzo = (Pawn) move.getStart().getPiece().get();
            }
            if(pezzo == null){
                return MoveResult.START_LOCATION_EMPTY;
            }

            //controllo che la pedina sia del giocatore corrente, a quel punto controllo la validità della mossa
            if(pezzo.getColour() == gameFrameCorrente.getActualTurn()){
                List<Move> possibleMoves = gameFrameCorrente.allPieceMoves(gameFrameCorrente, gameFrameCorrente.getActualTurn(), pezzo);
                //controllo se la mossa che voglio effettuare è contenuta tra le mosse possibili del pezzo
                if( !possibleMoves.contains(move)){
                    return MoveResult.MOVE_NOT_VALID;
                } else {
                    if(board.apply(move)) {
                        //mossa mangiante
                        if(gameFrameCorrente.getTheBoard().getIntermediateLocation(move.getStart(), move.getDestination()).getPiece().isPresent()
                                &&  move.isCapture()){

                            Piece captured = gameFrameCorrente.getTheBoard().getIntermediateLocation(move.getStart(), move.getDestination()).getPiece().get();
                            capturePieceWithPiece(pezzo, captured);

                        }
                    }
                    //rende una pedina re se arriva nella base avversaria
                    kingify(move, pezzo);
                    this.updateStatus();
                }

            } else return MoveResult.START_LOCATION_OTHER_PLAYER;

            //aggiorno lo stato del gioco
            threeCardGame();
        }
        return MoveResult.OK;
    }

    private void threeCardGame() {
        //in questo modo setto il gameFrame precedente solo dal secondo in poi

        this.turn = turn.opponent();
        this.gameFrameCorrente.setActualTurn(turn);
        //nuovo game frame
        GameFrame<Piece, Location> nuovoGameFrame = new CheckersFrame(gameFrameCorrente, turn, board);
        gameFrameCorrente.setFuture(nuovoGameFrame);
        gameFrameCorrente = nuovoGameFrame;
    }

    /**
     * Ritorna lo stato del gioco
     * @return status
     */
    @Override
    public GameState getStatus() {
        return this.status;
    }


    /**
     * Rende re una pedina che arriva nella base avversaria.
     * @param move che rende re una pedina
     * @param pezzo che diventa re
     */
    private static void kingify(Move move, Pawn pezzo) {
        if(pezzo.getColour() == WHITE && move.getDestination().getRow() == 0){
            pezzo.becomeKing();
        } else if(pezzo.getColour() == BLACK && move.getDestination().getRow() == 7){
            pezzo.becomeKing();
        }
    }


    /**
     * Cattura un pezzo con un altro. Rimuove il pezzo dalla lista dei pezzi del giocatore avversario e dalla board.
     * @param pezzo che cattura
     * @param captured pezzo che viene catturato
     */
    private void capturePieceWithPiece(Piece pezzo, Piece captured){
        //lo rimuovo dalla lista dei pezzi del giocatore avversario
        if(captured.getColour() == BLACK && pezzo.getColour() == WHITE){
            gameFrameCorrente.getBlackPieces().remove(captured);
        } else if(captured.getColour() == WHITE && pezzo.getColour() == BLACK) {
            gameFrameCorrente.getWhitePieces().remove(captured);
        }
        //e lo rimuovo dalla board
        this.getGameFrame().getTheBoard().getSchema()[captured.getLocation().getRow()][captured.getLocation().getColumn()].setPiece(null);
    }


    /**
     * Metodo che aggiorna lo stato del gioco.
     */
    @Override
    public void updateStatus(){
        if( this.isTerminal() ) {
            if( turn == WHITE ) {
                status = GameState.PLAYER_1_WINS;
            } else {
                status = GameState.PLAYER_2_WINS;
            }
        } else if (gameFrameCorrente.allPossibleMoves(turn.opponent()).isEmpty()){
            if( turn == WHITE ) {
                status = GameState.PLAYER_1_WINS;
            } else {
                status = GameState.PLAYER_2_WINS;
            }
        } else {
            status = GameState.RUNNING;
        }
    }


    /**
     * Frazione di gioco corrente.
     * @return gameframe
     */
    @Override
    public GameFrame<Piece, Location> getGameFrame() {
        return this.gameFrameCorrente;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CheckersGame)) return false;
        CheckersGame that = (CheckersGame) o;
        return Objects.equals(player1, that.player1) && Objects.equals(player2, that.player2) && status == that.status && Objects.equals(board, that.board) && turn == that.turn && Objects.equals(gameFrameCorrente, that.gameFrameCorrente);
    }


    @Override
    public int hashCode() {
        return Objects.hash(player1, player2, status, board, turn, gameFrameCorrente);
    }
}
