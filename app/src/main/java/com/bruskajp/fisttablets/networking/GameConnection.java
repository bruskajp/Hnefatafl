package com.bruskajp.fisttablets.networking;

import com.bruskajp.fisttablets.gameengine.Move;
import com.bruskajp.fisttablets.gameengine.Token;
import com.bruskajp.fisttablets.player.Player;

/**
 * Created by damonster on 10/10/15.
 */
public abstract class GameConnection{

    private final static String LOG_TAG = "GameConnection";

    Player player;

    public GameConnection(Player player){
        initialize(player);
    }

    private void initialize(Player player) {
        this.player = player;
    }

    public void beginGame() {}

    /***
     * Sends a move locally over the device.
     * @param gameConnection The {@code GameConnection} to get the movements for.
     * @param player1 The {@code Player} to send the moves to.
     * @param player2 The {@code Player} to receive the moves.
     */
    public abstract void sendMove(GameConnection gameConnection, Player player1, Player player2);

    /***
     * Receives a move locally over the device.
     * @param lastMove The {@code Move} that is received.
     * @param player The {@code Player} that receives the move.
     */
    public abstract void receiveMove(Move lastMove, Player player);
}
