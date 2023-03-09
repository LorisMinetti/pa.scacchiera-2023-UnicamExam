package it.unicam.cs.pa.scacchiera.list;

import it.unicam.cs.pa.scacchiera.list.Checkers.Pawn;
import it.unicam.cs.pa.scacchiera.list.pieces.Piece;
import it.unicam.cs.pa.scacchiera.list.util.Colour;

import java.util.ArrayList;
import java.util.List;


public class CheckersFrame implements GameFrame<Piece, Location> {
    private int frameNumber = 0;
    private GameFrame<Piece, Location> future, previous;
    private Colour actualTurn;
    private final List<Piece> whitePieces, blackPieces;
    private Board<Piece, Location> theBoard;

    public CheckersFrame(GameFrame<Piece, Location> prev, Colour turn, Board<Piece, Location> board) {
        actualTurn = turn;
        previous = prev;
        theBoard = board;
        whitePieces = fillPieceList(Colour.WHITE);
        blackPieces = fillPieceList(Colour.BLACK);
        frameNumber++;
        frameNumber = prev == null ? 1 : prev.getFrameNumber() + 1;

    }
    public CheckersFrame(GameFrame<Piece,Location> prev, Colour turn, Board<Piece, Location> board, int frameNumber, List<Piece> whitePieces, List<Piece> blackPieces){
        actualTurn = turn;
        previous = prev;
        theBoard = board;
        this.whitePieces = whitePieces;
        this.blackPieces = blackPieces;
        this.frameNumber = frameNumber;
    }

    /**
     * Crea una lista di pezzi di un certo colore
     *
     * @param colour
     * @return
     */
    public List<Piece> fillPieceList(Colour colour) {
        List<Piece> result = new ArrayList<>();
        for (Location loc : theBoard.allLocations()) {
            if (loc.getPiece().isPresent()
                    && loc.getPiece().orElse(null).getColour() == colour) {
                result.add(loc.getPiece().get());
            }
        }
        return result;
    }

    @Override
    public int getFrameNumber() {
        return frameNumber;
    }

    public GameFrame<Piece, Location> getFuture() {
        return future;
    }

    /**
     * Lista di tutte le posibili mosse che un pezzo, appartenente a un giocatore specifico, può effettuare
     * ATTENZIONE: se questa lista torna vuota la partita deve terminare !!
     * @param state
     * @param col
     * @param piece
     * @return Lista delle mosse effettuabili da un pezzo.
     */
    @Override
    public List<Move> allPieceMoves(GameFrame<Piece, Location> state, Colour col, Piece piece) {
        List<Move> moveList = new ArrayList<>();
        /* Questa è una lista "speciale" ovvero la lista delle sole mosse di cattura, poichè se un pezzo sia re o pedina che sia,
        * si trova davanti a una cattura è obbligato a eseguirla. Non verranno dunque più prese in considerazione le altre.  */
        List<Move> captureList = new ArrayList<>();
        List<Location> diagonalSpots = getTheBoard().getDiagonalAdjacentLocationsOfPiece(piece);
        for(Location loc : diagonalSpots){
            if(loc.getPiece().isPresent()){   //controllo se c'è un pezzo in una delle caselle diagonali
                if(loc.getPiece().get().getColour() != piece.getColour()     //controllo se il pezzo è dell'altro giocatore
                        && getTheBoard().getNextDiagonalSpot(piece.getLocation(), loc) != null){    //e parallelamente se la cella diagonalmente successiva esiste
                    if(getTheBoard().getNextDiagonalSpot(piece.getLocation(), loc).isFree()){     //e parallelamente se la cella diagonalmente successiva è libera
                        captureList.add(new Move(piece.getLocation(), getTheBoard().getNextDiagonalSpot(piece.getLocation(), loc), true));
                    }
                }
            } else { //non c'è nessun altro pezzo presente
                moveList.add(new Move(piece.getLocation(), loc));
            }
        }
        return switchList(moveList, captureList);
    }

