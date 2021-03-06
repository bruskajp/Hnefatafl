package com.bruskajp.fisttablets.Game;

import android.util.Log;

import com.bruskajp.fisttablets.artificialintelligence.ComputerPlayer;
import com.bruskajp.fisttablets.networking.GameConnection;
import com.bruskajp.fisttablets.networking.LocalGameConnection;
import com.bruskajp.fisttablets.player.HumanPlayer;
import com.bruskajp.fisttablets.player.Player;
import com.bruskajp.fisttablets.userinterface.GameBoard;

/**
 * Created by damonster on 11/18/15.
 */

public class SinglePlayerGame extends Game{

    GameConnection gameConnection1;
    GameConnection gameConnection2;
    GameBoard gameBoard;
    Player player1;
    Player player2;
    int gameBoardLastXPos = -1;
    int gameBoardLastYPos = -1;
    int hackFix = 0;


    public SinglePlayerGame(GameBoard gameBoard){
        this.gameBoard = gameBoard;
        initializeGame();
    }

    /***
     * Initializes the SinglePlayerGame
     */
    @Override
    void initializeGame() {
        // Case where the person picks a white player.
        if(gameBoard.playerColor.equals("white")){
            player1 = new ComputerPlayer(Player.PlayerType.BLACK);
            player2 = new HumanPlayer(Player.PlayerType.WHITE);

            gameConnection1 = new LocalGameConnection(player1, gameBoard);
            gameConnection2 = new LocalGameConnection(player2, gameBoard);
            gameConnection1.beginGame();
            gameConnection2.beginGame();

            while(!player1.isWinner() && !player2.isWinner()){
                if(player1.isWinner() || player2.isWinner()) break;
                player1.takeTurn();
                gameConnection1.sendMove(gameConnection2, player1, player2);
                synchronized (this) {
                    try {
                        wait(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                if(player1.isWinner() || player2.isWinner()) break;

                player2.takeTurn();
                do{
                    gameBoard.whiteTurn = true;
                    gameBoard.newXPos = gameBoardLastXPos;
                    gameBoard.newYPos = gameBoardLastYPos;
                    while(gameBoard.newXPos == gameBoardLastXPos && gameBoard.newYPos == gameBoardLastYPos){
                        synchronized (this){
                            try {
                                wait(50);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                    Log.e("SinglePlayerGame", gameBoard.newXPos + "  " + gameBoard.lastXPos + "  " + gameBoard.newYPos + "  " + gameBoard.lastYPos + "\n");
                    /*if(hackFix == 0){
                        ++hackFix;
                    } else {
                        ++gameBoard.lastYPos;
                    }*/
                }while(!player2.moveHumanPiece(gameBoard.lastXPos, gameBoard.lastYPos, gameBoard.newXPos, gameBoard.newYPos));
                Log.e("SinglePlayerGame", gameBoard.newXPos + "  " + gameBoard.newYPos + "\n");
                gameBoardLastXPos = gameBoard.newXPos;
                gameBoardLastYPos = gameBoard.newYPos;
                gameBoard.lastXPos = gameBoard.newXPos;
                gameBoard.lastYPos = gameBoard.newYPos;
                player2.finishTurn();
                Log.e("SinglePlayerGame", "check 2 \n");
                gameConnection2.sendMove(gameConnection1, player2, player1);
                Log.e("SinglePlayerGame", "check 3 \n");
            }
        // Case where the person picks a black player.
        } else {
            player1 = new ComputerPlayer(Player.PlayerType.WHITE);
            player2 = new HumanPlayer(Player.PlayerType.BLACK);

            gameConnection1 = new LocalGameConnection(player1, gameBoard);
            gameConnection2 = new LocalGameConnection(player2, gameBoard);
            gameConnection1.beginGame();
            gameConnection2.beginGame();

            while(!player1.isWinner() && !player2.isWinner()){
                if(player1.isWinner() || player2.isWinner()) break;

                player2.takeTurn();
                do{
                    gameBoard.blackTurn = true;
                    gameBoard.newXPos = gameBoardLastXPos;
                    gameBoard.newYPos = gameBoardLastYPos;
                    while(gameBoard.newXPos == gameBoardLastXPos && gameBoard.newYPos == gameBoardLastYPos){
                        synchronized (this){
                            try {
                                wait(50);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }while(!player2.moveHumanPiece(gameBoard.lastXPos, gameBoard.lastYPos, gameBoard.newXPos, gameBoard.newYPos));
                gameBoardLastXPos = gameBoard.newXPos;
                gameBoardLastYPos = gameBoard.newYPos;
                gameBoard.lastXPos = gameBoard.newXPos;
                gameBoard.lastYPos = gameBoard.newYPos;
                player2.finishTurn();
                gameConnection2.sendMove(gameConnection1, player2, player1);

                if(player1.isWinner() || player2.isWinner()) break;
                player1.takeTurn();
                gameConnection1.sendMove(gameConnection2, player1, player2);
                synchronized (this) {
                    try {
                        wait(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        if(player1.isWinner()){
            Log.i("SinglePlayerGame", "The winner is " + (player1.getWinnerType() == Player.PlayerType.WHITE ? "WHITE" : "BLACK")
                    + " not " + (player1.getWinnerType() == Player.PlayerType.WHITE ? "BLACK" : "WHITE") );
        }
    }
}
