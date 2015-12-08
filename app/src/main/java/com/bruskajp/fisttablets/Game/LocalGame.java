package com.bruskajp.fisttablets.Game;

import android.util.Log;

import com.bruskajp.fisttablets.networking.GameConnection;
import com.bruskajp.fisttablets.networking.LocalGameConnection;
import com.bruskajp.fisttablets.player.HumanPlayer;
import com.bruskajp.fisttablets.player.Player;
import com.bruskajp.fisttablets.userinterface.GameBoard;

/**
 * Created by damonster on 11/18/15.
 */

public class LocalGame extends Game{

    GameConnection gameConnection1;
    GameConnection gameConnection2;
    GameBoard gameBoard;

    public Player player1;
    public Player player2;

    public LocalGame(GameBoard gameBoard){
        this.gameBoard = gameBoard;
        initializeGame();
    }

    @Override
    void initializeGame(){
        player1 = new HumanPlayer(Player.PlayerType.BLACK);
        player2 = new HumanPlayer(Player.PlayerType.WHITE);

        //gameConnection1 = new LocalGameConnection(player1);
        //gameConnection2 = new LocalGameConnection(player2);
        gameConnection1.beginGame();
        gameConnection2.beginGame();

        while(!player1.isWinner() && !player2.isWinner()){
            player1.takeTurn();
            gameConnection1.sendMove(gameConnection2, player1, player2);
            player2.takeTurn();
            gameConnection2.sendMove(gameConnection1, player2, player1);
        }

        Log.i("LocalGame", "The winner is" + player1.isWinner() + "or" + player2.isWinner());

        // Do the ending things

    }

}
