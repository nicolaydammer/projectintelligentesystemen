package com.example.demo.gameloop;

import com.example.demo.*;

public class GameControllerForTTT {
    private Board board;
    protected final Player player;
    protected DecisionTree decisionTree;
    protected final SharedData data = SharedData.getInstance();

    public GameControllerForTTT() {
        this.board = new Board(3);
        this.player = data.getPlayer();
        this.decisionTree = new DecisionTree();
    }
    public void updateBoard(int move, char character){
        this.board.updateBoard(move, character);
    }
    public void refreshBoard(){board = new Board(3);}
    public int calculateMove(){
        return this.decisionTree.getNextMove(this.board, this.player.getPlayerCharacter());
    }

    public void changePlayerName(String name) {
        this.player.setName(name);
    }

    public String getPlayerName() {
        return this.player.getName();
    }

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

    public void printBoard(){
        this.board.printBoard();
    }

}
