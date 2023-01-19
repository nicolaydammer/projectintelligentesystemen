package com.example.demo.Othello;

import com.example.demo.board.Board;
import com.example.demo.board.Stones;

import java.util.ArrayList;
import java.util.List;

public class OthelloBoard extends Board {
    private char black = 'B';
    private char white = 'W';

    private int score;

    private int size = 64;

    public OthelloBoard() {
        super(8);
        setStones();
    }

    public int calcScore(char WhoseTurn) {
        Stones[][] board = super.getBoard();
        for(int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if(board[i][j].getValue() == WhoseTurn){
                    score++;
                }
            }
        }
        return score;
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

        List<Integer> moves = listOfAllowedMoves(stone);

        for (int k = 0; k < moves.size(); k++) {
            if(moves.get(k) == index ){
                return true;
            }
        }
        return false;
    }

    public void setMove(OthelloBoard board, int i, int j, char character) {
        Stones[][] Board = board.getBoard();
        if(allowedMove(i, j, character))
        for(int x = 0; x<this.size; x++) {
            if(i==x)
            {
                // ???
                for (int y = 0; y < this.size;y++){
                    if(j == y){
                        Board[i][j].setValue(character);
                    }
                }
            }
        }
    }

    public void resetBoard() {
        initialization();
        setStones();
    }

    public List<Integer> listOfAllowedMoves(char stoneChar) { // needs your character ('B' or 'W')
        Stones[][] board = super.getBoard();
        List<Integer> moves = new ArrayList<>();

        for(int i = 0; i < board.length; i++) {
            for(int j = 0; j < board[i].length; j++) {
                if (checkEmptyStone(board[i][j])) {
                    if(!checkSurroundingIsEmpty(i, j, board)) {
                        if(!giveBracketForPosition(i, j, stoneChar, board).isEmpty()){
                            if(checkSurroundingHasOpponent(i, j, board, stoneChar)) {
                                moves.add(getIndex(i, j));
                            }
                        }
                    }
                }
            }
        }
        return moves;
    }

    private List giveBracketForPosition(int i, int j, char stoneChar, Stones[][] board){
        List bracketPosition = new ArrayList<>();
        List leftSide = makeBracket(i, j - 1, 0, -1, stoneChar, board);
        if (!leftSide.isEmpty()) {
            bracketPosition.add(leftSide);
        }
        List rightSide = makeBracket(i, j + 1, 0, 1, stoneChar,board);
        if (!rightSide.isEmpty()) {
            bracketPosition.add(leftSide);
        }
        List upSide = makeBracket(i - 1, j, -1, 0, stoneChar, board);
        if (!upSide.isEmpty()) {
            bracketPosition.add(leftSide);
        }
        List downSide = makeBracket(i + 1, j, 1, 0, stoneChar, board);
        if (!downSide.isEmpty()) {
            bracketPosition.add(leftSide);
        }
        List rightUpDiagonal = makeBracket(i - 1, j + 1, -1, 1, stoneChar, board);
        if (!rightUpDiagonal.isEmpty()) {
            bracketPosition.add(leftSide);
        }
        List rightDownDiagonal = makeBracket(i + 1, j + 1, 1, 1, stoneChar, board);
        if (!rightDownDiagonal.isEmpty()) {
            bracketPosition.add(leftSide);
        }
        List leftDownDiagonal = makeBracket(i + 1, j - 1, 1, -1, stoneChar, board);
        if (!leftDownDiagonal.isEmpty()) {
            bracketPosition.add(leftSide);
        }
        List leftUpDiagonal = makeBracket(i - 1, j - 1, -1, -1, stoneChar, board);
        if (!leftUpDiagonal.isEmpty()) {
            bracketPosition.add(leftSide);
        }
        if (!bracketPosition.isEmpty()) {
            return null;
        }
        return bracketPosition;
    }


    private List makeBracket(int i, int j, int iNext, int jNext, char stoneChar, Stones[][] board) {
        List bracket = new ArrayList<>();
        while(isOnBoard(i, j) &! checkStoneIsOpponent(board[i][j], stoneChar) &! checkEmptyStone(board[i][j])){
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
        if(i > 7 || i<0 || j > 7 || j<0){
            return false;
        }
        return true;
    }

    public boolean checkStoneIsOpponent(Stones stone, char me){
        if(stone.getValue() == me){
            return false;
        }
        return true;
    }

    private boolean checkSurroundingHasOpponent(int i, int j, Stones[][] board, char me) {
        //northeast
        if(!checkEmptyStone(board[i+1][j+1]) && (isOnBoard(i+1, j+1) && checkStoneIsOpponent(board[i+1][j+1], me))){
            return true;
        }
        //east
        if(!checkEmptyStone(board[i][j+1]) && (isOnBoard(i, j+1) && checkStoneIsOpponent(board[i][j+1], me))){
            return true;
        }

        //north
        if(!checkEmptyStone(board[i+1][j]) && (isOnBoard(i+1, j) && checkStoneIsOpponent(board[i+1][j], me))){
            return true;
        }
        //northwest
        if(!checkEmptyStone(board[i+1][j-1]) && (isOnBoard(i+1, j-1) && checkStoneIsOpponent(board[i+1][j-1], me))){
            return true;
        }
        //west
        if(!checkEmptyStone(board[i][j-1]) && (isOnBoard(i, j-1) && checkStoneIsOpponent(board[i][j-1], me))){
            return true;
        }
        //southwest
        if(!checkEmptyStone(board[i-1][j-1]) && (isOnBoard(i-1, j-1) && checkStoneIsOpponent(board[i-1][j-1], me))){
            return true;
        }
        //south
        if(!checkEmptyStone(board[i-1][j]) && (isOnBoard(i-1, j) && checkStoneIsOpponent(board[i-1][j], me))){
            return true;
        }
        //southeast
        if(!checkEmptyStone(board[i-1][j+1]) && (isOnBoard(i-1, j+1) && checkStoneIsOpponent(board[i-1][j+1], me))){
            return true;
        }
        return false;
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
