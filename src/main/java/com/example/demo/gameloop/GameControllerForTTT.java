package com.example.demo.gameloop;

import com.example.demo.*;

public class GameControllerForTTT {
    protected final Player player;
    protected DecisionTree decisionTree;

    protected final TicTacToeBoard board;
    protected final SharedData data = SharedData.getInstance();

    public GameControllerForTTT() {
        this.player = data.getPlayer();
        this.decisionTree = new DecisionTree();
        this.board = new TicTacToeBoard();
    }

    public int calculateMove(){
        return this.decisionTree.getNextMove(this.board, this.player.getPlayerCharacter());
    }
    public void resetBoard(){board.resetBoard();}

    public void setUpOpponentCharacter(char character){
        this.player.setOpponentCharacter(character);
    }

    public char getOpponentCharacter(){
        return this.player.getOpponentCharacter();
    }

    public void setUpPlayerCharacter(char character){
        this.player.setPlayerCharacter(character);
    }

    public char getPlayerCharacter(){
        return this.player.getPlayerCharacter();
    }

    public void updateBoard(int move, char character){board.updateBoard(move, character);}

    public void printBoard(){board.printBoard();}


}
