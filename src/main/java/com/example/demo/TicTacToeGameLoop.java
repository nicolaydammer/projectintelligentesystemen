package com.example.demo;

import com.example.demo.gameloop.GameLoop;

public class TicTacToeGameLoop extends GameLoop {

    public TicTacToeGameLoop(ClientConnectionController clientConnectionController) {
        super(clientConnectionController);
    }

    @Override
    protected void processGameLoop() {
        while (isGameRunning()) {
            processInput();
            update();
            render();
        }
    }

    protected void update() {
        System.out.println("Still going strong!");
    }

}
