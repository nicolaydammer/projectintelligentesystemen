package com.example.demo.minimax;

public class TicTacToeMiniMax implements IMiniMax
{

    @Override
    public String[][] copyGrid() {
        return new String[0][];
    }

    @Override
    public String[] copyPlayer() {
        return new String[0];
    }

    @Override
    public boolean isWinning(String[] Player) {
        return false;
    }

    @Override
    public boolean isGameOver() {
        return false;
    }

    @Override
    public void setScoreList() {

    }

    @Override
    public int getBestMove() {
        return 0;
    }

    @Override
    public int minimax() {
        return 0;
    }
}
