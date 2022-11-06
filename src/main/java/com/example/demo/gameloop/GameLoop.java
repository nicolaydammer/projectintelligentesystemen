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
            if(clientConnectionController.checkTurn() == "Jij moet een zet doen!"){
                int move = controller.calculateMove();
                clientConnectionController.sendMessage("move " + move);
                controller.updateBoard(move);
            }else{
                int move = Integer.parseInt(clientConnectionController.checkTurn());
                controller.updateBoard(move);
            }
            int lag = new Random().nextInt(200) + 50;
            String newPlayerName = "Funny_" + lag;
            controller.changePlayerName(newPlayerName);
            if (controller.getPlayerName().length() == 9) {
                this.stop();
            }
            Thread.sleep(lag);
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    protected void render() {
        String playerName = controller.getPlayerName();
        System.out.println("Current playerName: " + playerName);
    }

    protected abstract void processGameLoop();

}
