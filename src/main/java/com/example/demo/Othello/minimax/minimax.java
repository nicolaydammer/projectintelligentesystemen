package com.example.demo.Othello.minimax;

import com.example.demo.Othello.OthelloBoard;

import java.util.List;

public class minimax {

    // Maxixum depth. //ToDo:: Perhaps include this in SharedData so we can let the user choose the max depth?
    protected int maxDepth = 5;

    /**
     * Copies the board, so we dont actually do this on an active playboard.
     * @param board
     * @return OthelloBoard
     */
    protected OthelloBoard copyBoard(OthelloBoard board) {
        OthelloBoard tempBoard = board;
        return tempBoard;
    }

    /**
     * Function that returns the best possible move, by help of a MinMax function.
     * @param whoseTurn = Black or White
     * @param board = The current board.
     * @return the best possible move.
     */
    protected int minimaxDecision(char whoseTurn, OthelloBoard board) {

        // Set bestMove and bestScore.
        int bestMove = -99999;
        int bestScore = Integer.MIN_VALUE;

        // Initialise a new temp board.
        OthelloBoard tempboard = copyBoard(board);

        //Todo:: Move needs to contain [allowedMove, allowedMove, allowedMove];
        int[] move = board.listOfAllowedMoves(whoseTurn);

        // For every possible move, set the move and return which is best.
        for(int i = 0; i < move.length; i++) {

            // Place move on this temp board.
            tempboard.setMove(move[i], whoseTurn);
            int searchPly = 1;

            // Get the minMax value.
            int val = minimaxValue(tempboard, whoseTurn, false,  searchPly);

            // If this value is higher than the best move, this new value is our new bestMove.
            if (val > bestMove) {
                bestMove = val;
            }

        }

        return bestMove;

    }

    /**
     * Function that returns the best score calculated by the MinMax Algorithm.
     * @param board = the board
     * @param whoseTurn = Black or WHite
     * @param maxingTime = If we need to find the MAX value
     * @param searchPly = How deep we are currently
     * @return the best possible score.
     */
    protected int minimaxValue(OthelloBoard board, char whoseTurn, boolean maxingTime, int searchPly) {

        // Geet the bestScore.
        int bestScore = board.getScore(whoseTurn);
        if(searchPly == maxDepth) return bestScore;

        // Initialise variables.
        bestScore = Integer.MAX_VALUE;
        OthelloBoard tempBoard = copyBoard(board);

        // ToDo:: Move needs to contain [allowedMove, allowedMove, allowedMove];
        int[] move = board.listOfAllowedMoves(whoseTurn);

        // If there are no allowed moves, return the original best turn.
        // ToDo:: If board.listOfAllowedMoves contain no allowed moves, the the first element to moves[0] == -1.
        if (move[0] == -1) return bestScore;

        // If we need to max, to the max!
        if(maxingTime) {
            for(int i = 0; i < move.length; i++) {
                // set the move on our temporarily board.
                tempBoard.setMove(move[i], whoseTurn);

                // now we need to find the min value
                int val = minimaxValue(tempBoard, whoseTurn, false, searchPly + 1);

                // Get the best maximum outcome. That is our new best score.
                bestScore = Math.max(val, bestScore);
            }
            // Otherwise min it!
        } else {
            for(int i = 0; i < move.length; i++) {
                // Set the move on our temporarily board
                tempBoard.setMove(move[i], whoseTurn);

                // Find the new value on the maximum outcome,
                int val = minimaxValue(tempBoard, whoseTurn, true, searchPly + 1);

                // Get the best minimum outcome, that is our new best score.
                bestScore = Math.min(val, bestScore);
            }
        }

        // Aaaand return the best score.
        return bestScore;
    }
}
