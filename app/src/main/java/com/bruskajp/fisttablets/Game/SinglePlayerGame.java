package com.bruskajp.fisttablets.Game;

import android.util.Log;

import com.bruskajp.fisttablets.artificialintelligence.ComputerPlayer;
import com.bruskajp.fisttablets.networking.ChatConnection;
import com.bruskajp.fisttablets.networking.GameConnection;
import com.bruskajp.fisttablets.networking.LocalGameConnection;
import com.bruskajp.fisttablets.networking.SinglePlayerChatConnection;
import com.bruskajp.fisttablets.networking.SinglePlayerGameConnection;
import com.bruskajp.fisttablets.player.Player;

/**
 * Created by damonster on 11/18/15.
 */

public class SinglePlayerGame extends Game{

    GameConnection gameConnection1;
    GameConnection gameConnection2;

    public SinglePlayerGame(){
        initializeGame();
    }

    @Override
    void initializeGame() {
        Player player1 = new ComputerPlayer(Player.PlayerType.BLACK);
        Player player2 = new ComputerPlayer(Player.PlayerType.WHITE);
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

        // TODO: Fix this

        if(player1.getWinner()){
            Log.i("SinglePlayerGame", "The winner is " + player1.getPlayerType() + " not " + player2.getPlayerType() );
        }else{
            Log.i("SinglePlayerGame", "The winner is " + player2.getPlayerType() + " not " + player1.getPlayerType() );
        }


        // Do winning stuff
    }
}
