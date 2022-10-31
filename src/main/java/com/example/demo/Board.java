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
        for(int i=0; i<=this.size; i++) {
            for (int j = 0; j <= this.size;j++){
                this.board[i][j] = new Stones(' ');
            }
        }
    }

    public void printBoard(){
        // todo: validate if this still works
        String borderChar = "_";
        String border = borderChar.repeat(this.size*3);
        System.out.println(border);

        for(int i=0; i<=this.size; i++) {
            System.out.print("| ");
            for (int j = 0; j <= this.size;j++){
                System.out.print(this.board[i][j] + " | ");
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
}
