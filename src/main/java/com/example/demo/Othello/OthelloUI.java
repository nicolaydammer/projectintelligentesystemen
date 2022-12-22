package com.example.demo.Othello;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.effect.ColorInput;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class OthelloUI implements Initializable {
    @FXML
    private Text scoreWhiteText;
    @FXML
    private Text scoreBlackText;

    private int scoreBlack;
    private int scoreWhite;

    private int playerTurn = 0;
    ArrayList<Pane> panes;
    OthelloBoard board;
    private int x= 8;
    private int y = 8;

    @FXML
    GridPane gridPane;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //todo: in gridpane zetten en van buttons panes maken + tekenen cirkel
        board = new OthelloBoard();
        panes = new ArrayList<Pane>();
        gridPane = new GridPane();

        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                //todo: maak klasse cel die pane extends
                Pane field = new Pane();
                panes.add(field);
                setupPane(field);
                field.setStyle("-fx-background-color: darkgreen;");
                if((i == 3 && j == 3) || (i==4 && j==4)){
                    Circle circle = drawCircle(Color.WHITE);
                    field.getChildren().add(circle);
                } else if ((i==4 && j==3) || (i==3 && j==4)) {
                    Circle circle = drawCircle(Color.BLACK);
                    field.getChildren().add(circle);
                }
                GridPane.setConstraints(field, i, j);
                gridPane.getChildren().addAll(field);

            }
        }
    }

    private void setupPane(Pane field) {
        field.setFocusTraversable(false);
        field.setDisable(true);
        legalMove(field);
        field.setOnMouseClicked(mouseEvent -> {
            setPlayerSymbol(field);
            field.setDisable(true);
        });
    }

    public void legalMove(Pane field){
        if (playerTurn % 2 == 0) {
            //todo: split possible moves from bracket
            List<Integer> moves = board.listAllowedMoves('B');
            for(int move: moves){
                Circle circle = drawCircle(Color.GREY);
                field.getChildren().add(circle);
                field.setDisable(false);
            }
        }else{
            List<Integer> moves = board.listAllowedMoves('W');
            for(int move: moves) {
                Circle circle = drawCircle(Color.GREY);
                field.getChildren().add(circle);
                field.setDisable(false);
            }
        }

    }

    @FXML
    void restartGame(ActionEvent event) {
        //panes.forEach(this::resetPane);
    }

    public void resetPane(Pane field) {
        //todo reset all panes except initial stones
        field.setDisable(false);
        //field.setText("");
    }

    public Circle drawCircle(Color color){
        Circle circle = new Circle(50, color);
        return circle;
    }

    public void calcScore(){
        //getscore --> Class othelloboard
    }

    public void setWhiteScore(){
        scoreWhiteText.setText(Integer.toString(getWhiteScore()));
    }

    public void setBlackScore() {
        scoreBlackText.setText(Integer.toString(getBlackScore()));
    }
    public int getWhiteScore(){
        return scoreWhite;
    }
    public int getBlackScore(){
        return scoreBlack;
    }

    public void setPlayerSymbol(Pane field) {
        //todo: turn other stones player's color
        if (playerTurn % 2 == 0) {
            //todo: current circle is grey --> change color
            Circle circle =  drawCircle(Color.BLACK);
            field.getChildren().add(circle);
            playerTurn = 1;

            //setmoce --> your move to board
            //getMove --> from AI class

        } else {
            Circle circle =  drawCircle(Color.WHITE);
            field.getChildren().add(circle);
            playerTurn = 0;
        }
    }




    }


