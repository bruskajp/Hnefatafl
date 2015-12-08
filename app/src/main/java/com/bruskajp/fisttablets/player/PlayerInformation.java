package com.bruskajp.fisttablets.player;

/**
 * Created by bruskajp on 10/21/15.
 */
public class PlayerInformation {

    int turnNumber;
    int piecesRemaining;
    Boolean isTurn;
    Boolean loser;
    Boolean winner;

    PlayerInformation() {
        this.initialize();
    }

    void initialize() {
        this.turnNumber = 0;
        this.isTurn = false;
        this.loser = false;
        this.winner = false;
    }

    public int getPiecesRemaining() {
        return piecesRemaining;
    }

    public int getTurnNumber() {
        return turnNumber;
    }

    public Boolean isTurn() {
        return isTurn;
    }

    public Boolean getLoser() {
        return loser;
    }

    public Boolean getWinner(){ return winner; }

    public void setPiecesRemaining(int piecesRemaining) {
        this.piecesRemaining = piecesRemaining;
    }

    public void setTurnNumber(int turnNumber) {
        this.turnNumber = turnNumber;
    }

    public void setTurn(Boolean isTurn) {
        this.isTurn = isTurn;
    }

    public void setLoser(Boolean loser) {
        this.loser = loser;
    }

    public void setWinner(Boolean winner) { this.winner = winner; }
}
