package it.unicam.cs.pa.scacchiera.list;

import it.unicam.cs.pa.scacchiera.list.pieces.Piece;
import it.unicam.cs.pa.scacchiera.list.player.Player;
import it.unicam.cs.pa.scacchiera.list.util.Colour;
import it.unicam.cs.pa.scacchiera.list.util.GameState;

import java.util.List;

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
    public void move(Move move) throws Exception {
        if(status != GameState.RUNNING){
            System.out.println("Partita terminata. Impossibile effettuare alcuna mossa.");
        }
        //Prendi la pedina
        Piece pezzo = move.getStart().getPiece();
        //controllo che la pedina sia del giocatore corrente, a quel punto controllo la validità della mossa
        if(pezzo.getColour() == gameFrameCorrente.getActualTurn()){
            List<Move> possibleMoves = gameFrameCorrente.allPossibleMoves(gameFrameCorrente, gameFrameCorrente.getActualTurn());
            if( !possibleMoves.contains(move)){
                System.out.println("Mossa non valida");
            }
        } else System.out.println("Il pezzo che stai provando a muovere non appartiene al giocatore corrente.");
        //sposto la pedina
        if( board.apply(move) ){
            Piece captured = gameFrameCorrente.getTheBoard().getIntermediateLocation(move.getStart(), move.getDestination()).getPiece();
            capturePieceWithPiece(pezzo, captured);
            gameFrameCorrente.kingify(pezzo);
        }

        //aggiorno lo stato del gioco
        this.updateStatus();
        //cambio turno
        this.turn = turn.opponent();
        //nuovo game frame
        GameFrame<Piece, Location> nuovoGameFrame = new CheckersFrame(gameFrameCorrente, turn, board);
        gameFrameCorrente.setFuture(nuovoGameFrame);
        gameFrameCorrente = nuovoGameFrame;
    }

    private void capturePieceWithPiece(Piece pezzo, Piece captured) {
        if(captured.getColour() == Colour.BLACK && pezzo.getColour() == Colour.WHITE){
            gameFrameCorrente.getBlackPieces().remove(captured);
        } else if(captured.getColour() == Colour.WHITE && pezzo.getColour() == Colour.BLACK) {
            gameFrameCorrente.getWhitePieces().remove(captured);
        }
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

    /**
     * Frazione di gioco corrente.
     * @return
     */
    @Override
    public GameFrame<Piece, Location> getGameFrame() {
        return this.gameFrameCorrente;
    }


}
