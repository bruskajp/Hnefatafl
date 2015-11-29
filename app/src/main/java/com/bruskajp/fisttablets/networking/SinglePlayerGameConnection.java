package com.bruskajp.fisttablets.networking;

import com.bruskajp.fisttablets.gameengine.Move;
import com.bruskajp.fisttablets.player.Player;

/**
 * Created by damonster on 11/18/15.
 */
public class SinglePlayerGameConnection extends GameConnection{

    public SinglePlayerGameConnection(Player player){
        super(player);
    }
    public void sendMove(GameConnection gameConnection, Player player1, Player player2){

    }

    public  void receiveMove(Move lastMove, Player player){

    }

    public SinglePlayerGameConnection(Player player) {
        super(player);
    }

    @Override
    public void sendMove(GameConnection gameConnection, Player player1, Player player2) {

    }

    @Override
    public void receiveMove(Move lastMove, Player player) {

    }
}
