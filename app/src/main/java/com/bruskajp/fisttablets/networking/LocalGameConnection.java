package com.bruskajp.fisttablets.networking;

import android.util.Log;

import com.bruskajp.fisttablets.gameengine.Move;
import com.bruskajp.fisttablets.gameengine.Token;
import com.bruskajp.fisttablets.player.HumanPlayer;
import com.bruskajp.fisttablets.player.Player;
import com.bruskajp.fisttablets.userinterface.GameBoard;

/**
 * Created by damonster on 11/18/15.
 */
public class LocalGameConnection extends GameConnection{


    GameBoard gameBoard ;
    public LocalGameConnection(Player player, GameBoard gameBoard) {
        super(player);
        this.gameBoard = gameBoard;
    }

    public void sendMove(GameConnection gameConnection, Player player1, Player player2) {
        Move lastMove = player1.getLastMove();
        Log.i("LocalGameConnection", " " + lastMove.getPreviousX() + " " + lastMove.getPreviousY() + " " + lastMove.getNewX() + " " + lastMove.getNewY());
        gameBoard.movePieceComputer(lastMove.getPreviousX(), lastMove.getPreviousY(), lastMove.getNewX(), lastMove.getNewY(),lastMove.getDeletedTokens());
        Log.i("LocalGameConnection", " " + lastMove.getPreviousX() + " " + lastMove.getPreviousY() + " " + lastMove.getNewX() + " " + lastMove.getNewY());

        gameConnection.receiveMove(player1.getLastMove(), player2);
    }

    public void receiveMove(Move lastMove, Player player) {
        player.movePiece(lastMove.getPreviousX(), lastMove.getPreviousY(), lastMove.getNewX(), lastMove.getNewY()); //can use null because it is not used
        gameBoard.movePieceComputer(lastMove.getPreviousX(), lastMove.getPreviousY(), lastMove.getNewX(), lastMove.getNewY(),lastMove.getDeletedTokens());

        // add a thing in move that stores the piece number that matches Davids
        // this also means adding a piece number to the token
    }
}
