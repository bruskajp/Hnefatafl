package com.bruskajp.fisttablets.userinterface;



/**
 * Created by damonster on 11/19/15.
 */

/***
 * UNUSED CLASS
 */
public class MoveAttempt {

    int previousX;
    int previousY;
    int newX;
    int newY;

    public MoveAttempt(int previousX, int previousY, int newX, int newY){
        this.previousX = previousX;
        this.previousY = previousY;
        this.newX = newX;
        this.newY = newY;
    }

    /***
     * Sets the values of the move attempt.
     * @param previousX The {@code int} to describe the moves old xPosition.
     * @param previousY The {@code int} to describe the moves old yPosition.
     * @param newX The {@code int} to describe the moves new xPosition.
     * @param newY The {@code int} to describe the moves new yPosition.
     */
    public void setMoveAttempt(int previousX, int previousY, int newX, int newY){
        this.previousX = previousX;
        this.previousY = previousY;
        this.newX = newX;
        this.newY = newY;
    }

}
