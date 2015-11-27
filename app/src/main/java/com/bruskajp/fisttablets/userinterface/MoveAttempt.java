package com.bruskajp.fisttablets.userinterface;



/**
 * Created by damonster on 11/19/15.
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

    public void setMoveAttempt(int previousX, int previousY, int newX, int newY){
        this.previousX = previousX;
        this.previousY = previousY;
        this.newX = newX;
        this.newY = newY;
    }

}
