package it.unicam.cs.pa.scacchiera.list;

import it.unicam.cs.pa.scacchiera.list.pieces.CheckersPawn;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DefaultSchema extends Schema implements ISchema {

    private static List<Cell> DEFAULT_PIECES_PLAYER1 =
            new ArrayList<>(List.of(
                    new Cell(0, 0, CellStatus.OCCUPIED, new CheckersPawn(0)),
                    new Cell(0, 2, CellStatus.OCCUPIED, new CheckersPawn(0)),
                    new Cell(0, 4, CellStatus.OCCUPIED, new CheckersPawn(0)),
                    new Cell(0, 6, CellStatus.OCCUPIED, new CheckersPawn(0)),
                    new Cell(1, 1, CellStatus.OCCUPIED, new CheckersPawn(0)),
                    new Cell(1, 3, CellStatus.OCCUPIED, new CheckersPawn(0)),
                    new Cell(1, 5, CellStatus.OCCUPIED, new CheckersPawn(0)),
                    new Cell(1, 7, CellStatus.OCCUPIED, new CheckersPawn(0)),
                    new Cell(2, 0, CellStatus.OCCUPIED, new CheckersPawn(0)),
                    new Cell(2, 2, CellStatus.OCCUPIED, new CheckersPawn(0)),
                    new Cell(2, 4, CellStatus.OCCUPIED, new CheckersPawn(0)),
                    new Cell(2, 6, CellStatus.OCCUPIED, new CheckersPawn(0))
            )
            );
    private static List<Cell> DEFAULT_PIECES_PLAYER2 = new ArrayList<>(List.of(getCellsOfPlayer2()));

    private static Cell[] getCellsOfPlayer2() {
        return new Cell[]{new Cell(7, 1, CellStatus.OCCUPIED, new CheckersPawn(1)),
                new Cell(7, 3, CellStatus.OCCUPIED, new CheckersPawn(1)),
                new Cell(7, 5, CellStatus.OCCUPIED, new CheckersPawn(1)),
                new Cell(7, 7, CellStatus.OCCUPIED, new CheckersPawn(1)),
                new Cell(6, 0, CellStatus.OCCUPIED, new CheckersPawn(1)),
                new Cell(6, 2, CellStatus.OCCUPIED, new CheckersPawn(1)),
                new Cell(6, 4, CellStatus.OCCUPIED, new CheckersPawn(1)),
                new Cell(6, 6, CellStatus.OCCUPIED, new CheckersPawn(1)),
                new Cell(5, 1, CellStatus.OCCUPIED, new CheckersPawn(1)),
                new Cell(5, 3, CellStatus.OCCUPIED, new CheckersPawn(1)),
                new Cell(5, 5, CellStatus.OCCUPIED, new CheckersPawn(1)),
                new Cell(5, 7, CellStatus.OCCUPIED, new CheckersPawn(1))};
    }

    private Schema player1 = new Schema(DEFAULT_PIECES_PLAYER1);
    private Schema player2 = new Schema(DEFAULT_PIECES_PLAYER2);

    public DefaultSchema() {
        super();
        buildStage(player1, player2);
    }


    /**
     * Metodo che genera un'unica lista di pezzi, unendo la lista del giocatore 1 e giocatore 2.
     *
     * @param first  schema del giocatore 1
     * @param second schema del giocatore 2
     * @return Schema dei pezzi del gioco
     */

    @Override
    public Schema buildStage(Schema first, Schema second) {
        List<Cell> allStartingCells = new ArrayList<>();

        try {
            if (first != null || second != null) {
                allStartingCells = Stream.concat(first.getCurrentSchema().stream(), second.getCurrentSchema().stream())
                        .collect(Collectors.toList());
            }
        } catch (NullPointerException e) {     //metodo getCurrentSchema() potrebbe lanciare una NullPointerException se non ci fosse uno schema istanziato a dovere.
            e.getMessage();
        }
        return new Schema(allStartingCells);
    }

    public Schema getDefaultPlayer1() {
        return player1;
    }

    public Schema getDefaultPlayer2() {
        return player2;
    }

    @Override
    public String toString() {
        return player1.toString() + "\n" + player2.toString();
    }

}
