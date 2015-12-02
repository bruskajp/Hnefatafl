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
    public final static int INITIAL_NUMBER_OF_BLACK_TOKENS = 20;
    public final static int MAX_NUMBER_OF_TOKENS = INITIAL_NUMBER_OF_BLACK_TOKENS+INITIAL_NUMBER_OF_WHITE_TOKENS;
    public final static int BOARD_LENGTH = 11;
    public Board() {
        initializeBoardState();
    }

    public HashSet<Token> getRemainingPieces(){
        // HACK: This is not the safest way, as the hashset is passed without any readonly protection, but I just put this here for now.
        return remainingPieces;
    }

    public Token checkBoardPosition(int xPosition, int yPosition){
        if(xPosition<0||yPosition<0||xPosition>=BOARD_LENGTH||yPosition>=BOARD_LENGTH) return null;
        return board[xPosition][yPosition];
    }

    private void initializeBoardState() {
        board = new Token[BOARD_LENGTH][BOARD_LENGTH];
        remainingPieces = new HashSet<>();
        setBlackBoardPieces();
        setWhiteBoardPieces();
        setUpPieceListeners();
    }

    private void setBlackBoardPieces(){
        for (int xPosition = 3; xPosition < 7; ++xPosition) {
            remainingPieces.add(board[xPosition][0] = new Token(xPosition, 0, Token.TokenType.BLACK));
        }
        remainingPieces.add(board[5][1] = new Token(5, 1, Token.TokenType.BLACK));

        for (int xPosition = 3; xPosition < 7; ++xPosition) {
            remainingPieces.add(board[xPosition][10] = new Token(xPosition, 10, Token.TokenType.BLACK));
        }
        remainingPieces.add(board[5][10] = new Token(5, 10, Token.TokenType.BLACK));

        for (int yPosition = 3; yPosition < 7; ++yPosition) {
            remainingPieces.add(board[0][yPosition] = new Token(0, yPosition, Token.TokenType.BLACK));
        }
        remainingPieces.add(board[1][5] = new Token(1, 5, Token.TokenType.BLACK));

        for (int yPosition = 3; yPosition < 7; ++yPosition) {
            remainingPieces.add(board[10][yPosition] = new Token(10, yPosition, Token.TokenType.BLACK));
        }
        remainingPieces.add(board[10][5] = new Token(10, 5, Token.TokenType.BLACK));
    }

    private void setWhiteBoardPieces() {
        remainingPieces.add(board[5][3] = new Token(5, 3, Token.TokenType.WHITE));
        remainingPieces.add(board[5][4] = new Token(5, 4, Token.TokenType.WHITE));
        remainingPieces.add(board[5][5] = new Token(5, 5, Token.TokenType.KING));
        remainingPieces.add(board[5][6] = new Token(5, 6, Token.TokenType.WHITE));
        remainingPieces.add(board[5][7] = new Token(5, 7, Token.TokenType.WHITE));

        remainingPieces.add(board[4][4] = new Token(4, 4, Token.TokenType.WHITE));
        remainingPieces.add(board[4][5] = new Token(4, 5, Token.TokenType.WHITE));
        remainingPieces.add(board[4][6] = new Token(4, 6, Token.TokenType.WHITE));

        remainingPieces.add(board[6][4] = new Token(6, 4, Token.TokenType.WHITE));
        remainingPieces.add(board[6][5] = new Token(6, 5, Token.TokenType.WHITE));
        remainingPieces.add(board[6][6] = new Token(6, 6, Token.TokenType.WHITE));

        remainingPieces.add(board[3][5] = new Token(3, 5, Token.TokenType.WHITE));
        remainingPieces.add(board[7][5] = new Token(7, 5, Token.TokenType.WHITE));
    }

    public void removePiece(Token token){
        remainingPieces.remove(token);
        board[token.getxPosition()][token.getyPosition()] = null;
    }

    public void setUpPieceListeners(){
        //THIS IS YOU DAVID
    }

}
