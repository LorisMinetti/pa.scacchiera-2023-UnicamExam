package it.unicam.cs.pa.scacchiera.list;

import it.unicam.cs.pa.scacchiera.list.Checkers.Pawn;
import it.unicam.cs.pa.scacchiera.list.pieces.Piece;
import it.unicam.cs.pa.scacchiera.list.util.Colour;

import java.util.ArrayList;
import java.util.List;


public class CheckersFrame implements GameFrame<Piece, Location> {
    private static int frameNumber = 0;
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

    public GameFrame<Piece, Location> getFuture() {
        return future;
    }
    /**
     * Lista di tutte le posibili mosse che un pezzo, appartenente ad un giocatore specifico, può effettuare
     *
     * ATTENZIONE: se questa lista torna vuota la partita deve terminare !!
     * @param state
     * @param col
     * @param piece
     * @return Lista delle mosse effettuabili da un pezzo.
     */
    @Override
    public List<Move> allPieceMoves(GameFrame<Piece, Location> state, Colour col, Piece piece) {
        List<Move> moveList = new ArrayList<>();
        /*questa è una lista "speciale" ovvero la lista delle sole mosse di cattura, poichè se un pezzo sia re o pedina che sia,
        * si trova davanti a una cattura è obbligato a eseguirla. Non verranno dunque più prese in considerazione le altre.  */
        List<Move> captureList = new ArrayList<>();
        List<Location> diagonalSpots = getTheBoard().getDiagonalAdjacentLocationsOfPiece(piece);
        Pawn pawn = new Pawn(piece.getLocation(), piece.getColour());
        for(Location loc : diagonalSpots){
            if(loc.getPiece().isPresent()){   //controllo se c'è un pezzo in una delle caselle diagonali
                if(loc.getPiece().get().getColour() != pawn.getColour()     //controllo se il pezzo è dell'altro giocatore
                        && getTheBoard().getNextDiagonalSpot(pawn.getLocation(), loc).isFree()){    //e parallelamente se la cella diagonalmente successiva è libera
                        captureList.add(new Move(pawn.getLocation(), getTheBoard().getNextDiagonalSpot(pawn.getLocation(), loc), true));
                }
            } else { //non c'è nessun altro pezzo presente
                moveList.add(new Move(pawn.getLocation(), loc));
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

//    /**
//     * metodo che controlla se si tratta di una pedina o di un re.
//     * @param col
//     * @param moveList
//     * @param diagonalSpots
//     * @param pawn
//     */
//    private void pawnOrKing(Colour col, List<Move> moveList, List<Location> diagonalSpots, Pawn pawn) {
//        for (Location loc : diagonalSpots) {
//            /* Check nella cell destinataria c'è un pezzo avversario */
//            if (pawn.getColour() != col) {
//                if(pawn.isKing()) {     /* Caso in cui la pedina è un re. Può muovere ovunque senza restrizioni*/
//                    Location nextDiagonalSpot = getTheBoard().getNextDiagonalSpot(pawn.getLocation(), loc);
//                    moveList.add(new Move(pawn.getLocation(), nextDiagonalSpot));
//                }
//                else {   /* Caso in cui la pedina non è re*/
//                    unidriectionalPawnMove(moveList, loc, pawn);
//                }
//            }
//            /* Check se la casella destinataria è vuota ed è nella scacchiera */
//                moveList.add(new Move(pawn.getLocation(), loc));
//        }
//    }
//
//    /**
//     * Metodo che gestisce i movimenti delle pedine semplici di ogni giocatore nell'unica direzione possibile in base alla loro posizione di partenza.
//     * @param moveList
//     * @param loc
//     * @param pawn
//     */
//    private void unidriectionalPawnMove(List<Move> moveList, Location loc, Pawn pawn) {
//        int increment = (pawn.getColour() == Colour.BLACK) ? 1 : -1;         /* costante che incrementa o decrementa la riga in base al colore */
//        int eatIncrement = (pawn.getColour() == Colour.BLACK) ? 2 : -2;      /* costante di controllo per una cattura */
//        if(pawn.getColour() == Colour.BLACK){                                       /* Se è nera può solo andare verso il basso*/
//            boolean canMove = ((loc.getRow() - pawn.getLocation().getRow()) == increment )    /* Check se l'incremento verticale */
//                    || ((loc.getRow() - pawn.getLocation().getRow()) == eatIncrement );
//            if(canMove && getTheBoard().isInsideBoard(loc)){                    /* Se la distanza tra le due coordinate  */
//                moveList.add(new Move(pawn.getLocation(), loc));
//            }
//        } else if( pawn.getColour() == Colour.WHITE){   /* Se è bianca può solo andare verso l'alto */
//            boolean canMove = (loc.getRow() - pawn.getLocation().getRow()) == increment
//                    || ((loc.getRow() - pawn.getLocation().getRow()) == eatIncrement );
//            if(canMove && getTheBoard().isInsideBoard(loc)){
//                moveList.add(new Move(pawn.getLocation(), loc));
//            }
//        }
//    }

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
     * @param piece
     */
    public void kingify(Piece piece){
        //tutti i pezzi bianchi nella riga con indice 0 diventano dama
        //tutti i pezzi neri nella riga con indice 7 diventano dama
        Pawn pawn = new Pawn(piece.getLocation(), piece.getColour());
        for(int i = 0; i < theBoard.getROW_VALUE(); i++){
            if(piece.getColour() == Colour.WHITE && piece.getLocation().getRow() == 0){
                pawn.becomeKing();
            } else if(piece.getColour() == Colour.BLACK && piece.getLocation().getRow() == 7){
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
