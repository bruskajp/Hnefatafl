package com.bruskajp.fisttablets.gameengine;

import android.util.Log;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by bruskajp on 10/21/15.
 */
public class Board {

    public Token board[][];
    private HashSet<Token> remainingPieces;
    private final static String LOG_TAG = "Board";
    public boolean winner = false;
    public final static int INITIAL_NUMBER_OF_WHITE_TOKENS = 13;
    public final static int INITIAL_NUMBER_OF_BLACK_TOKENS = 24;

    public Board() {
        initializeBoardState();
    }

    public HashSet<Token> getRemainingPieces(){
        // HACK: This is not the safest way, as the hashset is passed without any readonly protection, but I just put this here for now.
        return remainingPieces;
    }

    public Token checkBoardPosition(int xPosition, int yPosition){
        return board[xPosition][yPosition];
    }

    private void initializeBoardState() {
        board = new Token[13][13];
        remainingPieces = new HashSet<>();
        setBlackBoardPieces();
        setWhiteBoardPieces();
        setUpPieceListeners();
    }

    private void setBlackBoardPieces(){
        for (int xPosition = 5; xPosition < 9; ++xPosition) {
            remainingPieces.add(board[xPosition][0] = new Token(xPosition, 0, Token.TokenType.BLACK));
        }
        remainingPieces.add(board[7][1] = new Token(7, 1, Token.TokenType.BLACK));

        for (int xPosition = 5; xPosition < 9; ++xPosition) {
            remainingPieces.add(board[xPosition][12] = new Token(xPosition, 12, Token.TokenType.BLACK));
        }
        remainingPieces.add(board[7][11] = new Token(7, 11, Token.TokenType.BLACK));

        for (int yPosition = 5; yPosition < 9; ++yPosition) {
            remainingPieces.add(board[0][yPosition] = new Token(0, yPosition, Token.TokenType.BLACK));
        }
        remainingPieces.add(board[1][7] = new Token(1, 7, Token.TokenType.BLACK));

        for (int yPosition = 5; yPosition < 9; ++yPosition) {
            remainingPieces.add(board[12][yPosition] = new Token(12, yPosition, Token.TokenType.BLACK));
        }
        remainingPieces.add(board[11][7] = new Token(11, 7, Token.TokenType.BLACK));
    }

    private void setWhiteBoardPieces() {
        remainingPieces.add(board[7][5] = new Token(5, 3, Token.TokenType.WHITE));
        remainingPieces.add(board[7][6] = new Token(5, 4, Token.TokenType.WHITE));
        remainingPieces.add(board[7][7] = new Token(5, 5, Token.TokenType.KING));
        remainingPieces.add(board[7][8] = new Token(5, 6, Token.TokenType.WHITE));
        remainingPieces.add(board[7][9] = new Token(5, 7, Token.TokenType.WHITE));

        remainingPieces.add(board[6][6] = new Token(4, 4, Token.TokenType.WHITE));
        remainingPieces.add(board[6][7] = new Token(4, 5, Token.TokenType.WHITE));
        remainingPieces.add(board[6][8] = new Token(4, 6, Token.TokenType.WHITE));

        remainingPieces.add(board[8][6] = new Token(6, 4, Token.TokenType.WHITE));
        remainingPieces.add(board[8][7] = new Token(6, 5, Token.TokenType.WHITE));
        remainingPieces.add(board[8][8] = new Token(6, 6, Token.TokenType.WHITE));

        remainingPieces.add(board[5][7] = new Token(3, 5, Token.TokenType.WHITE));
        remainingPieces.add(board[9][7] = new Token(7, 5, Token.TokenType.WHITE));
    }

    public void removePiece(Token token){
        remainingPieces.remove(token);
        board[token.getxPosition()][token.getyPosition()] = null;
    }

    public void setUpPieceListeners(){
        //THIS IS YOU DAVID
    }

}
