package com.example.demo.Othello;

import com.example.demo.connection.ClientConnectionController;
import com.example.demo.gameloop.GameLoop;
import com.example.demo.gameloop.GameStatus;

public class OthelloGameLoop extends GameLoop {

    protected OthelloController controller = new OthelloController();

    public OthelloGameLoop(ClientConnectionController connection) {
        super(connection);
    }

    protected void render() {
        // call methods to render the frontend here
    }

    protected void update() {
        //use controller here to do stuff (all the implementation happens there)
        // todo: use game status to decide what to do

        switch (this.gameStatus) {
            case WON:
            case DRAW:
            case LOST:
                // display text
                // reset board
                System.out.println("test");
                controller.resetBoard();
                break;
            case MY_TURN:
                int move = controller.calculateMove();
                controller.updateBoard(move, controller.getPlayerCharacter());
                break;
            case OPPONENT_TURN:
                System.out.println("test");
                // do something
                break;
            case ERROR:
                System.out.println("test");
                // do something funny
                break;
            default:
                System.out.println("it went broken");
                // woopsie something went broken
        }
    }

    protected void processGameLoop() {
        while (isGameRunning()) {
            processInput();
            update();
            render();
        }
    }
}
