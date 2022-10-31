package com.example.demo;

public class Player {
    private String playerName;

    public Player(String playerName){
        this.playerName = playerName;
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
