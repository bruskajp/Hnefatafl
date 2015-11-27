package com.bruskajp.fisttablets.gameengine;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by damonster on 11/14/15.
 */
public class TokenMovement {

    Board board;
    private List<Move> moves;
    public boolean winner = false;

    public TokenMovement(Board board){
        this.board = board;
        moves = new LinkedList<Move>();
    }

    public Boolean isMoveValid(Token token, int xPosition, int yPosition) {
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

    public Boolean movePiece(Token token, int xPosition, int yPosition){
        if (isMoveValid(token, xPosition, yPosition)) {
            int oldxPosition = token.getxPosition();
            int oldyPosition = token.getyPosition();
            token.xPosition = xPosition;
            token.yPosition = yPosition;
            LinkedList<Token> deletedTokens = null;
            
            removePieces(xPosition, yPosition, deletedTokens);

            moves.add(new Move(oldxPosition, oldyPosition, xPosition, yPosition, deletedTokens));
            board.board[xPosition][yPosition] = token;
            board.board[oldxPosition][oldyPosition] = null;

            if(board.checkBoardPosition(0,0).isKing() || board.checkBoardPosition(0,10).isKing() || board.checkBoardPosition(10,0).isKing() || board.checkBoardPosition(10,10).isKing()){
                this.winner = true;
            }

            // check if the king is in a corner. If it is, set winner to true;

            return true;
        } else {
            return false;
        }
    }

    void removePieces(int xPosition, int yPosition, LinkedList<Token> deletedTokens){
        // This may throw an index out of bounds exception...

        //traps normal pieces
        if(board.board[xPosition + 1][yPosition] != null && board.board[xPosition + 2][yPosition] != null &&
                board.board[xPosition][yPosition].getColor() != board.board[xPosition + 1][yPosition].getColor() &&
                (board.board[xPosition + 2][yPosition] == null ||
                board.board[xPosition][yPosition].getColor() == board.board[xPosition + 2][yPosition].getColor() &&
                board.board[xPosition + 1][yPosition].getColor() != Token.TokenType.KING)) {
            Token deletableToken = board.board[xPosition + 1][yPosition];
            deletedTokens.add(deletableToken);
            board.removePiece(deletableToken);
        }

        if(board.board[xPosition - 1][yPosition] != null && board.board[xPosition - 2][yPosition] != null &&
                board.board[xPosition][yPosition].getColor() != board.board[xPosition - 1][yPosition].getColor() &&
                (board.board[xPosition - 2][yPosition] == null ||
                board.board[xPosition][yPosition].getColor() == board.board[xPosition - 2][yPosition].getColor() &&
                board.board[xPosition - 1][yPosition].getColor() != Token.TokenType.KING)) {
            Token deletableToken = board.board[xPosition - 1][yPosition];
            deletedTokens.add(deletableToken);
            board.removePiece(deletableToken);
        }

        if(board.board[xPosition][yPosition + 1] != null && board.board[xPosition][yPosition + 2] != null &&
                board.board[xPosition][yPosition].getColor() != board.board[xPosition][yPosition + 1].getColor() &&
                (board.board[xPosition - 2][yPosition] == null ||
                board.board[xPosition][yPosition].getColor() == board.board[xPosition][yPosition + 2].getColor() &&
                board.board[xPosition][yPosition + 1].getColor() != Token.TokenType.KING)) {
            Token deletableToken = board.board[xPosition][yPosition + 1];
            deletedTokens.add(deletableToken);
            board.removePiece(deletableToken);
        }

        if(board.board[xPosition][yPosition - 1] != null && board.board[xPosition][yPosition - 2] != null &&
                board.board[xPosition][yPosition].getColor() != board.board[xPosition - 1][yPosition].getColor() &&
                (board.board[xPosition - 2][yPosition] == null ||
                board.board[xPosition][yPosition].getColor() == board.board[xPosition][yPosition - 2].getColor() &&
                board.board[xPosition][yPosition - 1].getColor() != Token.TokenType.KING)) {
            Token deletableToken = board.board[xPosition][yPosition - 1];
            deletedTokens.add(deletableToken);
            board.removePiece(deletableToken);
        }


        //traps kings
        if(board.board[xPosition][yPosition - 1] != null && board.board[xPosition][yPosition - 2] != null && board.board[xPosition + 1][yPosition - 1] != null && board.board[xPosition - 1][yPosition - 1] != null &&
                board.board[xPosition][yPosition].getColor() == Token.TokenType.BLACK && board.board[xPosition][yPosition - 2].getColor() == Token.TokenType.BLACK &&
                board.board[xPosition + 1][yPosition - 1].getColor() == Token.TokenType.BLACK && board.board[xPosition - 1][yPosition - 1].getColor() == Token.TokenType.BLACK &&
                board.board[xPosition][yPosition - 1].getColor() == Token.TokenType.KING){
            Token deletableToken = board.board[xPosition][yPosition - 1];
            deletedTokens.add(deletableToken);
            board.removePiece(deletableToken);
        }

        if(board.board[xPosition][yPosition + 1] != null && board.board[xPosition][yPosition + 2] != null && board.board[xPosition + 1][yPosition + 1] != null && board.board[xPosition - 1][yPosition + 1] != null &&
                board.board[xPosition][yPosition].getColor() == Token.TokenType.BLACK && board.board[xPosition][yPosition + 2].getColor() == Token.TokenType.BLACK &&
                board.board[xPosition + 1][yPosition + 1].getColor() == Token.TokenType.BLACK && board.board[xPosition - 1][yPosition + 1].getColor() == Token.TokenType.BLACK &&
                board.board[xPosition][yPosition + 1].getColor() == Token.TokenType.KING){
            Token deletableToken = board.board[xPosition][yPosition + 1];
            deletedTokens.add(deletableToken);
            board.removePiece(deletableToken);
        }

        if(board.board[xPosition - 1][yPosition] != null && board.board[xPosition - 2][yPosition] != null && board.board[xPosition - 1][yPosition + 1] != null && board.board[xPosition - 1][yPosition - 1] != null &&
                board.board[xPosition][yPosition].getColor() == Token.TokenType.BLACK && board.board[xPosition - 2][yPosition].getColor() == Token.TokenType.BLACK &&
                board.board[xPosition - 1][yPosition + 1].getColor() == Token.TokenType.BLACK && board.board[xPosition - 1][yPosition - 1].getColor() == Token.TokenType.BLACK &&
                board.board[xPosition - 1][yPosition].getColor() == Token.TokenType.KING){
            Token deletableToken = board.board[xPosition][yPosition + 1];
            deletedTokens.add(deletableToken);
            board.removePiece(deletableToken);
        }

        if(board.board[xPosition + 1][yPosition] != null && board.board[xPosition + 2][yPosition] != null && board.board[xPosition + 1][yPosition + 1] != null && board.board[xPosition + 1][yPosition - 1] != null &&
                board.board[xPosition][yPosition].getColor() == Token.TokenType.BLACK && board.board[xPosition + 2][yPosition].getColor() == Token.TokenType.BLACK &&
                board.board[xPosition + 1][yPosition + 1].getColor() == Token.TokenType.BLACK && board.board[xPosition + 1][yPosition - 1].getColor() == Token.TokenType.BLACK &&
                board.board[xPosition + 1][yPosition].getColor() == Token.TokenType.KING){
            Token deletableToken = board.board[xPosition][yPosition + 1];
            deletedTokens.add(deletableToken);
            board.removePiece(deletableToken);
        }

        // cover the cases for edge trap and multiple edge trap
    }


    /***
     * Undoes the last move performed.
     */
    public void undo(){
        if(!moves.isEmpty()){
            Move lastMove = moves.get(moves.size()-1);
            board.board[lastMove.getPreviousX()][lastMove.getPreviousY()] = board.board[lastMove.getNewX()][lastMove.getNewY()];
            board.board[lastMove.getNewX()][lastMove.getNewY()] = null;
            moves.remove(moves.size()-1);

            if(!lastMove.getDeletedTokens().isEmpty()){
                for(Token token : lastMove.getDeletedTokens()){
                    board.board[token.getxPosition()][token.getyPosition()] = token;
                }
            }
        }
    }

    public Move getlastMove(){
        return moves.get(moves.size()-1);
    }

}
