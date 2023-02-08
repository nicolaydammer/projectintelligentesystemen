package com.example.demo.Othello;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class OthelloUI implements Initializable {
    @FXML
    private Text scoreWhiteText;
    @FXML
    private Text scoreBlackText;

    private int playerTurn = 0;

    OthelloBoard othelloBoard;
    private int x= 8;
    private int y = 8;
    private Group tileGroup = new Group();
    private Group pieceGroup = new Group();
    public static final int TILE_SIZE = 100;
    public static final int WIDTH = 8;
    public static final int HEIGHT = 8;

    @FXML
    GridPane gridPane;

    private Tile[][] board = new Tile[WIDTH][HEIGHT];

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        othelloBoard = new OthelloBoard();
        createBord(othelloBoard);

    }
    public void createBord(OthelloBoard othelloBoard){
        gridPane = new GridPane();

        gridPane.setPrefSize(WIDTH * TILE_SIZE, HEIGHT * TILE_SIZE);
        gridPane.getChildren().addAll(tileGroup, pieceGroup);

        for (int i = 0; i < HEIGHT; i++) {
            for (int j = 0; j < WIDTH; j++) {
                Tile field = new Tile(i, j);
                board[i][j] = field;

                tileGroup.getChildren().add(field);
                field.setFocusTraversable(false);
                //field.setDisable(true);

                Piece piece = null;

                if((i == 3 && j == 3) || (i==4 && j==4)){
                    piece = makePiece(PieceType.WHITE, i, j);

                } else if ((i==4 && j==3) || (i==3 && j==4)) {
                    piece = makePiece(PieceType.BLACK, i, j);
                }
                if(piece != null) {
                    field.setPiece(piece);
                    field.getChildren().add(piece);
                    pieceGroup.getChildren().add(piece);
                }

                gridPane.add(field, i, j);
                setupPane(field, i, j);

            }
        }
    }

    private Piece makePiece(PieceType type, int i, int j){
        Piece piece =  new Piece(type, i, j);
        return piece;
    }

    private void setupPane(Tile field, int i, int j) {
        field.setOnMouseClicked(mouseEvent -> {
            setPlayerSymbol(field);
            setBlackScore();
            setWhiteScore();
            field.setDisable(true);
            legalMove(field, i , j);
        });
    }

    public void legalMove(Tile field, int i, int j){
        //enables to make a move on field if it is a legal move
        if (playerTurn % 2 == 0) {
            if (othelloBoard.allowedMove(i, j, 'B')) {
                field.setGreyState();
                field.setStyle("-fx-background-color: Grey;");
                field.setDisable(false);
            }
        }else{
            if(othelloBoard.allowedMove(i, j, 'W')) {
                field.setGreyState();
                field.setStyle("-fx-background-color: Grey;");
                field.setDisable(false);
            }
        }
    }

    @FXML
    void restartGame(ActionEvent event) {
        othelloBoard.resetBoard();
        createBord(othelloBoard);
     }

    public void setWhiteScore(){
        scoreWhiteText.setText(Integer.toString(getWhiteScore()));
    }

    public void setBlackScore() {
        scoreBlackText.setText(Integer.toString(getBlackScore()));
    }
    public int getWhiteScore(){
        return othelloBoard.calcScore('W');
    }
    public int getBlackScore(){
        return othelloBoard.calcScore('B');
    }

    public void setPlayerSymbol(Tile field) {
        if (playerTurn % 2 == 0) {
            field.setBlackState();
            if(field.hasPiece()){
                if(!field.getPiece().equals(PieceType.BLACK)){
                    field.getPiece().setType(PieceType.BLACK);
                }
            }else{
                Piece piece = makePiece(PieceType.BLACK, field.getRow(), field.getCol());
                field.getChildren().add(piece);
            }
            playerTurn = 1;
            othelloBoard.updateBoard(field.getCelNumber(),field.getState());

        } else {
            field.setWhiteState();
            if(field.hasPiece()){
                if(!field.getPiece().equals(PieceType.WHITE)){
                    field.getPiece().setType(PieceType.WHITE);
                }
            }else{
                Piece piece = makePiece(PieceType.WHITE, field.getRow(), field.getCol());
                field.getChildren().add(piece);
            }
            playerTurn = 0;
            othelloBoard.updateBoard(field.getCelNumber(),field.getState());
        }
    }

    }


