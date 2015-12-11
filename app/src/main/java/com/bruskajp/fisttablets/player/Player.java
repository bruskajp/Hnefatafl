package com.bruskajp.fisttablets.player;

import com.bruskajp.fisttablets.gameengine.Board;
import com.bruskajp.fisttablets.gameengine.Move;
import com.bruskajp.fisttablets.gameengine.Token;
import com.bruskajp.fisttablets.gameengine.TokenMovement;

/**
 * Created by bruskajp on 10/21/15.
 */
public abstract class Player {

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

    public abstract void takeTurn();

    public abstract void finishTurn();

    public boolean movePiece(int oldXPosition, int oldYPosition, int newXPosition, int newYPosition){
        if(tokenMovement.movePiece(oldXPosition,oldYPosition,newXPosition,newYPosition)){
            return true;
        }
        return false;
    }

    public boolean movePiece(Token token, int xPosition, int yPosition){
        if (tokenMovement.movePiece(token, xPosition, yPosition)){
            return true;
        }
        return false;
    }

    public boolean moveHumanPiece(int oldXPosition, int oldYPosition, int newXPosition, int newYPosition){
        return tokenMovement.moveHumanPiece(oldXPosition, oldYPosition, newXPosition, newYPosition);
    }

    public void undo(){
        tokenMovement.undo();
    }


    public Token checkBoardPosition(int xPosition, int yPosition){
        return board.checkBoardPosition(xPosition, yPosition);
    }

    public Move getLastMove(){
        return tokenMovement.getlastMove();
    }

    public Boolean isWinner(){
        return tokenMovement.isWinner();
    }

    public PlayerType getWinnerType(){ return tokenMovement.getWinnerType();}
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
