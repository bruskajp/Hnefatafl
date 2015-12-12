package com.bruskajp.fisttablets.artificialintelligence;

import android.util.Log;

import com.bruskajp.fisttablets.gameengine.Board;
import com.bruskajp.fisttablets.gameengine.Token;
import com.bruskajp.fisttablets.player.Player;
import com.bruskajp.fisttablets.gameengine.TokenMovement;
import java.util.HashMap;
import java.util.List;
import java.util.LinkedList;
import java.util.Map;

/**
 * Created by meliteja on 11/3/15
 */

public class ComputerPlayerOptions{

    Board board;
    TokenMovement tokenMovement;

    public ComputerPlayerOptions(Board board, TokenMovement tokenMovement){
        this.board=board;
        this.tokenMovement = tokenMovement;
    }

    /***
     * Finds every possible legal move that the player of the color {@code color} can make.
     * @param color The color player to get the options for.
     * @return A {@code List<MovementData>} of all possible moves for the corresponding token.
     */
    public List<MovementData> getPlayerOptions(Player.PlayerType color){
        List<MovementData> list = new LinkedList<>();
        for(Token tok : board.getRemainingPieces() ){
            if(tok.getColor()==
                    (color == Player.PlayerType.WHITE ? Token.TokenType.WHITE : Token.TokenType.BLACK)){
                list.addAll(getTokenOptions(tok));
            }
            if(tok.getTokenType()==null) {
                Log.e("ComputerPlayerOptions", "Null token type");
            }
        }
        return list;
    }

    public enum Direction{
        UP,DOWN,LEFT,RIGHT
    }

    /***
     * Gets all valid movements for a given {@code Token}, {@code tok}.
     * @param tok The {@code Token} to get the movements for.
     * @return A {@code List<MovementData>} of all valid movements for {@code tok}.
     */
    public List<MovementData> getTokenOptions(Token tok){
        List<MovementData> list = new LinkedList<>();
        list.addAll(getTokenOptions(tok,Direction.UP));
        list.addAll(getTokenOptions(tok,Direction.DOWN));
        list.addAll(getTokenOptions(tok,Direction.LEFT));
        list.addAll(getTokenOptions(tok,Direction.RIGHT));
        return list;
    }

    /***
     * Gets all valid movements for a given {@code Token}. {@code tok}, in the direction {@code dir}.
     * @param tok The {@code Token} to get the movements for.
     * @param dir The {@code Direction} to search for valid movements.
     * @return A {@code List<MovementData>} of all valid movements for {@code tok} in the direction {@code dir}.
     */
    public List<MovementData> getTokenOptions(Token tok, Direction dir){
        List<MovementData> list = new LinkedList<>();
        int x = tok.getxPosition();
        int y = tok.getyPosition();
        switch(dir){
            case UP:
                while(tokenMovement.isMoveValid(tok, x, y=y-1)){
                    list.add(new MovementData(tok, new PositionPair(x,y)));
                }
                break;
            case DOWN:
                while(tokenMovement.isMoveValid(tok, x, y=y+1)){
                    list.add(new MovementData(tok, new PositionPair(x,y)));
                }
                break;
            case LEFT:
                while(tokenMovement.isMoveValid(tok, x=x-1, y)){
                    list.add(new MovementData(tok, new PositionPair(x,y)));
                }
                break;
            case RIGHT:
                while(tokenMovement.isMoveValid(tok,x=x+1,y)){
                    list.add(new MovementData(tok, new PositionPair(x,y)));
                }
                break;
        }
        return list;
    }

}