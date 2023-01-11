package com.example.demo.TicTacToe;

import com.example.demo.connection.ClientConnectionController;
import com.example.demo.data.SharedData;
import com.example.demo.gameloop.GameLoop;
import com.example.demo.gameloop.GameLoopStatus;
import com.example.demo.gameloop.GameStatus;

import java.io.IOException;


public class TicTacToeGameLoop extends GameLoop {

    protected GameControllerForTTT ticTacToeGameController;
    protected ClientConnectionController connection;
    int move;
    char currentCharacter;
    SharedData sharedData = SharedData.getInstance();

    public TicTacToeGameLoop(ClientConnectionController connection) {
        super(connection);

        ticTacToeGameController = new GameControllerForTTT();
        loopStatus = GameLoopStatus.STOPPED;
        if(sharedData.getStartingPlayer()){
            ticTacToeGameController.setUpPlayerCharacter('X');
            ticTacToeGameController.setUpOpponentCharacter('O');
        }else{
            ticTacToeGameController.setUpPlayerCharacter('O');
            ticTacToeGameController.setUpOpponentCharacter('X');
        }

        System.out.println("Jij speelt als: " + ticTacToeGameController.getPlayerCharacter());
    }

    @Override
    protected void render() {

    }

    @Override
    protected void processGameLoop() {
        while (isGameRunning()) {
            processInput();
            update();
            render();
        }
    }
    @Override
    protected void update() {
        try {
            String response = connection.checkTurn();
            System.out.println("Response van procesinput: " + response);

            // if the response equels move, the opponent did a move. and now we want to handle this response.
            if (gameStatus == GameStatus.OPPONENT_TURN){
                String moveString = response.substring(response.indexOf("\"") + 1, response.lastIndexOf("\""));
                move = Integer.parseInt(moveString);
                currentCharacter = ticTacToeGameController.getOpponentCharacter();

            }
            // If the response is that you can do a move, calculate the move and send it to the server.
            else if (gameStatus == GameStatus.MY_TURN ){
                move = ticTacToeGameController.calculateMove();
                currentCharacter = ticTacToeGameController.getPlayerCharacter();
                connection.sendMessage("move " + move);
            }

            else if (gameStatus == GameStatus.DRAW){
                ticTacToeGameController.resetBoard();
            }

            else if (gameStatus == GameStatus.LOST){
                ticTacToeGameController.resetBoard();
            }
            else if (gameStatus == GameStatus.WON){
                ticTacToeGameController.resetBoard();
            }
            else{
                System.out.println(response);
                System.out.println("Spel voorbij");
                ticTacToeGameController.printBoard();
            }
            System.out.println();
            System.out.println("update bord...");
            ticTacToeGameController.updateBoard(move, currentCharacter);
            System.out.println();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
