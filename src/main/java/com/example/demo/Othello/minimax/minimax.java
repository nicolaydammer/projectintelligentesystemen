package com.example.demo.Othello.minimax;

import com.example.demo.Othello.OthelloBoard;

import java.util.List;

public class minimax {

    // Maxixum depth.
    protected int maxDepth = 5;

    protected OthelloBoard copyBoard(OthelloBoard board) {
        OthelloBoard tempBoard = board;
        return tempBoard;
    }

    protected int minimaxDecision(char whoseTurn, OthelloBoard board) {

        // Set bestMove and bestScore.
        int bestMove = -99999;
        int bestScore = Integer.MIN_VALUE;

        // Initialise a new temp board.
        OthelloBoard tempboard = copyBoard(board);

        // For every possible move, set the move and return which is best.
        for (int move: tempboard.listOfAllowedMoves(whoseTurn)) {

            //setMove(tempboard, x, y, whoseTurn);
            tempboard.setMove(move, whoseTurn);
            int searchPly = 1;

            // Get the minMax value.
            int val = minimaxValue(tempboard, whoseTurn, false,  searchPly);

            if (val > bestMove) {
                bestMove = val;
            }

        }

        return bestMove;

    }

    protected int minimaxValue(OthelloBoard board, char whoseTurn, boolean maxingTime, int searchPly) {

        // Geet the bestScore.
        int bestScore = board.getScore(whoseTurn);
        if(searchPly == maxDepth) return bestScore;

        // Initialise variables.
        bestScore = Integer.MAX_VALUE;
        OthelloBoard tempBoard = copyBoard(board);

        // If we need to max, to the max!
        if(maxingTime) {
            for(int move: board.listOfAllowedMoves(whoseTurn)) {
                // ToDo:: make a move function
                tempBoard.setMove(move, whoseTurn);
                int val = minimaxValue(tempBoard, whoseTurn, true, searchPly + 1);
                bestScore = Math.max(val, bestScore);
            }
            // Otherwise min it!
        } else {
            for(int move: board.listOfAllowedMoves(whoseTurn)) {
                // ToDo:: make a move function
                tempBoard.setMove(move, whoseTurn);
                int val = minimaxValue(tempBoard, whoseTurn, true, searchPly + 1);
                bestScore = Math.min(val, bestScore);
            }
        }

        // Aaaand return the besrt score.
        return bestScore;
    }
}
