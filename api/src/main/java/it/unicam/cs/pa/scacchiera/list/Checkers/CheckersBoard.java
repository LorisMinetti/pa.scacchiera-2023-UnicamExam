package it.unicam.cs.pa.scacchiera.list.Checkers;

import it.unicam.cs.pa.scacchiera.list.BoardImpl;
import it.unicam.cs.pa.scacchiera.list.Colour;
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

                boolean darkRowsPlayer = i == 0 || i == 1 || i==2;
                boolean lightRowsPlayer = i == 5 || i == 6 || i == 7;
                if (lastColor) {
                    superSchema[i][j] = new LocationImpl(i, j, BackgroundColor.DARK);       /* Nelle celle di colore scuro ci saranno tutti i pezzi*/
                    if(darkRowsPlayer){
                        superSchema[i][j].setPiece(new Pawn( superSchema[i][j], Colour.BLACK));     //I pezzi scuri dell'avversario saranno nelle prime tre righe dall'alto
                    }
                    if(lightRowsPlayer){
                        superSchema[i][j].setPiece(new Pawn( superSchema[i][j], Colour.WHITE));     // I pezzi chiari del giocatore principale saranno nelle prime tre righe dal basso.
                    }
                } else {
                    superSchema[i][j] = new LocationImpl(i, j, BackgroundColor.LIGHT);    // Celle bianche che non verranno mai occupate durante la partita.
                }
                /* Toggle per il colore della casella adiacente */
                lastColor = !lastColor;
            }
            /*Shifta di 1 il colore per la riga successiva*/
            lastColor = !lastColor;
        }
    }
}
