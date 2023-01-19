package com.example.demo.Othello.minimax;

import com.example.demo.Othello.OthelloBoard;
import java.util.List;

public class minimax extends OthelloBoard {

    protected  OthelloBoard board;

    public minimax(OthelloBoard board) {
        this.board = new OthelloBoard();
    }

    protected int heuristic(OthelloBoard board, char whoseTurn) {
        char opponent = 'B';
        if (whoseTurn == 'B')
            opponent ='W';
        int myScore = calcScore(board, whoseTurn);
        int opponentScore = calcScore(board, opponent);
        return (myScore - opponentScore);
    }

    protected OthelloBoard copyBoard() {
        OthelloBoard tempBoard = this.board;
        return tempBoard;
    }

    protected void minimaxDecision(char whoseTurn, int x, int y) {

        // PSEUDO VAR
        int numMoves = 5;

        // Check who is your opponent.
        char opponent = 'B';
        if (whoseTurn == 'B')
            opponent = 'W';

        // Get the list of allowed moves.
        List move = listOfAllowedMoves(whoseTurn, x, y);

        // DOESNT WORK!!
        if (move.get(0).equals(0)) {
            x = -1;
            y = -1;
        }

         else {
             int bestMove = -99999;
             int bestX = x;
             int bestY = y;

             for (int i = 0; i < numMoves; i++) {
                 OthelloBoard tempboard = copyBoard();

                 // ToDo:: make a move function
                 setMove(tempboard, x, y, whoseTurn);
                 int val = minimaxValue(tempboard, whoseTurn, opponent, 1);

                 if (val > bestMove) {
                     bestMove = val;
                     bestX = x;
                     bestY = y;
                 }

                 y = bestY;
                 x = bestX;
             }

        }

    }

    protected int minimaxValue(OthelloBoard board, char originalTurn, char currentTurn, int searchPly) {
        if (searchPly == 5) {
            return heuristic(board, originalTurn);
        }

        char opponent = 'B';
        if (currentTurn == 'B')
            opponent = 'W';

        // PSEUDO VARS
        int numMoves = 5;
        int x = 0;

        if (numMoves == 0) {
            return minimaxValue(board, originalTurn, opponent, searchPly +1);
        } else {

            int bestMove = -99999; // for finding max
            if (originalTurn != currentTurn)
                bestMove = 99999; // finding min

            for (int i = 0; i < numMoves; i++) {
                OthelloBoard tempboard = copyBoard();

                // ToDo:: make a move function
                // makeMove(tempboard, x, y, whoseTurn)
                int val = minimaxValue(tempboard, originalTurn, opponent, 1);

                if (originalTurn == currentTurn) {
                    if (val > bestMove) {
                        bestMove = val;
                    } else {
                        if (val < bestMove) {
                            bestMove = val;
                        }
                    }
                }
                return bestMove;
            }
        }
        return -1; // should never get here.
    }
}
