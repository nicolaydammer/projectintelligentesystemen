package com.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;

public class TicTacToeController {
    private Stones stone;
    private final Board field = new Board(3);

    // for 2 humans playing
    @FXML
    private Button button1;
    @FXML
    private Button button2;
    @FXML
    private Button button3;
    @FXML
    private Button button4;
    @FXML
    private Button button5;
    @FXML
    private Button button6;
    @FXML
    private Button button7;
    @FXML
    private Button button8;
    @FXML
    private Button button9;

    @FXML
    private Text winnerText;

    @FXML
    private Button restart;

    private int playerTurn = 0;

    ArrayList<Button> buttons;
    ClientConnectionController controller;
    SharedData sharedData = SharedData.getInstance();

    public void initialize() {
        if (sharedData.hasConnection()) {
            controller = new ClientConnectionController();
            try {
                controller.startConnection();
                controller.sendStartData();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        buttons = new ArrayList<>(Arrays.asList(button1, button2, button3, button4, button5, button6, button7, button8, button9));
        buttons.forEach(button -> {
            setUpButton(button);
            button.setFocusTraversable(false);
        });

        restart.setOnMouseClicked(e -> {
            //todo: implement restart
        });
    }

    void restartGame(ActionEvent event) {
        buttons.forEach(this::resetButton);
        winnerText.setText("TicTacToe");
    }

    public void resetButton(Button button) {
        button.setDisable(false);
        button.setText("");
    }

    public void setUpButton(Button button) {
        button.setOnMouseClicked(mouseEvent -> {
            setPlayerSymbol(button);
            button.setDisable(true);
            checkIfGameIsOver();
        });
    }

    public void setPlayerSymbol(Button button) {
        if (playerTurn % 2 == 0) { // X starts always and is therefore always when playerTurn is even
            button.setText("X");
            playerTurn = 1;
        } else {
            button.setText("O");
            playerTurn = 0;
        }
    }

    public void checkIfGameIsOver() {
        for (int i = 0; i < 8; i++) {
            String line;
            switch (i) {
                case 0:
                    line = button1.getText() + button2.getText() + button3.getText();
                    break;
                case 1:
                    line = button4.getText() + button5.getText() + button6.getText();
                    break;
                case 2:
                    line = button7.getText() + button8.getText() + button9.getText();
                    break;
                case 3:
                    line = button1.getText() + button4.getText() + button7.getText();
                    break;
                case 4:
                    line = button2.getText() + button5.getText() + button8.getText();
                    break;
                case 5:
                    line = button3.getText() + button6.getText() + button9.getText();
                    break;
                case 6:
                    line = button1.getText() + button5.getText() + button9.getText();
                    break;
                case 7:
                    line = button3.getText() + button5.getText() + button7.getText();
                    break;
                default:
                    line = null;
                    break;
            }

            // X winner
            if (line.equals("XXX")) {
                winnerText.setText("X won!");
            }

            // O winner
            else if (line.equals("OOO")) {
                winnerText.setText("O won!");
            }
        }


    }


}
