package com.bruskajp.fisttablets.gameengine;

/**
 * Created by bruskajp on 10/21/15.
 */
public class Token {

    public int xPosition = -1;
    public int yPosition = -1;
    private TokenType tokenType;

    /***
     * Enum describing the Token color
     */
    public static enum TokenType{
        BLACK,WHITE,KING;
    }

    public Token(Token oldToken) {
        this.xPosition = oldToken.getxPosition();
        this.yPosition = oldToken.getyPosition();
        this.tokenType = oldToken.getTokenType();
    }

    public Token(int xPosition, int yPosition, TokenType tokenType) {
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        this.tokenType = tokenType;
    }

    /***
     * Gets the Token color;
     * @return A {@code TokenType} with the color.
     */
    public TokenType getColor() {
        return tokenType == TokenType.KING ? TokenType.WHITE : this.tokenType;
    }

    /***
     * Gets whether or not this Token is a king;
     * @return A {@code boolean} with whether or not this Token is a king.
     */
    public boolean isKing() {
        return tokenType == TokenType.KING;
    }

    /***
     * Gets the type of token;
     * @return A {@code TokenType} with the type of token.
     */
    public TokenType getTokenType(){ return this.tokenType; }

    /***
     * Gets the y position of the Token;
     * @return An {@code int} with the Token's yPosition.
     */
    public int getyPosition() {
        return this.yPosition;
    }

    /***
     * Gets the x position of the Token;
     * @return An {@code int} with the Token's xPosition.
     */
    public int getxPosition() {
        return this.xPosition;
    }

}
