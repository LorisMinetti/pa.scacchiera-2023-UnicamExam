package it.unicam.cs.pa.scacchiera.list;

import it.unicam.cs.pa.scacchiera.list.pieces.Piece;
import it.unicam.cs.pa.scacchiera.list.player.Player;

import java.util.LinkedList;
import java.util.List;

public class GameImpl implements Game{

    private Player player1, player2;
    private enum GameState { RUNNING, DRAW, PLAYER_1_WINS, PLAYER_2_WINS, ERROR}
    private GameState status;
    private Board<Piece, Location> board;
    private Colour turn;
    private GameFrame<Piece, Location> gameFrameCorrente;


    /* Quando creo un gioco lo faccio partire già da uno stato RUNNING */
    public GameImpl(Player player1, Player player2, Board<Piece, Location> board){
        status= GameState.RUNNING;
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
        for (Location location : board.allLocations()) {
            if (location.getPiece() != null && location.getPiece().getColour() == Colour.BLACK) {
                blackFound = true;
            } else if (location.getPiece() != null && location.getPiece().getColour() == Colour.WHITE) {
                whiteFound = true;
            }
        }
        return !whiteFound || !blackFound;
    }

    public void move(Move move) throws Exception {
        if(status != GameState.RUNNING){
            throw new Exception("Partita terminata. Impossibile effettuare alcuna mossa.");
        }
        //Prendi la pedina
        Piece pezzo = move.getStart().getPiece();
        //controllo che la pedina sia del giocatore corrente, a quel punto controllo la validità della mossa
        if(pezzo.getColour() == gameFrameCorrente.getActualTurn()){
            List<Move> possibleMoves = gameFrameCorrente.allPossibleMoves(gameFrameCorrente, gameFrameCorrente.getActualTurn());
            if( !possibleMoves.contains(move)){
                throw new Exception("Mossa non valida");
            }
        } else throw new Exception("Il pezzo che stai provando a muovere non appartiene al giocatore corrente.");
        //sposto la pedina
        if( board.apply(move) ){
            gameFrameCorrente.getTheBoard().allLocations().remove(pezzo);
        }


        /*devo fare tutti gli aggiornamenti sulle strutture dati
        * - Rimuovere il pezzo se qualcosa è stato mangiato.
        *
        * */

        //aggiorno lo stato del gioco
        this.updateStatus();
        //cambio turno
        turn = turn.opponent();
        //nuovo game frame
        GameFrame<Piece, Location> nuovoGameFrame = new GameFrameImpl(gameFrameCorrente, turn, board);
        gameFrameCorrente.setFuture(nuovoGameFrame);
        gameFrameCorrente = nuovoGameFrame;
    }

    @Override
    public void updateStatus(){
        if( ! this.isTerminal() ) {
            if( turn == Colour.WHITE ) {
                status = GameState.PLAYER_1_WINS;
            } else {
                status = GameState.PLAYER_2_WINS;
            }
        }
    }


}
