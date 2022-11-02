package com.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
    public ComboBox<String> needsConnection;

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

        if (gameType.getValue().equals(bke)) {
            Node node = (Node) actionEvent.getSource();
            Stage stage = (Stage) node.getScene().getWindow();
            stage.close();

            try {
                Parent root = FXMLLoader.load(GameApplication.class.getResource("/fxml/TicTacToe.fxml"));
                SharedData sharedData = SharedData.getInstance();

                sharedData.setPlayer(player);
                sharedData.setGameType("bke");
                sharedData.setHasConnection(needsConnection.getValue().equals("Online"));

                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();

                TicTacToeController controller = new FXMLLoader(getClass().getResource("/fxml/TicTacToe.fxml")).getController();
                controller.initialize();

            } catch (IOException e) {
                throw new RuntimeException(e);
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

        needsConnection.getItems().clear();
        needsConnection.getItems().addAll("Online", "Lokaal");
        needsConnection.getSelectionModel().selectFirst();
    }
}
