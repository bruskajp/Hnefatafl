package com.bruskajp.fisttablets.player;

/**
 * Created by damonster on 11/19/15.
 */
public class HumanPlayer extends Player {

    public HumanPlayer(PlayerType playerType) {
        super(playerType);



        /* This is the on touch listener stuff

            BoardListener boardListener = new BoardListener();

         */

    }

    public boolean isTurn(){
        return playerInformation.isTurn();
    }

    @Override
    public void takeTurn() {
        playerInformation.isTurn = true;
    }

    public void finishTurn() {
        playerInformation.isTurn = false;
    }

}
