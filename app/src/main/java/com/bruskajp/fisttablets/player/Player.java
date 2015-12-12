package com.bruskajp.fisttablets.player;

import com.bruskajp.fisttablets.gameengine.Board;
import com.bruskajp.fisttablets.gameengine.Move;
import com.bruskajp.fisttablets.gameengine.Token;
import com.bruskajp.fisttablets.gameengine.TokenMovement;

/**
 * Created by bruskajp on 10/21/15.
 */
public abstract class Player {

    /***
     * Enum describing the Player color.
     */
    public static enum PlayerType{
        BLACK,WHITE;
    }

    protected PlayerType playerType;
    protected PlayerInformation playerInformation;
    protected PlayerMovement playerMovement;
    protected Board board;
    protected TokenMovement tokenMovement;


    public Player(PlayerType playerType) {
        board = new Board();
        playerInformation = new PlayerInformation();
        tokenMovement = new TokenMovement(board);
        this.playerType = playerType;
        if(playerType.equals(PlayerType.WHITE)) {
            playerInformation.setPiecesRemaining(13);
        }else {
            playerInformation.setPiecesRemaining(24);
            playerInformation.isTurn = true;
        }
    }

    /***
     * Makes the player take a turn.
     */
    public abstract void takeTurn();

    /***
     * Makes the player finish a turn.
     */
    public abstract void finishTurn();

    /***
     * Attempts to move a Token on the board.
     * @param oldXPosition The {@code int} to describe the moves old xPosition.
     * @param oldYPosition The {@code int} to describe the moves old yPosition.
     * @param newXPosition The {@code int} to describe the moves new xPosition.
     * @param newYPosition The {@code int} to describe the moves new yPosition.
     * @return A {@code boolean} of the moves success.
     */
    public boolean movePiece(int oldXPosition, int oldYPosition, int newXPosition, int newYPosition){
        if(tokenMovement.movePiece(oldXPosition,oldYPosition,newXPosition,newYPosition)){
            return true;
        }
        return false;
    }

    /***
     * Attempts to move a Token on the board.
     * @param token The {@code Token} to describe the original position.
     * @param xPosition The {@code int} to describe the moves new xPosition.
     * @param yPosition The {@code int} to describe the moves new yPosition.
     * @return A {@code boolean} of the moves success.
     */
    public boolean movePiece(Token token, int xPosition, int yPosition){
        if (tokenMovement.movePiece(token, xPosition, yPosition)){
            return true;
        }
        return false;
    }

    /***
     * Attempts to move a Token on the board (specifically used for HumanPlayers).
     * @param oldXPosition The {@code int} to describe the moves old xPosition.
     * @param oldYPosition The {@code int} to describe the moves old yPosition.
     * @param newXPosition The {@code int} to describe the moves new xPosition.
     * @param newYPosition The {@code int} to describe the moves new yPosition.
     * @return A {@code boolean} of the moves success.
     */
    public boolean moveHumanPiece(int oldXPosition, int oldYPosition, int newXPosition, int newYPosition){
        return tokenMovement.moveHumanPiece(oldXPosition, oldYPosition, newXPosition, newYPosition);
    }

    /***
     * Undoes the last move performed.
     */
    public void undo(){
        tokenMovement.undo();
    }

    /***
     * Gets all valid movements for the Board.
     * @param xPosition The {@code int} describing the x position to check.
     * @param yPosition The {@code int} describing the y position to check.
     * @return A {@code Token} with what is in the position of the Board.
     */
    public Token checkBoardPosition(int xPosition, int yPosition){
        return board.checkBoardPosition(xPosition, yPosition);
    }

    /***
     * Gets the most recent Move;
     * @return A {@code Move} with the details of the most recent move.
     */
    public Move getLastMove(){
        return tokenMovement.getlastMove();
    }

    /***
     * Gets whether the Player is a winner;
     * @return A {@code boolean} with whether this Player is a winner.
     */
    public Boolean isWinner(){
        return tokenMovement.isWinner();
    }

    /***
     * Gets the color of the winner;
     * @return An {@code PlayerType} with the winning Player's color.
     */
    public PlayerType getWinnerType(){ return tokenMovement.getWinnerType();}

    /***
     * Gets a String of the player color;
     * @return An {@code String} with the Player's color.
     */
    public String getPlayerType() {
        if(playerType.equals(PlayerType.WHITE)) {
            return "White";
        }
        if(playerType.equals(PlayerType.BLACK)) {
            return "Black";
        }
        return null;
    }

}
