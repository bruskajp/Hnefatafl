package com.bruskajp.fisttablets.player;

/**
 * Created by damonster on 11/19/15.
 */
public class HumanPlayer extends Player {

    public HumanPlayer(PlayerType playerType) {
        super(playerType);
    }

    public boolean isTurn(){
        return playerInformation.isTurn();
    }

    @Override
    public void takeTurn() {
        playerInformation.isTurn = true;
    }

    @Override
    public void finishTurn() {
        playerInformation.isTurn = false;
    }

}
