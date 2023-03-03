package it.unicam.cs.pa.scacchiera.list.util;

public enum MoveResult {
        OK,
        START_LOCATION_EMPTY, // posizione di partenza non valida, la pedina non esiste
        START_LOCATION_OTHER_PLAYER, // posizione di partenza non valida, la pedina non è del giocatore corrente
        END_LOCATION_OCCUPIED, // posizione di arrivo non valida, se è occupata
        END_LOCATION_OUTSIDE, // posizione di arrivo non valida, se è fuori dalla scacchiera
        END_LOCATION_NOT_REACHABLE, // posizione di arrivo non valida, se non è raggiungibile dalla posizione di partenza'
        MOVE_NOT_VALID // mossa non valida, se non è consentita dalla pedina
    }

