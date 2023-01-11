package com.example.demo.Othello;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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

    private int playerTurn = 0;
    ArrayList<Pane> panes;
    OthelloBoard board;
    private int x= 8;
    private int y = 8;

    @FXML
    GridPane gridPane;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        board = new OthelloBoard();
        setupBoard(board);
    }

    private void setupBoard(OthelloBoard board){
        panes = new ArrayList<Pane>();
        gridPane = new GridPane();
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                Cel field = new Cel(board.getIndex(i, j));
                panes.add(field);
                field.setFocusTraversable(false);
                field.setDisable(true);
                field.setStyle("-fx-background-color: darkgreen;");
                if((i == 3 && j == 3) || (i==4 && j==4)){
                    Circle circle = drawCircle(Color.WHITE);
                    field.setWhiteState();
                    field.getChildren().add(circle);
                } else if ((i==4 && j==3) || (i==3 && j==4)) {
                    Circle circle = drawCircle(Color.BLACK);
                    field.setBlackState();
                    field.getChildren().add(circle);
                }
                setupPane(field, i, j);
                GridPane.setConstraints(field, i, j);
                gridPane.getChildren().addAll(field);

            }
        }
    }

    private void setupPane(Cel field, int i, int j) {
        field.setOnMouseClicked(mouseEvent -> {
            setBlackScore();
            setWhiteScore();
            legalMove(field, i , j);
            setPlayerSymbol(field);
            field.setDisable(true);
        });
    }

    public void legalMove(Cel field, int i, int j){
        //enables to make a move on field if it is a legal move
        if (playerTurn % 2 == 0) {
            if (board.allowedMove(i, j, 'B')) {
                field.setGreyState();
                Circle circle = drawCircle(Color.GREY);
                field.getChildren().add(circle);
                field.setDisable(false);
            }
        }else{
            if(board.allowedMove(i, j, 'W')) {
                field.setGreyState();
                Circle circle = drawCircle(Color.GREY);
                field.getChildren().add(circle);
                field.setDisable(false);
            }
        }
    }

    @FXML
    void restartGame(ActionEvent event) {
        setupBoard(board);
        board.resetBoard();
     }

//    public void resetPane(Cel field) {
//        //todo reset all panes except initial stones
//        field.setDisable(false);
//        //field.setText("");
//    }

    public Circle drawCircle(Color color){
        Circle circle = new Circle(50, color);
        return circle;
    }


    public void setWhiteScore(){
        scoreWhiteText.setText(Integer.toString(getWhiteScore()));
    }

    public void setBlackScore() {
        scoreBlackText.setText(Integer.toString(getBlackScore()));
    }
    public int getWhiteScore(){
        return board.getWhiteScore();
    }
    public int getBlackScore(){
        return board.getBlackScore();
    }

    public void setPlayerSymbol(Cel field) {
        //todo: turn other stones player's color
        //todo: update backend board
        if (playerTurn % 2 == 0) {
            //todo: current circle is grey --> change color
            field.setBlackState();
            Circle circle =  drawCircle(Color.BLACK);
            field.getChildren().add(circle);
            playerTurn = 1;
            board.updateBoard(field.getCelNumber(),field.getState());

        } else {
            field.setWhiteState();
            Circle circle =  drawCircle(Color.WHITE);
            field.getChildren().add(circle);
            playerTurn = 0;
            board.updateBoard(field.getCelNumber(),field.getState());
        }
    }

    }


