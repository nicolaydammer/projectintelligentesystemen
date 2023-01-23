package com.example.demo.Othello;

import com.example.demo.board.Board;
import com.example.demo.board.Stones;

import java.util.ArrayList;
import java.util.List;

public class OthelloBoard extends Board {
    private char black = 'B';
    private char white = 'W';

    private int whiteScore = 2;
    private int blackScore = 2;

    private int size = 64;

    public OthelloBoard() {
        super(8);
        setStones();
    }

    public void calcBlackScore() {
        Stones[][] board = super.getBoard();
        int score = 0;
        for(int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if(board[i][j].getValue() == black){
                    score++;
                }
            }
        }
        blackScore = score;
    }

   public int getScore(char whoseTurn) {
        if (whoseTurn == 'O') return this.getBlackScore();
        return this.getWhiteScore();
   }

    public int getBlackScore(){
        return blackScore;
    }

    public int getWhiteScore() {
        return whiteScore;
    }

    public void calcWhiteScore() {
        Stones[][] board = super.getBoard();
        int score = 0;
        for(int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if(board[i][j].getValue() == white){
                    score++;
                }
            }
        }
        whiteScore = score;
    }

    public void setStones() {
        Stones[][] board = super.getBoard();
        board[3][3].setValue(white);
        board[3][4].setValue(black);
        board[4][3].setValue(black);
        board[4][4].setValue(white);
    }

    @Override
    protected boolean allowedMove(int i, int j, char stone) {
        int index =  getIndex(i, j);

        List move = listOfAllowedMoves(stone, i, j);

        if(move.get(0).equals(index)){
            return true;
        }
        return false;
    }


    /**
     * Sets a move on the board.
     * @param move = int
     * @param whoseTurn = the character (black/white)
     */
    public void setMove(int move, char whoseTurn) {
        // get the board.
        Stones[][] board = this.getBoard();

        // Get the position of the move. if index  (i * j) == move, place the character.
        for (int i  = 0; i < this.size; i++) {
            for (int j = 0; j < this.size; j++) {
                if ( i*j == move) board[i][j].setValue(whoseTurn); return;
            }
        }
    }

    public void resetBoard() {
        initialization();
        setStones();
    }

    public List listOfAllowedMoves(char stoneChar, int i, int j) { // needs your character ('B' or 'W')
        Stones[][] board = super.getBoard();
        List move = new ArrayList<>();

//        for(int i = 0; i < board.length; i++) {
//            for(int j = 0; j < board[i].length; j++) {
//                List possibleMove = new ArrayList<>();
//                //check surrouding when i = 1, j = 2
                if (checkEmptyStone(board[i][j])) {
                    if(checkSurroundingIsEmpty(i, j, board)) {}
                    else{
                        if(checkSurroundingIsMe(i, j, board, stoneChar)) {}
                        else {
                            move.add(getIndex(i, j));
                            List leftSide = makeBracket(i, j - 1, 0, -1, stoneChar, board);
                            if (!leftSide.isEmpty()) {
                                move.add(leftSide);
                            }
                            List rightSide = makeBracket(i, j + 1, 0, 1, stoneChar,board);
                            if (!rightSide.isEmpty()) {
                                move.add(leftSide);
                            }
                            List upSide = makeBracket(i - 1, j, -1, 0, stoneChar, board);
                            if (!upSide.isEmpty()) {
                                move.add(leftSide);
                            }
                            List downSide = makeBracket(i + 1, j, 1, 0, stoneChar, board);
                            if (!downSide.isEmpty()) {
                                move.add(leftSide);
                            }
                            List rightUpDiagonal = makeBracket(i - 1, j + 1, -1, 1, stoneChar, board);
                            if (!rightUpDiagonal.isEmpty()) {
                                move.add(leftSide);
                            }
                            List rightDownDiagonal = makeBracket(i + 1, j + 1, 1, 1, stoneChar, board);
                            if (!rightDownDiagonal.isEmpty()) {
                                move.add(leftSide);
                            }
                            List leftDownDiagonal = makeBracket(i + 1, j - 1, 1, -1, stoneChar, board);
                            if (!leftDownDiagonal.isEmpty()) {
                                move.add(leftSide);
                            }
                            List leftUpDiagonal = makeBracket(i - 1, j - 1, -1, -1, stoneChar, board);
                            if (!leftUpDiagonal.isEmpty()) {
                                move.add(leftSide);
                            }
                            if (!move.isEmpty()) {
                                return null;
                            }
                        }
                    }
                }
//            }
//        }
        return move;
    }


    private List makeBracket(int i, int j, int iNext, int jNext, char stoneChar, Stones[][] board) {
        List bracket = new ArrayList<>();
        while(isOnBoard(i, j) &! checkStoneIsMe(board[i][j], stoneChar) &! checkEmptyStone(board[i][j])){
            bracket.add(getIndex(i, j));
            i = i + iNext;
            j = j + jNext;
        }
        if(checkEmptyStone(board[i][j])){
            return null;
        }
        if(!isOnBoard(i, j)){
            return null;
        }
        return bracket;
    }


    public boolean isOnBoard(int i, int j){
        if(i >= 8 || i<0 || j >= 8 || j<0){
            return false;
        }
        return true;
    }

    public boolean checkStoneIsMe(Stones stone, char me){
        if(stone.getValue() == me){
            return true;
        }
        return false;
    }

    private boolean checkSurroundingIsMe(int i, int j, Stones[][] board, char me) {
        //northeast
        if(isOnBoard(i+1, j+1) &! checkStoneIsMe(board[i+1][j+1], me)){
            return false;
        }
        //east
        if(isOnBoard(i, j+1) &! checkStoneIsMe(board[i][j+1], me)){
            return false;
        }
        //north
        if(isOnBoard(i+1, j) &! checkStoneIsMe(board[i+1][j], me)){
            return false;
        }
        //northwest
        if(isOnBoard(i+1, j-1) &! checkStoneIsMe(board[i+1][j-1], me)){
            return false;
        }
        //west
        if(isOnBoard(i, j-1) &! checkStoneIsMe(board[i][j-1], me)){
            return false;
        }
        //southwest
        if(isOnBoard(i-1, j-1) &! checkStoneIsMe(board[i-1][j-1], me)){
            return false;
        }
        //south
        if(isOnBoard(i-1, j) &! checkStoneIsMe(board[i-1][j], me)){
            return false;
        }
        //southeast
        if(isOnBoard(i-1, j+1) &! checkStoneIsMe(board[i-1][j+1], me)){
            return false;
        }
        return true;
    }

    public boolean checkEmptyStone(Stones stone){
        // stone is empty return true
        if(stone.getValue() == ' '){
            return true;
        }
        // if stone is not empty return false
        return false;
    }

    private boolean checkSurroundingIsEmpty(int i, int j, Stones[][] board) {
        //northeast
        if(isOnBoard(i+1, j+1) &! checkEmptyStone(board[i+1][j+1])){
            return false;
        }
        //east
        if(isOnBoard(i, j+1) &! checkEmptyStone(board[i][j+1])){
            return false;
        }
        //north
        if(isOnBoard(i+1, j) &! checkEmptyStone(board[i+1][j])){
            return false;
        }
        //northwest
        if(isOnBoard(i+1, j-1) &! checkEmptyStone(board[i+1][j-1])){
            return false;
        }
        //west
        if(isOnBoard(i, j-1) &! checkEmptyStone(board[i][j-1])){
            return false;
        }
        //southwest
        if(isOnBoard(i-1, j-1) &! checkEmptyStone(board[i-1][j-1])){
            return false;
        }
        //south
        if(isOnBoard(i-1, j) &! checkEmptyStone(board[i-1][j])){
            return false;
        }
        //southeast
        if(isOnBoard(i-1, j+1) &! checkEmptyStone(board[i-1][j+1])){
            return false;
        }
        return true;
    }

    public int getIndex(int i, int j){
        return i*8 + j;
    }


}