    /**
     * Se la prima lista è vuota, restituisce la seconda
     * @param moveList lista
     * @param captureList lista alternativa
     * @return prima lista non vuota.
     */
    private static List<Move> switchList(List<Move> moveList, List<Move> captureList) {
        if(!captureList.isEmpty()){
            return captureList;
        } else
            return moveList;
    }

    /**
     * Tutte le possibili mosse per un giocatore durante un determinato momento della partita.
     * @param colour
     * @return lista delle mosse possibili.
     */
    @Override
    public List<Move> allPossibleMoves(Colour colour) {
        List<Move> possibleMoves = new ArrayList<>();
        for (Location loc : getTheBoard().getAllLocationsOfPlayer(colour)) {
            if(loc.getPiece().isPresent()){
                Piece pz = loc.getPiece().get();
                List<Move> movesOfPiece = allPieceMoves(this, colour, pz);
                possibleMoves.addAll(movesOfPiece);
            }
            /* Concateno tutte le mosse per ogni pezzo per ottenere tutte le mosse di uno specifico giocatore */
        }
        return possibleMoves;
    }

    /**
     * Metodo che restituisce la lista dei pezzi non bloccati.
     * @return Lista di pezzi non bloccati
     */
    @Override
    public List<Piece> unblockedPieces(){
        List<Piece> unblockedPieces = new ArrayList<>();
        List<Piece> cycle = getActualTurn() == Colour.WHITE ? whitePieces : blackPieces;

        for(Piece piece : cycle){
            if(allPieceMoves(this, getActualTurn(), piece).size() > 0){
                unblockedPieces.add(piece);
            } else {
                System.out.println("Il pezzo " + piece + " è bloccato");
            }
        }
        return unblockedPieces;
    }

    /**
     * Stampa a console i pezzi non bloccati.
     * @return pezzi non bloccati
     */
    @Override
    public String printUnblockedPieces(){
        StringBuilder sb = new StringBuilder();
        sb.append("Pezzi disponibili da muovere: ");
        for(Piece piece : unblockedPieces()){
            sb.append(piece).append(" - ");
        }
        return sb.toString();
    }

    /**
     * Stampa a console la scacchiera.
     *
     * @return scacchiera stampata
     */
    @Override
    public String printBoardFrame() {
        return this.toString();
    }

    @Override
    public String toString() {
        return "Frame n° " + frameNumber +
                "\n\n" + theBoard;
    }

    /**
     * Da semplice pedina a Dama.
     * @param pawn
     */
    public void kingify(Pawn pawn){
        //tutti i pezzi bianchi nella riga con indice 0 diventano dama
        //tutti i pezzi neri nella riga con indice 7 diventano dama
        for(int i = 0; i < theBoard.getROW_VALUE(); i++){
            if(pawn.getColour() == Colour.WHITE && pawn.getLocation().getRow() == 0){
                pawn.becomeKing();
            } else if(pawn.getColour() == Colour.BLACK && pawn.getLocation().getRow() == 7){
                pawn.becomeKing();
            }
        }
    }

    public void setFuture(GameFrame<Piece, Location> future) {
        this.future = future;
    }

    public GameFrame<Piece, Location> getPrevious() {
        return previous;
    }

    public void setPrevious(GameFrame<Piece, Location> previous) {
        this.previous = previous;
    }

    public Colour getActualTurn() {
        return actualTurn;
    }

    public void setActualTurn(Colour actualTurn) {
        this.actualTurn = actualTurn;
    }

    public List<Piece> getBlackPieces() {
        return blackPieces;
    }

    public List<Piece> getWhitePieces() {
        return whitePieces;
    }

    public Board<Piece, Location> getTheBoard() {
        return theBoard;
    }

    public void setTheBoard(Board<Piece, Location> theBoard) {
        this.theBoard = theBoard;
    }
}
