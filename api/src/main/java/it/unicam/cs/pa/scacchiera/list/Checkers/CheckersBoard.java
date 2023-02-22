package it.unicam.cs.pa.scacchiera.list.Checkers;

import it.unicam.cs.pa.scacchiera.list.BoardImpl;
import it.unicam.cs.pa.scacchiera.list.Location;
import it.unicam.cs.pa.scacchiera.list.LocationImpl;
import it.unicam.cs.pa.scacchiera.list.LocationImpl.*;


/**
 * Damiera che inizializza già un campo da gioco abile di avere le caselle con il colore di
 * background adatto al gioco della dama italiana.
 */
public class CheckersBoard extends BoardImpl {

    public CheckersBoard(int row, int column) throws Exception {
        super(row, column);
        Location[][] superSchema = super.getSchema();

        /* Creare la scacchiera della Dama con colori alternati */
        boolean lastColor = false;
        for(int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                if (lastColor) {
                    superSchema[i][j] = new LocationImpl(i, j, BackgroundColor.DARK);
                } else {
                    superSchema[i][j] = new LocationImpl(i, j, BackgroundColor.LIGHT);
                }
                /* Toggle per il colore della casella adiacente */
                lastColor = !lastColor;
            }
            /*Shifta di 1 il colore per la riga successiva*/
            lastColor = !lastColor;
        }
    }
}
