package it.unicam.cs.pa.scacchiera.list;

import it.unicam.cs.pa.scacchiera.list.util.BackgroundColor;
import it.unicam.cs.pa.scacchiera.list.util.Colour;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;

import java.util.List;

public class CheckersBoardTest {

    private CheckersBoard board;

    @BeforeEach
    public void setUp() throws Exception {
        board = new CheckersBoard(8, 8);
    }

    @Test
    public void testIsInsideBoardWithValidLocation() {
        Location location = new LocationImpl(3, 5, BackgroundColor.DARK);
        Assertions.assertTrue(board.isInsideBoard(location));
    }

    @Test
    public void testIsInsideBoardWithInvalidLocation() {
        Location location = new LocationImpl(10, 5, BackgroundColor.DARK);
        Assertions.assertFalse(board.isInsideBoard(location));
    }

    @Test
    public void testAllLocations() {
        List<Location> allLocations = board.allLocations();
        Assertions.assertEquals(64, allLocations.size());
    }

    @Test
    public void testGetAllLocationsOfPlayer() {
        List<Location> blackLocations = board.getAllLocationsOfPlayer(Colour.BLACK);
        List<Location> whiteLocations = board.getAllLocationsOfPlayer(Colour.WHITE);
        Assertions.assertEquals(12, blackLocations.size());
        Assertions.assertEquals(12, whiteLocations.size());
    }

    @Test
    public void testGetIntermediateLocation() {
        Location loc1 = new LocationImpl(2, 2, BackgroundColor.DARK);
        Location loc2 = new LocationImpl(4, 4, BackgroundColor.DARK);
        Location intermediateLocation = board.getIntermediateLocation(loc1, loc2);
        Assertions.assertEquals(3, intermediateLocation.getRow());
        Assertions.assertEquals(3, intermediateLocation.getColumn());
    }

    @Test
    public void testIsDiagonal() {
        Location loc = new LocationImpl(2, 2, BackgroundColor.DARK);
        Location check = new LocationImpl(4, 4, BackgroundColor.DARK);
        Assertions.assertTrue(board.isDiagonal(loc, check));
    }
}