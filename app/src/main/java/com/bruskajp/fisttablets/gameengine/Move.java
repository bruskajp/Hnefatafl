package com.bruskajp.fisttablets.gameengine;

/**
 * Created by damonster on 11/13/15.
 */

import java.util.LinkedList;

/**
 * This object is immutable
 *
 * Are this and MovementData the same thing?
 */
final public class Move {
    private int previousX, previousY, newX, newY;
    private LinkedList<Token> deletedTokens;

    public Move(int previousX, int previousY, int newX, int newY, LinkedList<Token> deletedTokens){
        this.previousX = previousX;
        this.previousY = previousY;
        this.newX = newX;
        this.newY = newY;
        this.deletedTokens = deletedTokens;
    }

    public int getPreviousX() {
        return previousX;
    }

    public int getPreviousY() {
        return previousY;
    }

    public int getNewX() {
        return newX;
    }

    public int getNewY() {
        return newY;
    }

    public LinkedList<Token> getDeletedTokens() {
        return deletedTokens;
    }
}
