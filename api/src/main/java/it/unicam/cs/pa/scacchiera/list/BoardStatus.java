package it.unicam.cs.pa.scacchiera.list;

import it.unicam.cs.pa.scacchiera.list.pieces.Colour;
import it.unicam.cs.pa.scacchiera.list.pieces.Piece;

import javax.swing.text.Position;
import java.util.HashMap;

import static java.awt.Color.black;

public class BoardStatus {
     private final int DEFAULT_VALUE_BOARD_HEIGHT,  DEFAULT_VALUE_BOARD_WIDTH;
     private BoardStatus previous, future;
     private Move lastMove;
     private Piece[][] spots;

     public BoardStatus(int height, int width){
         this.DEFAULT_VALUE_BOARD_HEIGHT = height;
         this.DEFAULT_VALUE_BOARD_WIDTH = width;
         spots = new Piece[DEFAULT_VALUE_BOARD_HEIGHT][DEFAULT_VALUE_BOARD_WIDTH];
     }

     public BoardStatus( int height, int width, String arrangement, Piece[] piece){
         this(height, width);

         HashMap<String, Piece> symbols = new HashMap<>();
         for(Piece p : piece){
             symbols.put(piece.toString(), p);
         }
         symbols.put("", null);
         int count = 0;
         for(int y = height-1; y >= 0; y--){
             for(int x = 0; x < width; x++){
                 String symbol = arrangement.charAt(count++)+"";
                 while(!symbols.containsKey(symbol)){
                     symbol = arrangement.charAt(count++)+"";
                 }
                 setPiece(new Location(x,y), symbols.get(symbol));
             }
         }
     }

     public boolean isValid(Move move) {
         for(int coordinate : new int[]{move.getX1(), move.getX2(), move.getY1(), move.getY2()}){
             if(coordinate < 0 || coordinate >= spots[0].length)
                 return false;
         }
         Piece piece = getPiece(move.getStart());
         if( piece != null && piece.getColour() == getPlayerTurn() && piece.isValid(this, move) ){
             return true;
         }
         return false;
     }

    public void makeMove(Move move) {
        getPiece(move.getStart()).makeMove(this, move);
        setLastMove(move);
    }

    public boolean isTerminal(){
         boolean whiteFound = false;
         boolean blackFound = false;
         for(Position pos : allPositions()){
             Piece piece = getPiece(pos);
             if(piece != null)
                 if(piece.getColour()== Colour.WHITE){
                     whiteFound = true;
                 } else blackFound = true;
         }
         return !whiteFound || !blackFound;
    }
}
