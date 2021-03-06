package com.bruskajp.fisttablets.artificialintelligence;

import android.util.Log;

import com.bruskajp.fisttablets.gameengine.Board;
import com.bruskajp.fisttablets.gameengine.Token;
import com.bruskajp.fisttablets.gameengine.TokenMovement;
import com.bruskajp.fisttablets.player.Player;

import java.util.List;

/**
 * Created by meliteja on 11/3/15
 */

public class SimpleAI implements ArtificialIntelligence{

    ComputerPlayerOptions cpOptions;
    Board board;
    TokenMovement tokenMovement;
    Player.PlayerType color;
    public static final int SEARCH_DEPTH = 2;
    public static final int SEARCH_WIDTH = 300;
    private final Double INFINITY = Double.POSITIVE_INFINITY;
    private final Double NEGATIVE_INFINITY = Double.NEGATIVE_INFINITY;
    private final int EDGE_INDEX;
    Node<NodeData> root;
    protected class NodeData implements Comparable{
        MovementData movementData;
        double evaluation;
        public NodeData(double evaluation){
            this(evaluation,null);
        }
        public NodeData(double evaluation, MovementData movementData){
            this.evaluation = evaluation;
            this.movementData = movementData;
        }

        @Override
        public int compareTo(Object another) {
            return Double.compare(this.evaluation, ((NodeData)(another)).evaluation);
        }
    }

    public SimpleAI(Board board, TokenMovement tokenMovement, Player.PlayerType color){
        this.board = board;
        this.tokenMovement= tokenMovement;
        this.color = color;
        cpOptions = new ComputerPlayerOptions(board, tokenMovement);
        EDGE_INDEX = board.BOARD_LENGTH-1;
    }

    /***
     * Searches for the next move that the computer should do. NOTE: Computationally expensive.
     * @return A {@code MovementData} that contains information on what move the computer should do next.
     */
    public MovementData getNextMove(){
        return minMaxSearch(SEARCH_DEPTH,SEARCH_WIDTH);
    }

    /***
     * Uses the miniMax algorithm with alpha-beta pruning to decide the next move the computer should do.
     * @param depth The depth to search to.
     * @param width A limit on how wide the tree should be.
     * @return A {@code MovementData} that contains information on what move the computer should do next.
     */
    protected MovementData minMaxSearch(int depth, int width){
        // Initialize root of tree
        root = new Node<>(
                new NodeData(color==Player.PlayerType.BLACK ? NEGATIVE_INFINITY:INFINITY));
        // Build the tree
        buildTree(depth,width,NEGATIVE_INFINITY,INFINITY,color,root);
        // Return a random child (buildTree strips all suboptimal moves from the tree)
        if(root.getChildren().isEmpty()){
            Log.e("SimpleAI","ERROR: children empty");
        }
        // Look for an immediate king win
        if(color== Player.PlayerType.WHITE) {
            for(Node<NodeData> node : root.getChildren()){
                MovementData md = node.getData().movementData;
                if(md.tok.isKing()){
                    int x = md.coordinates.x;
                    int y = md.coordinates.y;
                    if( (x==0 && (y==0||y== EDGE_INDEX))
                            ||(x== EDGE_INDEX&&(y==0||y== EDGE_INDEX))){
                        return md;
                    }
                }
            }
        }
        // Look to see if any pieces can be taken in one move. If so, take the move that takes the most.
        MovementData deletionMove = null;
        int maxDeletions = 0;
        for(Node<NodeData> node : root.getChildren()){
            MovementData md = node.getData().movementData;
            int initPcs = board.getRemainingPieces().size();
            tokenMovement.movePiece(md.tok, md.coordinates.x, md.coordinates.y);
            int finalPcs = board.getRemainingPieces().size();
            tokenMovement.undo();
            if(initPcs - finalPcs > maxDeletions) {
                maxDeletions = initPcs - finalPcs;
                deletionMove = md;
            }

        }
        if(deletionMove != null) return deletionMove;

        return root.getChildren()
                .get((int)(Math.random() * root.getChildren().size()))
                .getData()
                .movementData;
    }

