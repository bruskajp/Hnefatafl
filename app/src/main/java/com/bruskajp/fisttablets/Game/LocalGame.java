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
    int gameBoardLastXPosBlack = -1;
    int gameBoardLastYPosBlack = -1;
    int gameBoardLastXPosWhite = -1;
    int gameBoardLastYPosWhite = -1;

    public LocalGame(GameBoard gameBoard){
        this.gameBoard = gameBoard;
        initializeGame();
    }

    @Override
    void initializeGame(){
        player1 = new HumanPlayer(Player.PlayerType.BLACK);
        player2 = new HumanPlayer(Player.PlayerType.WHITE);

        gameConnection1 = new LocalGameConnection(player1, gameBoard);
        gameConnection2 = new LocalGameConnection(player2, gameBoard);
        gameConnection1.beginGame();
        gameConnection2.beginGame();

        while(!player1.isWinner() && !player2.isWinner()){
            if(player1.isWinner() || player2.isWinner()) break;
            player1.takeTurn();
            do{
                gameBoard.blackTurn = true;
                gameBoard.newXPos = gameBoardLastXPosBlack;
                gameBoard.newYPos = gameBoardLastYPosBlack;
                while(gameBoard.newXPos == gameBoardLastXPosBlack && gameBoard.newYPos == gameBoardLastYPosBlack){}
                Log.e("SinglePlayerGame", gameBoard.newXPos + "  " + gameBoard.lastXPos + "  " + gameBoard.newYPos + "  " + gameBoard.lastYPos + "\n");
            }while(!player1.movePiece(gameBoard.lastXPos, gameBoard.lastYPos, gameBoard.newXPos, gameBoard.newYPos));
            Log.e("SinglePlayerGame", gameBoard.newXPos + "  " + gameBoard.newYPos + "\n");
            gameBoardLastXPosBlack = gameBoard.newXPos;
            gameBoardLastYPosBlack = gameBoard.newYPos;
            gameBoard.lastXPos = gameBoard.newXPos;
            gameBoard.lastYPos = gameBoard.newYPos;
            player1.finishTurn();
            Log.e("SinglePlayerGame", "check 2 \n");
            gameConnection2.sendMove(gameConnection2, player1, player2);
            Log.e("SinglePlayerGame", "check 3 \n");

            if(player1.isWinner() || player2.isWinner()) break;

            player2.takeTurn();
            do{
                gameBoard.whiteTurn = true;
                gameBoard.newXPos = gameBoardLastXPosWhite;
                gameBoard.newYPos = gameBoardLastYPosWhite;
                while(gameBoard.newXPos == gameBoardLastXPosWhite && gameBoard.newYPos == gameBoardLastYPosWhite){}
                Log.e("SinglePlayerGame", gameBoard.newXPos + "  " + gameBoard.lastXPos + "  " + gameBoard.newYPos + "  " + gameBoard.lastYPos + "\n");
            }while(!player2.movePiece(gameBoard.lastXPos, gameBoard.lastYPos, gameBoard.newXPos, gameBoard.newYPos));
            Log.e("SinglePlayerGame", gameBoard.newXPos + "  " + gameBoard.newYPos + "\n");
            gameBoardLastXPosWhite = gameBoard.newXPos;
            gameBoardLastYPosWhite = gameBoard.newYPos;
            gameBoard.lastXPos = gameBoard.newXPos;
            gameBoard.lastYPos = gameBoard.newYPos;
            player2.finishTurn();
            Log.e("SinglePlayerGame", "check 2 \n");
            gameConnection2.sendMove(gameConnection1, player2, player1);
            Log.e("SinglePlayerGame", "check 3 \n");
        }

        Log.i("LocalGame", "The winner is" + player1.isWinner() + "or" + player2.isWinner());

        // Do the ending things

    }

}
