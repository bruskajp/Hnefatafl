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
    private final int edgeIndex;

    public TokenMovement(Board board){
        this.board = board;
        moves = new LinkedList<Move>();
        edgeIndex= board.BOARD_LENGTH-1;
    }

    public Boolean isMoveValid(Token token, int xPosition, int yPosition) {
        if(xPosition<0||yPosition<0||xPosition>=board. BOARD_LENGTH||yPosition>=board.BOARD_LENGTH) return false;
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

            board.board[xPosition][yPosition] = token;
            board.board[oldxPosition][oldyPosition] = null;

            LinkedList<Token> deletedTokens = removePieces(xPosition, yPosition);


            moves.add(new Move(oldxPosition, oldyPosition, xPosition, yPosition, deletedTokens));

            if(board.checkBoardPosition(0,0) != null && board.checkBoardPosition(0,0).isKing() ||
                    board.checkBoardPosition(0,edgeIndex) != null && board.checkBoardPosition(0,edgeIndex).isKing() ||
                    board.checkBoardPosition(edgeIndex,0) != null && board.checkBoardPosition(edgeIndex,0).isKing() ||
                    board.checkBoardPosition(edgeIndex,edgeIndex) != null && board.checkBoardPosition(edgeIndex,edgeIndex).isKing()){
                this.winner = true;
            }

            // check if the king is in a corner. If it is, set winner to true;

            return true;
        } else {
            return false;
        }
    }

    LinkedList<Token> removePieces(int xPosition, int yPosition){
        // This may throw an index out of bounds exception...
        LinkedList<Token> deletedTokens = new LinkedList<>();
        //traps normal pieces
        if(board.checkBoardPosition(xPosition + 1,yPosition)!= null && board.checkBoardPosition(xPosition + 2,yPosition)!= null &&
                board.checkBoardPosition(xPosition,yPosition).getColor() != board.checkBoardPosition(xPosition + 1,yPosition).getColor() &&
                (board.checkBoardPosition(xPosition + 2,yPosition)== null ||
                        board.checkBoardPosition(xPosition,yPosition).getColor() == board.checkBoardPosition(xPosition + 2,yPosition).getColor() &&
                                board.checkBoardPosition(xPosition + 1,yPosition).getColor() != Token.TokenType.KING)) {
            Token deletableToken = board.checkBoardPosition(xPosition + 1,yPosition);
            deletedTokens.add(deletableToken);
            board.removePiece(deletableToken);
        }
        if(board.checkBoardPosition(xPosition - 1,yPosition) != null && board.checkBoardPosition(xPosition - 2,yPosition) != null &&
                board.checkBoardPosition(xPosition,yPosition).getColor() != board.checkBoardPosition(xPosition - 1,yPosition).getColor() &&
                (board.checkBoardPosition(xPosition - 2,yPosition) == null ||
                board.checkBoardPosition(xPosition,yPosition).getColor() == board.checkBoardPosition(xPosition - 2,yPosition).getColor() &&
                board.checkBoardPosition(xPosition - 1,yPosition).getColor() != Token.TokenType.KING)) {
            Token deletableToken = board.checkBoardPosition(xPosition - 1,yPosition);
            deletedTokens.add(deletableToken);
            board.removePiece(deletableToken);
        }

        if(board.checkBoardPosition(xPosition,yPosition+1) != null && board.checkBoardPosition(xPosition,yPosition + 2) != null &&
                board.checkBoardPosition(xPosition,yPosition).getColor() != board.checkBoardPosition(xPosition,yPosition+1).getColor() &&
                (board.checkBoardPosition(xPosition - 2,yPosition) == null ||
                board.checkBoardPosition(xPosition,yPosition).getColor() == board.checkBoardPosition(xPosition,yPosition + 2).getColor() &&
                board.checkBoardPosition(xPosition,yPosition+1).getColor() != Token.TokenType.KING)) {
            Token deletableToken = board.checkBoardPosition(xPosition,yPosition+1);
            deletedTokens.add(deletableToken);
            board.removePiece(deletableToken);
        }

        if(board.checkBoardPosition(xPosition,yPosition-1) != null && board.checkBoardPosition(xPosition,yPosition - 2) != null &&
                // TODO: JAMES FIX YOUR SHIT BROKE ON THE LINE BELOW WITH NULL REFERENCE
                board.checkBoardPosition(xPosition,yPosition).getColor() != board.checkBoardPosition(xPosition - 1,yPosition).getColor() &&
                // TODO: THIS LINE ABOVE ME YEAH THATS RIGHT
                (board.checkBoardPosition(xPosition - 2,yPosition) == null ||
                board.checkBoardPosition(xPosition,yPosition).getColor() == board.checkBoardPosition(xPosition,yPosition - 2).getColor() &&
                board.checkBoardPosition(xPosition,yPosition-1).getColor() != Token.TokenType.KING)) {
            Token deletableToken = board.checkBoardPosition(xPosition,yPosition-1);
            deletedTokens.add(deletableToken);
            board.removePiece(deletableToken);
        }


        //traps kings
        if(board.checkBoardPosition(xPosition,yPosition-1) != null && board.checkBoardPosition(xPosition,yPosition - 2) != null && board.checkBoardPosition(xPosition + 1,yPosition -1) != null && board.checkBoardPosition(xPosition - 1,yPosition - 1) != null &&
                board.checkBoardPosition(xPosition,yPosition).getColor() == Token.TokenType.BLACK && board.checkBoardPosition(xPosition,yPosition - 2).getColor() == Token.TokenType.BLACK &&
                board.checkBoardPosition(xPosition + 1,yPosition -1).getColor() == Token.TokenType.BLACK && board.checkBoardPosition(xPosition - 1,yPosition - 1).getColor() == Token.TokenType.BLACK &&
                board.checkBoardPosition(xPosition,yPosition-1).getColor() == Token.TokenType.KING){
            Token deletableToken = board.checkBoardPosition(xPosition,yPosition-1);
            deletedTokens.add(deletableToken);
            board.removePiece(deletableToken);
        }

        if(board.checkBoardPosition(xPosition,yPosition+1) != null && board.checkBoardPosition(xPosition,yPosition + 2) != null && board.checkBoardPosition(xPosition+ 1,yPosition + 1) != null && board.checkBoardPosition(xPosition - 1,yPosition + 1) != null &&
                board.checkBoardPosition(xPosition,yPosition).getColor() == Token.TokenType.BLACK && board.checkBoardPosition(xPosition,yPosition + 2).getColor() == Token.TokenType.BLACK &&
                board.checkBoardPosition(xPosition+ 1,yPosition + 1).getColor() == Token.TokenType.BLACK && board.checkBoardPosition(xPosition - 1,yPosition + 1).getColor() == Token.TokenType.BLACK &&
                board.checkBoardPosition(xPosition,yPosition+1).getColor() == Token.TokenType.KING){
            Token deletableToken = board.checkBoardPosition(xPosition,yPosition+1);
            deletedTokens.add(deletableToken);
            board.removePiece(deletableToken);
        }

        if(board.checkBoardPosition(xPosition - 1,yPosition) != null && board.checkBoardPosition(xPosition - 2,yPosition) != null && board.checkBoardPosition(xPosition - 1,yPosition + 1) != null && board.checkBoardPosition(xPosition - 1,yPosition - 1) != null &&
                board.checkBoardPosition(xPosition,yPosition).getColor() == Token.TokenType.BLACK && board.checkBoardPosition(xPosition - 2,yPosition).getColor() == Token.TokenType.BLACK &&
                board.checkBoardPosition(xPosition - 1,yPosition + 1).getColor() == Token.TokenType.BLACK && board.checkBoardPosition(xPosition - 1,yPosition - 1).getColor() == Token.TokenType.BLACK &&
                board.checkBoardPosition(xPosition - 1,yPosition).getColor() == Token.TokenType.KING){
            Token deletableToken = board.checkBoardPosition(xPosition,yPosition+1);
            deletedTokens.add(deletableToken);
            board.removePiece(deletableToken);
        }

        if(board.checkBoardPosition(xPosition + 1,yPosition) != null && board.checkBoardPosition(xPosition+2,yPosition) != null && board.checkBoardPosition(xPosition+ 1,yPosition + 1) != null && board.checkBoardPosition(xPosition + 1,yPosition -1) != null &&
                board.checkBoardPosition(xPosition,yPosition).getColor() == Token.TokenType.BLACK && board.checkBoardPosition(xPosition+2,yPosition).getColor() == Token.TokenType.BLACK &&
                board.checkBoardPosition(xPosition+ 1,yPosition + 1).getColor() == Token.TokenType.BLACK && board.checkBoardPosition(xPosition + 1,yPosition -1).getColor() == Token.TokenType.BLACK &&
                board.checkBoardPosition(xPosition + 1,yPosition).getColor() == Token.TokenType.KING){
            Token deletableToken = board.checkBoardPosition(xPosition,yPosition+1);
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
