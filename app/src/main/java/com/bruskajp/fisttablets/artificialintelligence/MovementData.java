package com.bruskajp.fisttablets.artificialintelligence;
import com.bruskajp.fisttablets.gameengine.Token;

/**
 * Created by meliteja on 11/3/15
 */

/***
 * Used to pass information on which token to move and to which coordinates.
 */
public class MovementData{
    public Token tok;
    public PositionPair coordinates;
    public MovementData(Token tok, PositionPair coordinates){
        this.tok = tok;
        this.coordinates = coordinates;
    }
}