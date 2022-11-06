package com.example.demo;

public class Player {
    private String playerName;
    private char playerCharacter;

    public Player(String playerName){
        this.playerName = playerName;
        this.playerCharacter = playerCharacter;
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
