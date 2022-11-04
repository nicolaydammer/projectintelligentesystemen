package com.example.demo.gameloop;

import java.util.Random;

public abstract class GameLoop {

    protected volatile GameStatus status;

    protected GameController controller;

    private Thread gameThread;

    public GameLoop() {
        controller = new GameController();
        status = GameStatus.STOPPED;
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
            int lag = new Random().nextInt(200) + 50;
            String newPlayerName = "Funny_" + lag;
            controller.changePlayerName(newPlayerName);
            if (controller.getPlayerName().length() == 9) {
                this.stop();
            }
            Thread.sleep(lag);
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }

    protected void render() {
        String playerName = controller.getPlayerName();
        System.out.println("Current playerName: " + playerName);
    }

    protected abstract void processGameLoop();

}
