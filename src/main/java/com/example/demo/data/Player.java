package com.example.demo.data;

public class Player {
    private String playerName;
    private char playerCharacter;
    private char opponentCharacter;

    public Player(String playerName){
        this.playerName = playerName;
        this.playerCharacter = playerCharacter;
        this.opponentCharacter = opponentCharacter;
    }

    public char getOpponentCharacter() {
        return opponentCharacter;
    }

    public void setOpponentCharacter(char opponentCharacter) {
        this.opponentCharacter = opponentCharacter;
    }

    public char getPlayerCharacter() {
        return playerCharacter;
    }

    public void setPlayerCharacter(char playerCharacter) {
        this.playerCharacter = playerCharacter;
    }

    public String getName() {
        return playerName;
    }

    public void setName(String name) {
        this.playerName = name;
    }

    @Override
    public String toString() {
        return "Player{" +
                "playerName='" + playerName + '\'' +
                '}';
    }
}
