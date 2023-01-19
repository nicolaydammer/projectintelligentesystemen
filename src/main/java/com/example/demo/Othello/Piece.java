package com.example.demo.Othello;

import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.StrokeType;

import static com.example.demo.Othello.OthelloUI.TILE_SIZE;

public class Piece extends Circle {

    private PieceType type;


    public Piece(PieceType type, int i, int j){
        this.type = type;

        relocate(i * TILE_SIZE, j * TILE_SIZE);

        Circle circle = new Circle();
        //circle.setFill(type == PieceType.BLACK ? Color.BLACK : Color.WHITE);
        circle.setStroke(type == PieceType.BLACK ? Color.BLACK : Color.WHITE);
        //circle.setStrokeWidth(TILE_SIZE * 0.03);
        circle.setStrokeType(StrokeType.INSIDE);
        circle.setLayoutX(25);
        circle.setLayoutY(25);
        circle.setRadius(17);
    }

    public PieceType getType() {
        return type;
    }

    public void setType(PieceType type) {
        this.type = type;
    }


}
