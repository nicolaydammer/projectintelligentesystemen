package com.example.demo.Othello;

import com.example.demo.board.Stones;
import com.example.demo.connection.ClientConnectionController;
import com.example.demo.data.SharedData;
import com.example.demo.gameloop.GameLoop;
import com.example.demo.gameloop.GameStatus;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.paint.Color;

import java.io.IOException;

public class OthelloGameLoop extends GameLoop {

    protected OthelloController controller = new OthelloController();

    SharedData sharedData = SharedData.getInstance();

    public OthelloGameLoop(ClientConnectionController connection) {
        super(connection);
    }

    protected void render() {
        System.out.println("try render me");
        OthelloUI othelloUI = sharedData.getUIcontroller();

        stop();
    }

    protected void update() {
        //use controller here to do stuff (all the implementation happens there)
        // todo: use game status to decide what to do
        System.out.println("lets figure out what to do");
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
            case NOT_STARTED:
                System.out.println("do a move please");
                break;
            default:
                System.out.println("it went broken");
                stop();
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
