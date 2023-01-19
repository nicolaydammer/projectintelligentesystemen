package com.example.demo.Othello;

import com.example.demo.connection.ClientConnectionController;
import com.example.demo.gameloop.GameLoop;
import com.example.demo.gameloop.GameStatus;

public class OthelloGameLoop extends GameLoop {

    private final OthelloController controller = new OthelloController();

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
        // todo: use game status to decide what to do
        switch (this.gameStatus) {
            case WON:
            case DRAW:
            case LOST:
                // display text
                // reset board
                break;
            case MY_TURN:
                // do something turn related
                break;
            case OPPONENT_TURN:
                // do something
                break;
            case ERROR:
                // do something funny
                break;
            default:
                // woopsie something went broken
        }
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
