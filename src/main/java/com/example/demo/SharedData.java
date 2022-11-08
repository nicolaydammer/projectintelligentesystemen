package com.example.demo;

//singleton class
public final class SharedData {
    private final static SharedData INSTANCE = new SharedData();
    private Player player;
    private String gameType;
    private boolean hasConnection;
    private boolean setStartingPlayer;
    private int move;

    private SharedData() {}

    public static SharedData getInstance() {
        return INSTANCE;
    }

    public void setMove(int move) {
        this.move = move;
    }

    public int getMove(){
        return this.move;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public void setStartingPlayer(boolean setStartingPlayer){
        this.setStartingPlayer = setStartingPlayer;
    }

    public boolean getStartingPlayer(){
        return setStartingPlayer;
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
