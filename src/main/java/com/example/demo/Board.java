package com.example.demo;

public class Board {
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
        // todo: validate if this still works
        String borderChar = "_";
        String border = borderChar.repeat(this.size*3);
        System.out.println(border);

        for(int i=0; i<this.size; i++) {
            System.out.print("| ");
            for (int j = 0; j < this.size;j++){
                System.out.print(this.board[i][j].getValue() + " | ");
            }

            System.out.println();
            System.out.println(border);
        }
        System.out.println(border);
    }

    public int getSize() {
        return this.size;
    }

    public Stones[][] getBoard() {return this.board; }

    private boolean isEmptyPlace(int i, int j) {
        return this.board[i][j].getValue() == ' ';
    }

    public int pieceCounter(){
        int counter = 0;
        for(int i=0; i<this.size; i++) {

            for (int j = 0; j < this.size;j++){
                if(!isEmptyPlace(i,j)){
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
