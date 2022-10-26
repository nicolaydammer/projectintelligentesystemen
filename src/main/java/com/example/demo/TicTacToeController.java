package com.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import java.util.ResourceBundle;

public class TicTacToeController implements Initializable {
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

    private int playerTurn = 0;

    ArrayList<Button> buttons;

    private TicTacToeMakeTurn makeTurn = new TicTacToeMakeTurn();

    private Thread makeTurnThread;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        buttons = new ArrayList<>(Arrays.asList(button1, button2, button3, button4, button5, button6, button7, button8, button9));
        buttons.forEach(button -> {
            setUpButton(button);
            button.setFocusTraversable(false);
        });

        makeTurnThread = new Thread(this::runMakeTurn);
        makeTurnThread.start();
    }

    public void runMakeTurn() {

        Settings settings = new Settings();

//        set connection details, if not changed use default remote server
        settings.setHostName("localhost");
//        settings.setPort(1234);

        ClientConnectionController connection = new ClientConnectionController();
        connection.connection(settings.getHostName(), settings.getPort(), makeTurn);
    }

    @FXML
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
}
