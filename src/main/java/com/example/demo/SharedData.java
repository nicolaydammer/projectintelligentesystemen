package com.example.demo;

//singleton class
public final class SharedData {
    private final static SharedData INSTANCE = new SharedData();
    private Player player;
    private String gameType;

    private String gamemode;
    private boolean setStartingPlayer;

    private boolean tournementMode;

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

    public String getGamemode() {
        return gamemode;
    }

    public void setGamemode(String gamemode) {
        this.gamemode = gamemode;
    }

    public boolean isTournementMode() {
        return tournementMode;
    }

    public void setTournementMode(boolean tournementMode) {
        this.tournementMode = tournementMode;
    }
}
