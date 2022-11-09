package com.example.demo.gameloop;

import com.example.demo.ClientConnectionController;
import com.example.demo.SharedData;

import java.io.IOException;
import java.util.Random;

public abstract class GameLoop {

    protected volatile GameStatus status;

    protected GameControllerForTTT ticTacToeGameController;

    protected ClientConnectionController connection;

    private Thread gameThread;

    int move;

    char currentCharacter;
    SharedData sharedData = SharedData.getInstance();

    public GameLoop(ClientConnectionController connection) {
        this.connection = connection;
        ticTacToeGameController = new GameControllerForTTT();
        status = GameStatus.STOPPED;
        if(sharedData.getStartingPlayer()){
            ticTacToeGameController.setUpPlayerCharacter('X');
            ticTacToeGameController.setUpOpponentCharacter('O');
        }else{
            ticTacToeGameController.setUpPlayerCharacter('O');
            ticTacToeGameController.setUpOpponentCharacter('X');
        }

        System.out.println("Jij speelt als: " + ticTacToeGameController.getPlayerCharacter());

    }

    public void run() {
        status = GameStatus.RUNNING;
        gameThread = new Thread(this::processGameLoop);
        gameThread.start();
    }

    public void stop() {
        status = GameStatus.STOPPED;
    }

    public boolean isGameRunning() {
        return status == GameStatus.RUNNING;
    }

    protected void processInput() {
        try {
            String response = connection.checkTurn();
            System.out.println("Response van procesinput: " + response);

            // if the response equels move, the opponent did a move. and now we want to handle this response.
            if (response.contains("Zet tegenstander:")){
                String moveString = response.substring(response.indexOf("\"") + 1, response.lastIndexOf("\""));
                move = Integer.parseInt(moveString);
                currentCharacter = ticTacToeGameController.getOpponentCharacter();
                update();
                render();
            }

            // If the response is that you can do a move, calculate the move and send it to the server.
            if(response.contains("Jij moet een zet doen!")){
                move = ticTacToeGameController.calculateMove();
                currentCharacter = ticTacToeGameController.getPlayerCharacter();
                update();
                render();
                connection.sendMessage("move " + move);
            }

            if (response.contains("Je hebt verloren")){
                connection.stopConnection();
                update();
                render();
                status = GameStatus.STOPPED;
            }

            if(response.equals("Je hebt gewonnen")){
                connection.stopConnection();
                update();
                render();
                status = GameStatus.STOPPED;
            }
//            else{
//                System.out.println(response);
//                System.out.println("Spel voorbij");
//                ticTacToeGameController.printBoard();
//                connection.stopConnection();
//                status = GameStatus.STOPPED;
//            }
//            System.out.println();
//            int lag = new Random().nextInt(200) + 50;
//            Thread.sleep(lag);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    protected void render() {
        ticTacToeGameController.printBoard();
    }

    protected void update() {
        System.out.println("update bord...");
        ticTacToeGameController.updateBoard(move, currentCharacter);
        System.out.println();
    }

    protected abstract void processGameLoop();

}
