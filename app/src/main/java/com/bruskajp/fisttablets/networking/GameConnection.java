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

    public void beginGame() {

    }

    public abstract void sendMove(GameConnection gameConnection, Player player1, Player player2);

    public abstract void receiveMove(Move lastMove, Player player);
}
