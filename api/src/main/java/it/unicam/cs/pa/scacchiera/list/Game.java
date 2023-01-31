package it.unicam.cs.pa.scacchiera.list;
import it.unicam.cs.pa.scacchiera.list.player.Player;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class Game implements Board, GameObserver {

    private DefaultSchema defaultSchema = new DefaultSchema();
    private static int turn;
    //Si considera ogni gioco fatto su scacchiera con una scacchiera 8x8ù
    private int boardSize;
    private Cell[][] cells;

    /*
    TODO: capire come implementare il concetto di Scacchiera qui, un Game infatti deve avere una scacchiera sul quale
        poter essere performato.
     */

    //In futuro in base alla scelta di gioco
    // 1 vs 1 - 1 vs Bot - Bot vs Bot  generare uno switch che assegni ad ogni player il giusto tipo,
    // HumanPlayer o BotPlayer
    private Player player1;
    private Player player2;



    public Game(int boardSize) {
        //Creo lo schema di default della scacchiera al turno 0
        this.boardSize = boardSize;
        this.cells = new Cell[boardSize][boardSize];
        this.turn = 0;
        buildStage();

    }


    //Getters
    public Player getPlayer1() {
        return player1;
    }
    public Player getPlayer2() {
        return player2;
    }
    public Cell[][] getCells() { return this.cells; }
    public int getBoardSize() { return this.boardSize; }
    public Schema getDefaultSchema() { return this.defaultSchema; }

    /**
     * Metodo che torna il giocatore attuale.
     * @return actual Player
     */
    public Player getCurrentPlayer(){
        return ( (turn % 2) == 0 ) ? player1 : player2;
    }

    /**
     * Metodo che passa il turno al giocatore avversario
     * @return the next player
     */
    public int switchPlayer() {
        return (turn + 1) % 2;
    }



    @Override
    public void update(Game game) {

        turn++;
        switchPlayer();

    }


    /**
     * Metodo che ritorna false se la cella considerata non rientra nelle coordinate della scacchiera
     * @param c cella da controllare
     * @return true if the cell is in the board; false otherwise
     */
    @Override
    public boolean isValid(Cell c) {
        return (c.getRow() < this.boardSize ) && (c.getColumn() < this.boardSize);
    }

//    @Override
//    public List<Cell> possibleMoves(Schema actaul, Piece pz, Player p) {
//
//    }



//    public void setPiece(Cell cell, Piece p) throws BoardGameException {
//        if(cell != null && p!= null){
//            if(isValid(cell)){
//                cell.
//            }
//        }
//    }

    public void buildStage() {
        for (int i = 0; i < this.boardSize; i++) {
            for (int j = 0; j < this.boardSize; j++) {
               cells[i][j] = new Cell(i, j);
            }
        }
    }

    public Schema buildCheckersStage(DefaultSchema schema) {
        List<Cell> finalStage = schema
                .buildStage(schema.getDefaultPlayer1(), schema.getDefaultPlayer2())
                .getCurrentSchema();
        Schema emptyCellsStage = new Schema();
        for (int i = 0; i < this.boardSize; i++) {
            for (int j = 0; j < this.boardSize; j++) {
                if (this.cells[i][j].getStatus().isEmpty()) {
                    emptyCellsStage.setSchema(
                            List.of(new Cell(i, j))
                    );
                }
            }
        }
        finalStage = Stream.concat( finalStage.stream(), emptyCellsStage.getCurrentSchema().stream() ).collect(Collectors.toList());
        Schema ret = new Schema();
        ret.setSchema(finalStage);
        return ret;
    }

    @Override
    public String toString() {
        String s = "";
        for(int i = 0; i < this.boardSize; i++){
            for(int j = 0; j < this.boardSize; j++){
                if(this.cells[i][j]!= null){
                    s += "[ " +this.cells[i][j].toString()+ " ]";
                }
            }
            s += "\n";
        }
        return s;
    }
}
