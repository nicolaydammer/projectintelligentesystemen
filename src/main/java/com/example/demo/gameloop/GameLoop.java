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
            System.out.println("response checkturn van procesinput:" + response);
            if(response.contains("Jij moet een zet doen!")){
                System.out.println(response); //jij moet een zet doen
                int move = ticTacToeGameController.calculateMove();
                ticTacToeGameController.updateBoard(move, ticTacToeGameController.getPlayerCharacter());
                ticTacToeGameController.printBoard();
            }else if (response.contains("MOVE:")){
                System.out.println(response); //move: <int>
                String moveString = response.substring(response.indexOf("\"") + 1, response.lastIndexOf("\""));
                int move = Integer.parseInt(moveString);
                System.out.println(move);
                ticTacToeGameController.updateBoard(move, ticTacToeGameController.getOpponentCharacter());
                ticTacToeGameController.printBoard();
            }else if (response.contains("Je hebt verloren")){
                System.out.println("Je hebt verloren");
                ticTacToeGameController.printBoard();
                connection.stopConnection();
                status = GameStatus.STOPPED;
            }else if(response.equals("Je hebt gewonnen")){
                System.out.println("Je hebt gewonnen");
                ticTacToeGameController.printBoard();
                connection.stopConnection();
                status = GameStatus.STOPPED;
            } else{
                System.out.println(response);
                System.out.println("Spel voorbij");
                ticTacToeGameController.printBoard();
                connection.stopConnection();
                status = GameStatus.STOPPED;
            }
            int lag = new Random().nextInt(200) + 50;
            Thread.sleep(lag);
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    protected void render() {
        ticTacToeGameController.printBoard();
    }

    protected abstract void processGameLoop();

}
