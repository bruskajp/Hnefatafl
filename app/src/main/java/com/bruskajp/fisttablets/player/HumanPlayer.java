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

    @Override
    public void takeTurn() {
        if(playerInformation.IsTurn()){
            //  I don't know if this is needed because it is dealt with by the game.

            // Look for the movement stuff (Listener)
        }
    }

}
