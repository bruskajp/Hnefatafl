package com.bruskajp.fisttablets.Game;

import android.util.Log;

import com.bruskajp.fisttablets.artificialintelligence.ComputerPlayer;
import com.bruskajp.fisttablets.networking.GameConnection;
import com.bruskajp.fisttablets.networking.LocalGameConnection;
import com.bruskajp.fisttablets.player.Player;
import com.bruskajp.fisttablets.userinterface.GameBoard;

/**
 * Created by damonster on 11/18/15.
 */

public class DemoGame extends Game{

    GameConnection gameConnection1;
    GameConnection gameConnection2;
    GameBoard gameBoard;

    public DemoGame(GameBoard gameBoard){
        this.gameBoard = gameBoard;
        initializeGame();
    }

    /***
     * Initializes the DemoGame
     */
    @Override
    void initializeGame() {
        Player player1 = new ComputerPlayer(Player.PlayerType.BLACK);
        Player player2 = new ComputerPlayer(Player.PlayerType.WHITE);
        gameConnection1 = new LocalGameConnection(player1, gameBoard);
        gameConnection2 = new LocalGameConnection(player2, gameBoard);
        gameConnection1.beginGame();
        gameConnection2.beginGame();

        // wait to let the board rendering finish
        synchronized(this){
            try {
                wait(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        while(!player1.isWinner() && !player2.isWinner()){

            player1.takeTurn();
            gameConnection1.sendMove(gameConnection2, player1, player2);
            synchronized (this) {
                try {
                    wait(1500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            if(player1.isWinner() || player2.isWinner()) break;
            player2.takeTurn();
            gameConnection2.sendMove(gameConnection1, player2, player1);
            synchronized (this) {
                try {
                    wait(1500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        if(player1.isWinner()){
            Log.i("SinglePlayerGame", "The winner is " + (player1.getWinnerType() == Player.PlayerType.WHITE ? "WHITE" : "BLACK")
                    + " not " + (player1.getWinnerType() == Player.PlayerType.WHITE ? "BLACK" : "WHITE") );
        }
    }
}
