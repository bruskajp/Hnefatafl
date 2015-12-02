package com.bruskajp.fisttablets.networking;

import android.util.Log;

import com.bruskajp.fisttablets.gameengine.Move;
import com.bruskajp.fisttablets.gameengine.Token;
import com.bruskajp.fisttablets.player.HumanPlayer;
import com.bruskajp.fisttablets.player.Player;

/**
 * Created by damonster on 11/18/15.
 */
public class LocalGameConnection extends GameConnection{


    public LocalGameConnection(Player player) {
        super(player);
    }

    public void sendMove(GameConnection gameConnection, Player player1, Player player2) {
        gameConnection.receiveMove(player1.getLastMove(), player2);
    }

    public void receiveMove(Move lastMove, Player player) {
        player.movePiece(lastMove.getPreviousX(), lastMove.getPreviousY(), lastMove.getNewX(), lastMove.getNewY()); //can use null because it is not used
    }
}
