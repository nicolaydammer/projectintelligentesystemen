package com.example.demo.Othello;

import com.example.demo.connection.ClientConnectionController;
import com.example.demo.gameloop.GameLoop;

public class OthelloGameLoop extends GameLoop {

    private final OController controller = new OController();

    public OthelloGameLoop(ClientConnectionController connection) {
        super(connection);
    }

    @Override
    protected void render() {
        // call methods to render the frontend here
    }

    @Override
    protected void update() {
        //use controller here to do stuff (all the implementation happens there)
    }

    @Override
    protected void processGameLoop() {
        while (isGameRunning()) {
            processInput();
            update();
            render();
        }
    }
}
