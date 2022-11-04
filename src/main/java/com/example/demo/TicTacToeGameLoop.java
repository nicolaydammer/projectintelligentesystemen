package com.example.demo;

import com.example.demo.gameloop.GameLoop;

public class TicTacToeGameLoop extends GameLoop {

    @Override
    protected void processGameLoop() {
        while (isGameRunning()) {
            processInput();
            update();
            render();
        }
    }

    protected void update() {
        System.out.println("PlayerName : " + controller.getPlayerName());
    }

}
