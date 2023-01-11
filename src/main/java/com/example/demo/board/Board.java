package com.example.demo.board;

import com.example.demo.board.Stones;

public abstract class Board {
    private final int size;
    private final Stones[][] board;

    public Board(int size) {
        this.size = size;
        this.board =  new Stones[size][size];
        initialization();
    }

    public void initialization(){
        for(int i=0; i<this.size; i++) {
            for (int j = 0; j < this.size;j++){
                this.board[i][j] = new Stones(' ');
            }
        }
    }

    public void printBoard(){
        String borderChar = "_";
        String border = borderChar.repeat(this.size*10);

        System.out.println(border);
        for(Stones[] stones : this.board) {
            for (Stones stone: stones){
                System.out.print(stone.getValue() + " ");
            }
            System.out.println();
        }
        System.out.println(border);
    }

    public int getSize() {
        return this.size;
    }

    public Stones[][] getBoard() {return this.board; }

    public char getStone(int i, int j){
        return this.board[i][j].getValue();
    }

    protected abstract boolean allowedMove(int i, int j, char Stone);

    public int pieceCounter(){
        int counter = 0;
        for(int i=0; i<this.size; i++) {

            for (int j = 0; j < this.size;j++){
                if(!allowedMove(i,j, getStone(i, j))){
                    counter++;
                }
            }
        }
        //System.out.println("counted " + counter + "pieces on the board");
        return counter;
    }

    public void updateBoard(int move, char character){
        int counter = 0;
        for(int i=0; i<this.size; i++) {
            for (int j = 0; j < this.size;j++){
                if(counter == move){
                    board[i][j] = new Stones(character);
                }
                counter ++;
            }
        }
    }

}
