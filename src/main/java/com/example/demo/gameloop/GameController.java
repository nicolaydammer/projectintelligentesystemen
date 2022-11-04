package com.example.demo.gameloop;

import com.example.demo.Player;

public class GameController {

    protected final Player player;

    public GameController() {
        this.player = new Player("Funny");
    }

    public void changePlayerName(String name) {
        String currentMessage = player.getName();
        player.setName(name);
    }

    public String getPlayerName() {
        return player.getName();
    }

}
