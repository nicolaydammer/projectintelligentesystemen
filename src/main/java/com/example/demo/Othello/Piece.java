package com.example.demo.Othello;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import static com.example.demo.Othello.OthelloUI.TILE_SIZE;

public class Piece extends Circle {

    private PieceType type;


    public Piece(PieceType type, int i, int j){
        this.type = type;

        relocate(i * TILE_SIZE, j * TILE_SIZE);

        Circle circle = new Circle(TILE_SIZE * 0.3125);
        circle.setFill(type == PieceType.BLACK ? Color.BLACK : Color.WHITE);
        circle.setStroke(Color.BLACK);
        circle.setStrokeWidth(TILE_SIZE * 0.03);

        circle.setTranslateX((TILE_SIZE - TILE_SIZE * 0.3125 * 2) / 2);
        circle.setTranslateY((TILE_SIZE - TILE_SIZE * 0.3125 * 2) / 2);



    }

    public PieceType getType() {
        return type;
    }

    public void setType(PieceType type) {
        this.type = type;
    }


}
