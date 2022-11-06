package com.example.demo.gameloop;

import com.example.demo.Board;
import com.example.demo.DecisionTree;
import com.example.demo.Player;
import com.example.demo.Stones;

public class GameControllerForTTT {
    private final Board board;
    private final Stones stone;
    protected final Player player;

    protected DecisionTree decisionTree;

    public GameControllerForTTT() {
        this.board = new Board(3);
        this.player = new Player("Funny");
        this.stone = new Stones(' ');
        this.decisionTree = new DecisionTree(this.board, this.stone);
    }
    public void updateBoard(int move){
        board.updateBoard(move);
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

}
