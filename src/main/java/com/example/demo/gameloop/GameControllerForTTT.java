package com.example.demo.gameloop;

import com.example.demo.*;

public class GameControllerForTTT {
    private final Board board;
    private final Stones stone;
    protected final Player player;
    protected DecisionTree decisionTree;
    protected final SharedData data = SharedData.getInstance();

    public GameControllerForTTT() {
        this.board = new Board(3);
        this.player = data.getPlayer();
        this.stone = new Stones(player.getPlayerCharacter());
        this.decisionTree = new DecisionTree(this.board, this.stone);
    }
    public void updateBoard(int move, char character){
        board.updateBoard(move, character);
    }
    public int calculateMove(){
        return decisionTree.getNextMove();
    }

    public void changePlayerName(String name) {
        player.setName(name);
    }

    public String getPlayerName() {
        return player.getName();
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
