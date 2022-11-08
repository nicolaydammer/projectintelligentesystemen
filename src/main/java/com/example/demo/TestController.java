package com.example.demo;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class TestController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }

    public static void main(String[] args) {
        String test = "SVR GAME MATCH {PLAYERTOMOVE: \"Wesley\", GAMETYPE: \"Tic-tac-toe\", OPPONENT: \"Wesley\"}";
        String test2 = "SVR GAME MOVE {PLAYER: \"test\", MOVE: \"1\", DETAILS: \"\"}";
        String test3 = "SVR GAME MOVE {PLAYER: \"test\", MOVE: \"2\", DETAILS: \"\"}";
        String[] part = test3.split("\"", 3);
        String optie = part[2].substring(2, part[2].lastIndexOf(','));
        System.out.println(part[2].substring(2, part[2].lastIndexOf(',')));
        String testEcht = optie.substring(optie.indexOf("\"") + 1, optie.lastIndexOf("\""));
        System.out.println(testEcht);
    }
}