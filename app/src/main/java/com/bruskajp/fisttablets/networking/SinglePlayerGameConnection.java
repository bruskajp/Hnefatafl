package com.bruskajp.fisttablets.networking;

import com.bruskajp.fisttablets.gameengine.Move;
import com.bruskajp.fisttablets.player.Player;

/**
 * Created by damonster on 11/18/15.
 */

/***
 * UNUSED CLASS
 */
public class SinglePlayerGameConnection extends GameConnection{

    public SinglePlayerGameConnection(Player player) {
        super(player);
    }

    /***
     * Sends a move.
     */
    @Override
    public void sendMove(GameConnection gameConnection, Player player1, Player player2) {

    }

    /***
     * Receives a move.
     */
    @Override
    public void receiveMove(Move lastMove, Player player) {

    }
}
