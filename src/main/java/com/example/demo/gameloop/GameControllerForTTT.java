package com.example.demo.gameloop;

import com.example.demo.*;

public class GameControllerForTTT {
    private final Board board;
    protected final Player player;
    protected DecisionTree decisionTree;
    protected final SharedData data = SharedData.getInstance();

    public GameControllerForTTT() {
        this.board = new Board(3);
        this.player = data.getPlayer();
        this.decisionTree = new DecisionTree();
    }
    public void updateBoard(int move, char character){
        board.updateBoard(move, character);
    }
    public int calculateMove(){
        return decisionTree.getNextMove(board, player.getPlayerCharacter());
    }

    public void changePlayerName(String name) {
        player.setName(name);
    }

    public String getPlayerName() {
        return player.getName();
    }

    public void setUpOpponentCharacter(char character){
        player.setOpponentCharacter(character);
    }

    public char getOpponentCharacter(){
        return player.getOpponentCharacter();
    }

    public void setUpPlayerCharacter(char character){
        player.setPlayerCharacter(character);
    }

    public char getPlayerCharacter(){
        return player.getPlayerCharacter();
    }

    public void printBoard(){
        board.printBoard();
    }

}
