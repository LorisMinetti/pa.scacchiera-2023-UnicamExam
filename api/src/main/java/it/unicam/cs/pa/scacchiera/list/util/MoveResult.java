package it.unicam.cs.pa.scacchiera.list.util;

public enum MoveResult {
        OK,
        START_LOCATION_EMPTY, // posizione di partenza non valida, la pedina non esiste
        START_LOCATION_OTHER_PLAYER, // posizione di partenza non valida, la pedina non è del giocatore corrente
        MOVE_NOT_VALID // mossa non valida, se non è consentita dalla pedina
    }

