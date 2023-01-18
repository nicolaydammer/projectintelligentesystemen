package com.example.demo.Othello;

import com.example.demo.TicTacToe.DecisionTree;
import com.example.demo.TicTacToe.TicTacToeBoard;
import com.example.demo.data.Player;
import com.example.demo.data.SharedData;

public class OthelloController {
    protected final Player player;
    //protected minimax minimax;

    protected final OthelloBoard board;
    protected final SharedData data = SharedData.getInstance();

    public OthelloController() {
        this.player = data.getPlayer();
        //this.minimax = new minimax();
        this.board = new OthelloBoard();
    }

    public int calculateMove(){
        //return this.minimax.getNextMove();
        return 0;
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
