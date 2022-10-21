package src.main.java.com.example.demo;

import java.util.Random;

public class Stones {
    private String player;
    private String oppositePlayer;

    private String game;

    private final String[] TicTacToe = {"X", "O"};
    private final String[] Orthello = {"Black", "White"};

    public Stones(String game) {
        this.game =  game;
        initialization();
    }

    public void initialization(){
        Random rand = new Random();
        if(this.game.equals("TicTacToe")){
            this.player = this.TicTacToe[rand.nextInt(this.TicTacToe.length)];

            if(this.player.equals("X")){
                this.oppositePlayer = "O";
            } else {
                this.oppositePlayer = "X";
            }
        } else if (this.game.equals("Orthello")) {
            this.player = this.Orthello[rand.nextInt(this.Orthello.length)];

            if(this.player.equals("Black")){
                this.oppositePlayer = "White";
            } else {
                this.oppositePlayer = "Black";
            }
        }
    }

    public String getGame() {
        return game;
    }

    public void setGame(String game) {
        this.game = game;
    }

    public String getPlayer() {
        return player;
    }

    public void setPlayer(String player) {
        this.player = player;
    }

    public String getOppositePlayer() {
        return oppositePlayer;
    }

    public void setOppositePlayer(String oppositePlayer) {
        this.oppositePlayer = oppositePlayer;
    }
}