    /***
     * Uses the miniMax algorithm with alpha-beta pruning to build a tree.
     * @param depth The depth to search to.
     * @param width A limit on how wide the tree should be.
     * @param color The color to use in the algorithm to decide if looking for a min or max.
     * @param root The root of the tree.
     * @return A tree whose children contain the optimal move to take.
     */
    private Node<NodeData> buildTree(int depth, int width, double alpha, double beta, Player.PlayerType color, Node<NodeData> root){

        if(depth<0||width<0||board.winner){
            root.getData().evaluation = evaluate(board);
            return root;
        }
        List<MovementData> possibleMoves = cpOptions.getPlayerOptions(color);
        if(possibleMoves.isEmpty()){
            cpOptions.getPlayerOptions(color);
            Log.e("SimpleAI","ERROR: Possible moves is empty");
        }
        // Black wants the highest possible eval
        int addedNodes = 0;
        if(color==Player.PlayerType.BLACK){
            while(!possibleMoves.isEmpty() && addedNodes < width) {
                // Randomly select the next move to evaluate
                MovementData nextMove = possibleMoves.remove((int) (Math.random() * possibleMoves.size()));
                if(tokenMovement.movePiece(nextMove.tok, nextMove.coordinates.x, nextMove.coordinates.y)) {

                    // Recurse
                    Node<NodeData> newNode = buildTree(
                            depth - 1,
                            width,
                            alpha,
                            beta,
                            Player.PlayerType.WHITE,
                            new Node<>(new NodeData(INFINITY, nextMove)));

                    double currentEval = root.getData().evaluation;
                    double newEval = newNode.getData().evaluation;
                    if (currentEval < newEval) {
                        // Trim unnecessary children, add the new child, update alpha
                        root.getChildren().clear();
                        root.addChild(newNode);
                        root.getData().evaluation = newEval;
                        alpha = Math.max(alpha, newEval);
                    } else if (currentEval == newEval) {
                        root.addChild(newNode);
                    }
                    tokenMovement.undo();
                    // Return if beta closes in on alpha
                    if (beta <= alpha) return root;
                    addedNodes++;
                }
                else{
                    Log.e("SimpleAI","Tried an invalid movement from ("+nextMove.tok.getxPosition()+","+nextMove.tok.getyPosition()+
                            ") to ("+nextMove.coordinates.x+","+nextMove.coordinates.y+")");
                }
            }
        }
        else{
            while(!possibleMoves.isEmpty() && addedNodes < width) {
                // Randomly select the next move to evaluate
                MovementData nextMove = possibleMoves.remove((int)(Math.random() * possibleMoves.size()));
                if(tokenMovement.movePiece(nextMove.tok, nextMove.coordinates.x, nextMove.coordinates.y)) {
                    // Recurse
                    Node<NodeData> newNode = buildTree(
                            depth - 1,
                            width,
                            alpha,
                            beta,
                            Player.PlayerType.BLACK,
                            new Node<NodeData>(new NodeData(NEGATIVE_INFINITY, nextMove)));
                    double currentEval = root.getData().evaluation;
                    double newEval = newNode.getData().evaluation;
                    if (currentEval > newEval) {
                        // Trim unnecessary children, add the new child, update beta
                        root.getChildren().clear();
                        root.addChild(newNode);
                        root.getData().evaluation = newEval;
                        beta = Math.min(beta, newEval);
                    } else if (currentEval == newEval) {
                        root.addChild(newNode);
                    }
                    tokenMovement.undo();
                    // Return if beta closes in on alpha
                    if (beta <= alpha) return root;
                    addedNodes++;
                }
                else{
                    Log.e("SimpleAI","Doing an invalid movement from ("+nextMove.tok.getxPosition()+","+nextMove.tok.getyPosition()+
                            ") to ("+nextMove.coordinates.x+","+nextMove.coordinates.y+")");
                }
            }
        }
        return root;
    }
    /***
     * Evaluates the state of the board using an evaluation function of
     * Value(Board) = (blackCaptures-whiteCaptures)+.1*manhattanDistanceOfKing.
     * Higher values are good for black, lower values are good for white.
     * @param board The {@code Board} to apply the evaluation function on.
     * @return The value of the evaluation function applied to {@code board}.
     */
    protected double evaluate(Board board){
        int blackPieces = 0;
        int whitePieces = 0;
        int kingDistance = 0;
        for(Token t : board.getRemainingPieces()){
            if(t.getColor() == Token.TokenType.WHITE){
                whitePieces++;
            } else{
                blackPieces++;
            }
            if(t.isKing()){
                kingDistance = findManhattanDistance(board,t);
            }
        }

        return -(Board.INITIAL_NUMBER_OF_BLACK_TOKENS-blackPieces)+
                (Board.INITIAL_NUMBER_OF_WHITE_TOKENS - whitePieces)+
                .1*(kingDistance == 0 ? -1000 : kingDistance); // Make the king distance arbitrarily small to indicate that white will win
    }

    /***
     * Finds the manhattan distance between the {@code king} and the closest corner of the {@code board}.
     * Does not take into account tokens that may be in the way.
     * @param board The board that {@code king} is on.
     * @param king The {@code token} to find the manhattan distance to the corner, presumably the king.
     * @return The manhattan distance from {@code king} to the closest corner of {@code board}.
     */
    protected int findManhattanDistance(Board board, Token king){
        int xPos = king.getxPosition();
        int yPos = king.getyPosition();
        int boardDimension = Board.BOARD_LENGTH;
        return ((xPos>(boardDimension/2)) ? boardDimension-xPos : xPos )
                + ((yPos>(boardDimension/2)) ? boardDimension-yPos : yPos);
    }

}