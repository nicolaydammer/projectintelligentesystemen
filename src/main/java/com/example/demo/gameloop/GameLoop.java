package com.example.demo.gameloop;

import com.example.demo.connection.ClientConnectionController;
import com.example.demo.data.SharedData;
import com.example.demo.TicTacToe.GameControllerForTTT;

import java.io.IOException;
import java.util.Random;

public abstract class GameLoop {

    protected volatile GameLoopStatus loopStatus;

    protected volatile GameStatus gameStatus;

    protected ClientConnectionController connection;

    private Thread gameThread;

    int move;

    char currentCharacter;
    SharedData sharedData = SharedData.getInstance();

    public GameLoop(ClientConnectionController connection) {
        this.connection = connection;
//        ticTacToeGameController = new GameControllerForTTT();
//        status = GameStatus.STOPPED;
//        if(sharedData.getStartingPlayer()){
//            ticTacToeGameController.setUpPlayerCharacter('X');
//            ticTacToeGameController.setUpOpponentCharacter('O');
//        }else{
//            ticTacToeGameController.setUpPlayerCharacter('O');
//            ticTacToeGameController.setUpOpponentCharacter('X');
//        }
//
//        System.out.println("Jij speelt als: " + ticTacToeGameController.getPlayerCharacter());
//
    }

    public void run() {
        loopStatus = GameLoopStatus.RUNNING;
        gameThread = new Thread(this::processGameLoop);
        gameThread.start();
    }

    public void stop() {
        loopStatus = GameLoopStatus.STOPPED;
    }

    public boolean isGameRunning() {
        return loopStatus == GameLoopStatus.RUNNING;
    }

    protected void processInput() {
        // todo: this needs to support offline and online mode
//        try {
//            String response = connection.checkTurn();
//            System.out.println("Response van procesinput: " + response);
//
//            // if the response equels move, the opponent did a move. and now we want to handle this response.
//            if (response.contains("Zet tegenstander:")){
//                gameStatus = GameStatus.OPPONENT_TURN;
//            }
//
//            // If the response is that you can do a move, calculate the move and send it to the server.
//            else if (response.contains("Jij moet een zet doen!")){
//                gameStatus = GameStatus.MY_TURN;
//            }
//
//            else if (response.contains("Je hebt verloren")){
//                gameStatus =GameStatus.LOST;
//            }
//
//            else if (response.equals("Je hebt gewonnen")){
//                gameStatus = GameStatus.WON;
//            }
//            else if (response.equals("Gelijkspel")){
//                gameStatus = GameStatus.DRAW;
//            }
//            else{
//                gameStatus = GameStatus.ERROR;
//            }
//            System.out.println();
//            int lag = new Random().nextInt(200) + 50;
//            Thread.sleep(lag);
//
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
    }

    protected abstract void render();

    protected abstract void update();

    protected abstract void processGameLoop();

}
