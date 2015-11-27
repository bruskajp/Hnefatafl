package com.bruskajp.fisttablets.Game;

import android.util.Log;

import com.bruskajp.fisttablets.networking.ChatConnection;
import com.bruskajp.fisttablets.networking.GameConnection;
import com.bruskajp.fisttablets.networking.LocalChatConnection;
import com.bruskajp.fisttablets.networking.LocalGameConnection;
import com.bruskajp.fisttablets.player.HumanPlayer;
import com.bruskajp.fisttablets.player.Player;

/**
 * Created by damonster on 11/18/15.
 */

public class LocalGame extends Game{

    GameConnection gameConnection1;
    GameConnection gameConnection2;

    public LocalGame(){
        initializeGame();
    }

    @Override
    void initializeGame(){
        Player player1 = new HumanPlayer(Player.PlayerType.BLACK);
        Player player2 = new HumanPlayer(Player.PlayerType.WHITE);

        gameConnection1 = new LocalGameConnection(player1);
        gameConnection2 = new LocalGameConnection(player2);
        gameConnection1.beginGame();
        gameConnection2.beginGame();

        while(!player1.getWinner() && !player2.getWinner()){
            player1.takeTurn();
            gameConnection1.sendMove(gameConnection2, player1, player2);
            player2.takeTurn();
            gameConnection2.sendMove(gameConnection1, player2, player1);
        }

        Log.i("LocalGame", "The winner is" + player1.getWinner() + "or" + player2.getWinner());

        // Do the ending things

    }

}
