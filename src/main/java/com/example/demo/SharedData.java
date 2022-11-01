package com.example.demo;

//singleton class
public final class SharedData {
    private final static SharedData INSTANCE = new SharedData();
    private Player player;
    private String gameType;
    private boolean hasConnection;

    private SharedData() {}

    public static SharedData getInstance() {
        return INSTANCE;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public String getGameType() {
        return gameType;
    }

    public void setGameType(String gameType) {
        this.gameType = gameType;
    }

    public boolean hasConnection() {
        return hasConnection;
    }

    public void setHasConnection(boolean hasConnection) {
        this.hasConnection = hasConnection;
    }
}
