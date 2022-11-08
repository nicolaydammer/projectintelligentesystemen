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
            if(connection.checkTurn().equals( "Jij moet een zet doen!")){
                int move = ticTacToeGameController.calculateMove();
                sharedData.setMove(move);
               // connection.sendMessage(move);
                System.out.println();
                ticTacToeGameController.updateBoard(move, ticTacToeGameController.getPlayerCharacter());
            }else if (!connection.checkTurn().equals("Je hebt gewonnen") || !connection.checkTurn().equals("Je hebt verloren")){
                System.out.println("tegenstander zet "+ connection.checkTurn());
                int move = Integer.parseInt(connection.checkTurn());
                ticTacToeGameController.updateBoard(move, ticTacToeGameController.getOpponentCharacter());
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
        try {
            if (connection.checkTurn().equals("Je hebt verloren")){
                System.out.println("Je hebt verloren");
                connection.sendMessage("bye");
                status = GameStatus.STOPPED;
            }
            else if (connection.checkTurn().equals("Je hebt gewonnen")){
                System.out.println("Je hebt gewonnen");
                connection.sendMessage("bye");
                status = GameStatus.STOPPED;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    protected abstract void processGameLoop();

}
