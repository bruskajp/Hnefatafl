package com.bruskajp.fisttablets.artificialintelligence;

import android.util.Log;

import com.bruskajp.fisttablets.player.Player;

/**
 * Created by damonster on 10/10/15.
 */
public class ComputerPlayer extends Player{

    private final static String LOG_TAG = "ComputerPlayer";

    private ArtificialIntelligence ai;
    public ComputerPlayer(PlayerType playerType){
        super(playerType);
        ai = new SimpleAI(board,tokenMovement,playerType);
    }


    // HEY JACOB, LOOK AT THIS FUNCTION
    // use this to make your actual move.
    // I think this is all I need to do to implement it.


    @Override
    public void takeTurn() {
        makeNextMove();
    }

    public void initializeComputerPlayer() {
        Log.i(LOG_TAG, "This is how to log things to console");
    }

    /***
     * Makes the computer player take its next move.
     */
    public void makeNextMove(){
        MovementData nextMove = ai.getNextMove();
        //this.tokenMovement.movePiece(nextMove.tok, nextMove.coordinates.x, nextMove.coordinates.y);
        movePiece(nextMove.tok, nextMove.coordinates.x, nextMove.coordinates.y);
    }
}