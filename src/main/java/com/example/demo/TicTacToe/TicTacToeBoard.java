package com.example.demo.TicTacToe;

import com.example.demo.Board;

public class TicTacToeBoard extends Board {
    public TicTacToeBoard() {
        super(3);
    }

    @Override
    protected boolean allowedMove(int i, int j) {
        return super.getStone(i, j) == ' ';
    }

    public void resetBoard(){initialization();}
}
