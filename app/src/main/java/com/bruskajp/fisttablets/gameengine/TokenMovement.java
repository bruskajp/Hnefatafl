package com.bruskajp.fisttablets.gameengine;

import android.util.Log;

import com.bruskajp.fisttablets.player.Player;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by damonster on 11/14/15.
 */
public class TokenMovement {

    Board board;
    private List<Move> moves;
    public boolean winner = false;
    public boolean loser = false;
    public Player.PlayerType winnerColor = null;
    private final int edgeIndex;

    public TokenMovement(Board board){
        this.board = board;
        moves = new LinkedList<Move>();
        edgeIndex= Board.BOARD_LENGTH-1;
    }

    /***
     * Checks if a move is possible.
     * @param token The {@code Token} to describe the original position.
     * @param xPosition The {@code int} to describe the moves new xPosition.
     * @param yPosition The {@code int} to describe the moves new yPosition.
     * @return A {@code Boolean} of if the move is valid.
     */
    public Boolean isMoveValid(Token token, int xPosition, int yPosition) {
        if(xPosition<0||yPosition<0||xPosition>edgeIndex||yPosition>edgeIndex) return false;
        if(!token.isKing()){
            if((xPosition==0&&(yPosition==0 || yPosition==edgeIndex) )||
                    (xPosition==edgeIndex&&(yPosition==0 || yPosition==edgeIndex))) return false;

        }
        if(token.getxPosition() == xPosition && token.getyPosition() != yPosition && board.checkBoardPosition(xPosition, yPosition) == null) {
            return true;
        }
        else if(token.getxPosition() != xPosition && token.getyPosition() == yPosition && board.checkBoardPosition(xPosition, yPosition) == null) {
            return true;
        }
        else {
            return false;
        }
    }

    /***
     * Checks if the spaces between a Token and its destination in a move are clear.
     * @param token The {@code Token} to describe the original position.
     * @param xPosition The {@code int} to describe the moves new xPosition.
     * @param yPosition The {@code int} to describe the moves new yPosition.
     * @return A {@code boolean} of if the spaces are clear.
     */
    public boolean checkValidXPosAndYPos(Token token, int xPosition, int yPosition){
        if(xPosition > token.xPosition) {
            for(int counter = token.xPosition + 1; counter < xPosition; ++counter){
                if(board.checkBoardPosition(counter, token.yPosition) != null) return false;
            }
            return true;
        }else if(xPosition < token.xPosition) {
            for(int counter = token.xPosition - 1; counter > xPosition; --counter){
                if(board.checkBoardPosition(counter, token.yPosition) != null) return false;
            }
            return true;
        }else if(yPosition > token.yPosition) {
            for(int counter = token.yPosition + 1; counter < yPosition; ++counter){
                if(board.checkBoardPosition(token.xPosition, counter) != null) return false;
            }
            return true;
        }else if(yPosition < token.yPosition) {
            for(int counter = token.yPosition - 1; counter > yPosition; --counter){
                if(board.checkBoardPosition(token.xPosition, counter) != null) return false;
            }
            return true;
        }

        return false;
    }

