package com.bruskajp.fisttablets.gameengine;

/**
 * Created by damonster on 11/13/15.
 */

import java.util.LinkedList;

/**
 * This object is immutable
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

    /***
     * Gets the previous x value;
     * @return An {@code int} with the previousX value.
     */
    public int getPreviousX() {
        return previousX;
    }

    /***
     * Gets the previous y value;
     * @return An {@code int} with the previousY value.
     */
    public int getPreviousY() {
        return previousY;
    }

    /***
     * Gets the new x value;
     * @return An {@code int} with the newX value.
     */
    public int getNewX() {
        return newX;
    }

    /***
     * Gets the new y value;
     * @return An {@code int} with the newY value.
     */
    public int getNewY() {
        return newY;
    }

    /***
     * Gets the list of deleted tokens;
     * @return A {@code LinkedList<Token>} with the deletedTokens.
     */
    public LinkedList<Token> getDeletedTokens() {
        return deletedTokens;
    }
}
