package com.example.demo.minimax;

public interface IMiniMax {

    String[][] copyGrid();

    String[] copyPlayer();

    boolean isWinning(String[] Player);

    boolean isGameOver();

    void setScoreList();

    int getBestMove();

    int minimax();
}