    /***
     * Attempts to move a Token on the board.
     * @param oldXPosition The {@code int} to describe the moves old xPosition.
     * @param oldYPosition The {@code int} to describe the moves old yPosition.
     * @param newXPosition The {@code int} to describe the moves new xPosition.
     * @param newYPosition The {@code int} to describe the moves new yPosition.
     * @return A {@code boolean} of the moves success.
     */
    public boolean movePiece(int oldXPosition, int oldYPosition, int newXPosition, int newYPosition) {
        Token tok;
        if((tok=board.checkBoardPosition(oldXPosition,oldYPosition))!=null){
            return movePiece(tok,newXPosition,newYPosition);
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
        Token token;

        if((token=board.checkBoardPosition(oldXPosition,oldYPosition))!=null){
            return (checkValidXPosAndYPos(token, newXPosition, newYPosition) && movePiece(token, newXPosition, newYPosition));
        }

        Log.i("TokenMovement", "why?");
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
        if (isMoveValid(token, xPosition, yPosition)) {
            int oldxPosition = token.getxPosition();
            int oldyPosition = token.getyPosition();
            token.xPosition = xPosition;
            token.yPosition = yPosition;

            board.board[xPosition][yPosition] = token;
            board.board[oldxPosition][oldyPosition] = null;

            LinkedList<Token> deletedTokens = removePieces(xPosition, yPosition);


            moves.add(new Move(oldxPosition, oldyPosition, xPosition, yPosition, deletedTokens));

            // check if the king is in a corner. If it is, set winner to true;
            if((board.checkBoardPosition(0,0) != null && board.checkBoardPosition(0,0).isKing()) ||
                    (board.checkBoardPosition(0,edgeIndex) != null && board.checkBoardPosition(0,edgeIndex).isKing()) ||
                    (board.checkBoardPosition(edgeIndex,0) != null && board.checkBoardPosition(edgeIndex,0).isKing()) ||
                    (board.checkBoardPosition(edgeIndex,edgeIndex) != null && board.checkBoardPosition(edgeIndex,edgeIndex).isKing())){
                this.winner = true;
                this.winnerColor = Player.PlayerType.WHITE;
                //Log.e("TokenMovement", "\n\n WINNER \n\n" );
            }

            if(token.getColor() == Token.TokenType.BLACK && board.remainingWhitePieces == 0){
                this.winner = true;
                this.winnerColor = Player.PlayerType.BLACK;
            }

            if(token.getColor() == Token.TokenType.WHITE && board.remainingBlackPieces == 0){
                this.winner = true;
                this.winnerColor = Player.PlayerType.WHITE;
            }

            return true;
        } else {
            return false;
        }
    }

    /***
     * Removes the pieces from the Board for a valid move.
     * @param xPosition The {@code int} to describe the moves old xPosition.
     * @param yPosition The {@code int} to describe the moves old yPosition.
     * @return A {@code LinkedList<Token>} of the Tokens removed.
     */
    LinkedList<Token> removePieces(int xPosition, int yPosition){
        // This may throw an index out of bounds exception...
        LinkedList<Token> deletedTokens = new LinkedList<>();
        //traps normal pieces
        if(board.checkBoardPosition(xPosition + 1,yPosition)!= null && board.checkBoardPosition(xPosition + 2,yPosition)!= null &&
                board.checkBoardPosition(xPosition,yPosition).getColor() != board.checkBoardPosition(xPosition + 1,yPosition).getColor() &&
                (board.checkBoardPosition(xPosition,yPosition).getColor() == board.checkBoardPosition(xPosition + 2,yPosition).getColor() &&
                        !board.checkBoardPosition(xPosition + 1,yPosition).isKing())) {
            Token deletableToken = board.checkBoardPosition(xPosition + 1,yPosition);
            deletedTokens.add(deletableToken);
            board.removePiece(deletableToken);
            if(deletableToken.getColor() == Token.TokenType.WHITE) {
                --board.remainingWhitePieces;
            } else {
                --board.remainingBlackPieces;
            }
        }

        if(board.checkBoardPosition(xPosition - 1,yPosition) != null && board.checkBoardPosition(xPosition - 2,yPosition) != null &&
                board.checkBoardPosition(xPosition,yPosition).getColor() != board.checkBoardPosition(xPosition - 1,yPosition).getColor() &&
                (board.checkBoardPosition(xPosition,yPosition).getColor() == board.checkBoardPosition(xPosition - 2,yPosition).getColor() &&
                !board.checkBoardPosition(xPosition - 1,yPosition).isKing())) {
            Token deletableToken = board.checkBoardPosition(xPosition - 1,yPosition);
            deletedTokens.add(deletableToken);
            board.removePiece(deletableToken);
            if(deletableToken.getColor() == Token.TokenType.WHITE) {
                --board.remainingWhitePieces;
            } else {
                --board.remainingBlackPieces;
            }
        }

        if(board.checkBoardPosition(xPosition,yPosition + 1) != null && board.checkBoardPosition(xPosition,yPosition + 2) != null &&
                board.checkBoardPosition(xPosition,yPosition).getColor() != board.checkBoardPosition(xPosition,yPosition + 1).getColor() &&
                (board.checkBoardPosition(xPosition,yPosition).getColor() == board.checkBoardPosition(xPosition,yPosition + 2).getColor() &&
                !board.checkBoardPosition(xPosition,yPosition + 1).isKing())) {
            Token deletableToken = board.checkBoardPosition(xPosition,yPosition + 1);
            deletedTokens.add(deletableToken);
            board.removePiece(deletableToken);
            if(deletableToken.getColor() == Token.TokenType.WHITE) {
                --board.remainingWhitePieces;
            } else {
                --board.remainingBlackPieces;
            }
        }

        if(board.checkBoardPosition(xPosition,yPosition - 1) != null && board.checkBoardPosition(xPosition,yPosition - 2) != null &&
                board.checkBoardPosition(xPosition,yPosition).getColor() != board.checkBoardPosition(xPosition, yPosition - 1).getColor() &&
                (board.checkBoardPosition(xPosition,yPosition).getColor() == board.checkBoardPosition(xPosition,yPosition - 2).getColor() &&
                !board.checkBoardPosition(xPosition,yPosition - 1).isKing())) {
            Token deletableToken = board.checkBoardPosition(xPosition,yPosition - 1);
            deletedTokens.add(deletableToken);
            board.removePiece(deletableToken);
            if(deletableToken.getColor() == Token.TokenType.WHITE) {
                --board.remainingWhitePieces;
            } else {
                --board.remainingBlackPieces;
            }
        }


        // traps kings
        if(board.checkBoardPosition(xPosition,yPosition - 1) != null && board.checkBoardPosition(xPosition,yPosition - 2) != null && board.checkBoardPosition(xPosition + 1,yPosition -1) != null && board.checkBoardPosition(xPosition - 1,yPosition - 1) != null &&
                board.checkBoardPosition(xPosition,yPosition).getColor() == Token.TokenType.BLACK && board.checkBoardPosition(xPosition,yPosition - 2).getColor() == Token.TokenType.BLACK &&
                board.checkBoardPosition(xPosition + 1,yPosition -1).getColor() == Token.TokenType.BLACK && board.checkBoardPosition(xPosition - 1,yPosition - 1).getColor() == Token.TokenType.BLACK &&
                board.checkBoardPosition(xPosition,yPosition - 1).isKing()){
            Token deletableToken = board.checkBoardPosition(xPosition,yPosition - 1);
            deletedTokens.add(deletableToken);
            board.removePiece(deletableToken);
        }

        if(board.checkBoardPosition(xPosition,yPosition + 1) != null && board.checkBoardPosition(xPosition,yPosition + 2) != null && board.checkBoardPosition(xPosition + 1,yPosition + 1) != null && board.checkBoardPosition(xPosition - 1,yPosition + 1) != null &&
                board.checkBoardPosition(xPosition,yPosition).getColor() == Token.TokenType.BLACK && board.checkBoardPosition(xPosition,yPosition + 2).getColor() == Token.TokenType.BLACK &&
                board.checkBoardPosition(xPosition + 1,yPosition + 1).getColor() == Token.TokenType.BLACK && board.checkBoardPosition(xPosition - 1,yPosition + 1).getColor() == Token.TokenType.BLACK &&
                board.checkBoardPosition(xPosition,yPosition + 1).isKing()){
            Token deletableToken = board.checkBoardPosition(xPosition,yPosition + 1);
            deletedTokens.add(deletableToken);
            board.removePiece(deletableToken);
        }

        if(board.checkBoardPosition(xPosition - 1,yPosition) != null && board.checkBoardPosition(xPosition - 2,yPosition) != null && board.checkBoardPosition(xPosition - 1,yPosition + 1) != null && board.checkBoardPosition(xPosition - 1,yPosition - 1) != null &&
                board.checkBoardPosition(xPosition,yPosition).getColor() == Token.TokenType.BLACK && board.checkBoardPosition(xPosition - 2,yPosition).getColor() == Token.TokenType.BLACK &&
                board.checkBoardPosition(xPosition - 1,yPosition + 1).getColor() == Token.TokenType.BLACK && board.checkBoardPosition(xPosition - 1,yPosition - 1).getColor() == Token.TokenType.BLACK &&
                board.checkBoardPosition(xPosition - 1,yPosition).isKing()){
            Token deletableToken = board.checkBoardPosition(xPosition - 1,yPosition);
            deletedTokens.add(deletableToken);
            board.removePiece(deletableToken);
        }

        if(board.checkBoardPosition(xPosition + 1,yPosition) != null && board.checkBoardPosition(xPosition + 2,yPosition) != null && board.checkBoardPosition(xPosition + 1,yPosition + 1) != null && board.checkBoardPosition(xPosition + 1,yPosition -1) != null &&
                board.checkBoardPosition(xPosition,yPosition).getColor() == Token.TokenType.BLACK && board.checkBoardPosition(xPosition+2,yPosition).getColor() == Token.TokenType.BLACK &&
                board.checkBoardPosition(xPosition+ 1,yPosition + 1).getColor() == Token.TokenType.BLACK && board.checkBoardPosition(xPosition + 1,yPosition - 1).getColor() == Token.TokenType.BLACK &&
                board.checkBoardPosition(xPosition + 1,yPosition).isKing()){
            Token deletableToken = board.checkBoardPosition(xPosition +1,yPosition);
            deletedTokens.add(deletableToken);
            board.removePiece(deletableToken);
        }
        return deletedTokens;
        // cover the cases for edge trap and multiple edge trap
    }


    /***
     * Undoes the last move performed.
     */
    public void undo(){
        if(!moves.isEmpty()){
            Move lastMove = moves.get(moves.size()-1);
            Token modifiedTok = board.board[lastMove.getPreviousX()][lastMove.getPreviousY()] = board.board[lastMove.getNewX()][lastMove.getNewY()];
            board.board[lastMove.getNewX()][lastMove.getNewY()] = null;
            modifiedTok.xPosition=lastMove.getPreviousX();
            modifiedTok.yPosition=lastMove.getPreviousY();

            if(!lastMove.getDeletedTokens().isEmpty()){
                for(Token token : lastMove.getDeletedTokens()){
                    board.board[token.getxPosition()][token.getyPosition()] = token;
                    board.getRemainingPieces().add(token);
                    if(token.getColor() == Token.TokenType.WHITE) {
                        ++board.remainingWhitePieces;
                    } else {
                        ++board.remainingBlackPieces;
                    }
                }
            }
            moves.remove(moves.size()-1);

            if(this.winner == true){
                this.winner = false;
                this.winnerColor = null;
            }
        }
    }

    /***
     * Gets the most recent Move;
     * @return A {@code Move} with the details of the most recent move.
     */
    public Move getlastMove(){
        if(moves.size() == 0){
            return null;
        }
        return moves.get(moves.size()-1);
    }

    /***
     * Gets whether the Player is a winner;
     * @return A {@code boolean} with whether this Player is a winner.
     */
    public boolean isWinner() {
        return this.winner;
    }

    /***
     * Gets the color of the winner;
     * @return An {@code PlayerType} with the winning Player's color.
     */
    public Player.PlayerType getWinnerType(){
        return this.winnerColor;
    }
}
