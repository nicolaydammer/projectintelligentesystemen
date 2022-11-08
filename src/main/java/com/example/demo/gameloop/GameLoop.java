package com.example.demo.gameloop;

import com.example.demo.ClientConnectionController;

import java.io.IOException;
import java.util.Random;

public abstract class GameLoop {

    protected volatile GameStatus status;

    protected GameControllerForTTT controller;

    protected ClientConnectionController clientConnectionController;

    private Thread gameThread;

    public GameLoop() {
        clientConnectionController  = new ClientConnectionController();
        controller = new GameControllerForTTT();
        status = GameStatus.STOPPED;
        try {
            if(clientConnectionController.checkStartingPlayer()){
                controller.setUpPlayerCharacter('X');
            }else{
                controller.setUpPlayerCharacter('O');
            }
        } catch (IOException e){
            System.out.println(e.getMessage());
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
            if(clientConnectionController.checkTurn().equals( "Jij moet een zet doen!")){
                int move = controller.calculateMove();
                clientConnectionController.sendMessage("move " + move);
                controller.updateBoard(move, controller.getPlayerCharacter());
            }else if (!clientConnectionController.checkTurn().equals("Je hebt gewonnen") || !clientConnectionController.checkTurn().equals("Je hebt verloren")){
                int move = Integer.parseInt(clientConnectionController.checkTurn());
                controller.updateBoard(move, controller.getPlayerCharacter());
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
        controller.printBoard();
        try {
            if (clientConnectionController.checkTurn().equals("Je hebt verloren")){
                System.out.println("Je hebt verloren");
                clientConnectionController.sendMessage("bye");
                status = GameStatus.STOPPED;
            }
            else if (clientConnectionController.checkTurn().equals("Je hebt gewonnen")){
                System.out.println("Je hebt gewonnen");
                clientConnectionController.sendMessage("bye");
                status = GameStatus.STOPPED;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    protected abstract void processGameLoop();

}
