package com.example.demo.Othello;

import com.example.demo.Board;
import com.example.demo.Stones;

import java.util.ArrayList;
import java.util.List;

public class OthelloBoard extends Board {
    private char black = 'B';
    private char white = 'W';

    private int whiteScore = 2;
    private int blackScore = 2;

    public OthelloBoard() {
        super(8);
        setStones();
    }
    //todo make method getscore

    public void setStones() {
        Stones[][] board = super.getBoard();
        board[3][3].setValue(white);
        board[3][4].setValue(black);
        board[4][3].setValue(black);
        board[4][4].setValue(white);
    }

    @Override
    protected boolean allowedMove(int i, int j) {
        return false;
    }

    public void resetBoard() {
        initialization();
        setStones();
    }


    public List<Integer> listAllowedMoves(char stoneChar) { // needs your character ('B' or 'W')
        List<Integer> moves = new ArrayList<>();
        Stones[][] board = super.getBoard();

        int count = 0;
        for(int i = 0; i < board.length; i++) {
            List stone = new ArrayList<>();
            for(int j = 0; j < board[i].length; j++) {
                List bracket =  new ArrayList<>();
                board[i][j].setIndex(count);
                count++;
                //check surrouding when i = 1, j = 2
                if (checkEmptyStone(board[i][j])) {
                    continue;
                }
                if (board[i][j].getValue() == stoneChar) {

                    boolean leftSide = checkSurrounding(i, j-1, 0, -1, stoneChar);
                    boolean rightSide = checkSurrounding(i, j+1, 0, 1, stoneChar);
                    boolean upSide = checkSurrounding(i-1, j, -1, 0, stoneChar);
                    boolean downSide = checkSurrounding(i+1, j, 1, 0, stoneChar);
                    boolean rightUpDiagonal = checkSurrounding(i-1, j+1, -1, 1, stoneChar);
                    boolean rightDownDiagonal = checkSurrounding(i+1, j+1, 1, 1, stoneChar);
                    boolean leftDownDiagonal = checkSurrounding(i+1, j-1, 1, -1, stoneChar);
                    boolean leftUpDiagonal = checkSurrounding(i-1, j-1, -1, -1, stoneChar);
                    if(leftSide || rightSide || upSide || downSide || rightDownDiagonal || rightUpDiagonal || leftDownDiagonal || leftUpDiagonal){
                      moves.add(board[i][j].getIndex());
                    }
                    stone.add(bracket);
                }
            }
           // moves.add(stone);
        }
        return moves;
    }

    public boolean checkSurrounding(int i, int j, int iNextCheck, int jNextCheck, char stoneChar) {
        Stones[][] board = super.getBoard();

        if(i == 0 || i == 7 || j == 0 || j == 7){
            return false;
        }
        if(board[i][j].getValue() != stoneChar){
            return true;
        }
        if(board[i][j].getValue() == ' '){
            return true;
        }
        if(board[i][j].getValue() == stoneChar){
            return false;
        }
        boolean answer = checkSurrounding( i + iNextCheck, j + jNextCheck, iNextCheck, jNextCheck, stoneChar);
        return answer;
    }


    public boolean checkEmptyStone(Stones stone){
        // stone is empty return true
        if(stone.getValue() == ' '){
            return true;
        }
        // if stone is not empty return false
        return false;
    }

    public List listOfAllowedMoves(char stoneChar) { // needs your character ('B' or 'W')
        Stones[][] board = super.getBoard();
        List moves = new ArrayList<>();

        for(int i = 0; i < board.length; i++) {


            for(int j = 0; j < board[i].length; j++) {
                List possibleMove = new ArrayList<>();
                //check surrouding when i = 1, j = 2
                if (checkEmptyStone(board[i][j])) {
                    if(checkSurroundingIsEmpty(i, j, board)){
                        continue;
                    }else{
                        possibleMove.add(getIndex(i, j));
                        List leftSide = makeBracket(i, j-1,0, -1, stoneChar);
                        if(!leftSide.isEmpty()){
                            possibleMove.add(leftSide);
                        }
                        List rightSide = makeBracket(i, j+1, 0, 1, stoneChar);
                        if(!leftSide.isEmpty()){
                            possibleMove.add(leftSide);
                        }
                        List upSide = makeBracket(i-1, j, -1, 0, stoneChar);
                        if(!leftSide.isEmpty()){
                            possibleMove.add(leftSide);
                        }
                        List downSide = makeBracket(i+1, j, 1, 0, stoneChar);
                        if(!leftSide.isEmpty()){
                            possibleMove.add(leftSide);
                        }
                        List rightUpDiagonal = makeBracket(i-1, j+1, -1, 1, stoneChar);
                        if(!leftSide.isEmpty()){
                            possibleMove.add(leftSide);
                        }
                        List rightDownDiagonal = makeBracket(i+1, j+1, 1, 1, stoneChar);
                        if(!leftSide.isEmpty()){
                            possibleMove.add(leftSide);
                        }
                        List leftDownDiagonal = makeBracket(i+1, j-1, 1, -1, stoneChar);
                        if(!leftSide.isEmpty()){
                            possibleMove.add(leftSide);
                        }
                        List leftUpDiagonal = makeBracket(i-1, j-1, -1, -1, stoneChar);
                        if(!leftSide.isEmpty()){
                            possibleMove.add(leftSide);
                        }
                        if(!possibleMove.isEmpty()){
                            moves.add(possibleMove);
                        }

                    }
                }
            }
        }
        return moves;
    }

    private List makeBracket(int i, int j, int iNext, int jNext, char stoneChar) {
        //todo: make list of bracket (is a list of stones effected by move)
        return null;
    }


    public boolean isOnBoard(int i, int j){
        if(i >= 8 || i<0 || j >= 8 || j<0){
            return false;
        }
        return true;
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
