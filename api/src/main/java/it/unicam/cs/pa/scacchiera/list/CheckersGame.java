package it.unicam.cs.pa.scacchiera.list;

import it.unicam.cs.pa.scacchiera.list.Checkers.Pawn;
import it.unicam.cs.pa.scacchiera.list.pieces.Piece;
import it.unicam.cs.pa.scacchiera.list.player.Player;
import it.unicam.cs.pa.scacchiera.list.util.Colour;
import it.unicam.cs.pa.scacchiera.list.util.GameState;
import it.unicam.cs.pa.scacchiera.list.util.MoveResult;

import java.util.List;
import java.util.Objects;

public class CheckersGame implements Game{

    private Player player1, player2;
    private GameState status;
    private Board<Piece, Location> board;
    private Colour turn;
    private GameFrame<Piece, Location> gameFrameCorrente;


    /* Quando creo un gioco lo faccio partire già da uno stato RUNNING */
    public CheckersGame(Player player1, Player player2, Board<Piece, Location> board, GameFrame<Piece, Location> frame){
        status= GameState.RUNNING;
        this.board = board;
        this.player1 = player1;
        this.player2 = player2;
        turn = Colour.WHITE;
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
        for(int i=0; i< board.getROW_VALUE(); i++){
            for(int j=0; j< board.getCOLUMN_VALUE(); j++){
                Location location = board.getSchema()[i][j];
                if (location.getPiece().isPresent()
                        && location.getPiece().orElse(null).getColour() == Colour.BLACK) {
                    blackFound = true;
                } else if (location.getPiece().isPresent()
                        && location.getPiece().orElse(null).getColour() == Colour.WHITE) {
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

            }
            System.out.println("il pezzo da mangiare lo vedo"); //togliere da qui ... non sempre la lista delle mosse contiene la mossa mangiante
            if(possibleMoves.contains(move)){
                if(board.apply(move)) {
                    //mossa mangiante
                    if(gameFrameCorrente.getTheBoard().getIntermediateLocation(move.getStart(), move.getDestination()).getPiece().isPresent()
                        &&  move.isCapture()){

                        if( gameFrameCorrente.getTheBoard().getIntermediateLocation(move.getStart(), move.getDestination()).isFree() ){ //prova
                            System.out.println("mi dice che qui in mezzo non c'è nulla");
                        }

                        Piece captured = gameFrameCorrente.getTheBoard().getIntermediateLocation(move.getStart(), move.getDestination()).getPiece().get();
                        capturePieceWithPiece(pezzo, captured);

                    }
                    if(pezzo.getColour() == Colour.WHITE && move.getDestination().getRow() == 0){
                        pezzo.becomeKing();
                    } else if(pezzo.getColour() == Colour.BLACK && move.getDestination().getRow() == 7){
                        pezzo.becomeKing();
                    }
                }
            }

        } else return MoveResult.START_LOCATION_OTHER_PLAYER;
        //sposto la pedina


        //aggiorno lo stato del gioco
        this.updateStatus();
        //cambio turno
        this.turn = turn.opponent();
        this.gameFrameCorrente.setActualTurn(turn);
        //nuovo game frame
        GameFrame<Piece, Location> nuovoGameFrame = new CheckersFrame(gameFrameCorrente, turn, board);
        gameFrameCorrente.setFuture(nuovoGameFrame);
        gameFrameCorrente = nuovoGameFrame;
        return MoveResult.OK;
    }

    private void capturePieceWithPiece(Piece pezzo, Piece captured) throws Exception {
        //lo rimuovo dalla lista dei pezzi del giocatore avversario
        if(captured.getColour() == Colour.BLACK && pezzo.getColour() == Colour.WHITE){
            gameFrameCorrente.getBlackPieces().remove(captured);
        } else if(captured.getColour() == Colour.WHITE && pezzo.getColour() == Colour.BLACK) {
            gameFrameCorrente.getWhitePieces().remove(captured);
        }
        //e lo rimuovo dalla board
        this.getGameFrame().getTheBoard().getSchema()[captured.getLocation().getRow()][captured.getLocation().getColumn()].setPiece(null);
    }

    @Override
    public void updateStatus(){
        if( this.isTerminal() ) {
            if( turn == Colour.WHITE ) {
                status = GameState.PLAYER_1_WINS;
            } else {
                status = GameState.PLAYER_2_WINS;
            }
        } else if (gameFrameCorrente.allPossibleMoves(turn.opponent()).isEmpty()){
            if( turn == Colour.WHITE ) {
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
     * @return
     */
    @Override
    public GameFrame<Piece, Location> getGameFrame() {
        return this.gameFrameCorrente;
    }

    public void setTurn(Colour turn) {
        this.turn = turn;
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
