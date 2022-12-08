package com.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class GamePickerController implements Initializable {

    @FXML
    public TextField name;

    @FXML
    public ComboBox<String> gameType;

    @FXML
    public ComboBox<String> gamemode;

    @FXML
    public CheckBox tournementMode;

    private static final String bke = "Boter kaas en eieren";
    private static final String othello = "Othello";

    public void onSubmit(ActionEvent actionEvent) {
        Player player;

        //todo: warn client for a valid username
        if (!name.getText().isEmpty()) {
            player = new Player(name.getText());
        } else {
            player = new Player("Unknown Player");
        }

        SharedData sharedData = SharedData.getInstance();

        sharedData.setPlayer(player);
        sharedData.setGameType("tic-tac-toe");
        sharedData.setGamemode(gamemode.getValue());
        sharedData.setTournementMode(tournementMode.isSelected());


        if (gameType.getValue().equals(bke)) {
            Node node = (Node) actionEvent.getSource();
            Stage stage = (Stage) node.getScene().getWindow();
            stage.close();

            if(sharedData.getGamemode().equals("Speler vs Speler")) {
                try {
                    FXMLLoader fxmlLoader = new FXMLLoader(GameApplication.class.getResource("/fxml/TicTacToe.fxml"));
                    fxmlLoader.setController(new TicTacToeUI());

                    Parent root = fxmlLoader.load();

                    stage.setScene(new Scene(root, 800, 400));
                    stage.show();

                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }else{
                try {
                    int wait = 100;
                    ClientConnectionController connection = new ClientConnectionController();
                    connection.startConnection();
                    if(connection.sendStartData()){
                        TicTacToeGameLoop gameLoop = new TicTacToeGameLoop(connection);
                        boolean isRunning = true;
                        while (isRunning) {
                            gameLoop.run();
                            Thread.sleep(wait);
                            isRunning = gameLoop.isGameRunning();
                        }
                        gameLoop.stop();
                    }
                    else{
                        System.out.println("Niet mogelijk connectie te maken");
                    }

                } catch (IOException e) {
                    throw new RuntimeException(e);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }

//        if (gameType.getValue().equals(othello)) {
            //todo: implement this in 2nd term.
//        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        gameType.getItems().clear();
        gameType.getItems().addAll(bke, othello);
        gameType.getSelectionModel().selectFirst();

        gamemode.getItems().clear();
        gamemode.getItems().addAll("Speler vs Speler", "Speler vs Computer", "AI vs AI (online)");
        gamemode.getSelectionModel().selectFirst();
    }
}
