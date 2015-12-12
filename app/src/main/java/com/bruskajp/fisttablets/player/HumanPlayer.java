package com.bruskajp.fisttablets.player;

/**
 * Created by damonster on 11/19/15.
 */
public class HumanPlayer extends Player {

    public HumanPlayer(PlayerType playerType) {
        super(playerType);
    }

    /***
     * Gets whether the turn is in progress.
     * @return A {@code boolean} describing if the turn is in progress or not.
     */
    public boolean isTurn(){
        return playerInformation.isTurn();
    }

    /***
     * Makes the player take a turn.
     */
    @Override
    public void takeTurn() {
        playerInformation.isTurn = true;
    }

    /***
     * Makes the player finish a turn.
     */
    @Override
    public void finishTurn() {
        playerInformation.isTurn = false;
    }

}
