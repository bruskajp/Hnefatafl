package com.bruskajp.fisttablets.gameengine;

/**
 * Created by bruskajp on 10/21/15.
 */
public class Token {

    public int xPosition = -1;
    public int yPosition = -1;
    private TokenType tokenType;

    public static enum TokenType{
        BLACK,WHITE,KING;
    }

    public Token(Token oldToken) {
        this.xPosition = oldToken.getxPosition();
        this.yPosition = oldToken.getyPosition();
        this.tokenType = oldToken.getTokenType();
    }

    public Token(int xPosition, int yPosition, TokenType tokenType) { // Add an image view to this.
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        this.tokenType = tokenType;
    }

    public TokenType getColor() {
        return tokenType == TokenType.KING ? TokenType.WHITE : this.tokenType;
    }

    public boolean isKing() {
        return tokenType == TokenType.KING;
    }

    public TokenType getTokenType(){ return this.tokenType; }

    public int getyPosition() {
        return this.yPosition;
    }

    public int getxPosition() {
        return this.xPosition;
    }

}
