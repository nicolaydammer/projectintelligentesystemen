package com.example.demo.Othello;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import static com.example.demo.Othello.OthelloUI.TILE_SIZE;

public class Tile extends Rectangle {
    private int celNumber;
    private int row;
    private int col;
    private char state;
    static final private char Black = 'B';
    static final private char White = 'W';
    static final private char Grey = 'G';

    private Piece piece;

    public Tile(int i, int j) {
        i = this.row;
        j = this.col;
        setWidth(OthelloUI.TILE_SIZE);
        setHeight(OthelloUI.TILE_SIZE);
        setFill(Color.DARKGREEN);
        setStroke(Color.BLACK);
        setStrokeWidth(TILE_SIZE * 0.03);
        relocate(i * OthelloUI.TILE_SIZE, j * OthelloUI.TILE_SIZE);
    }

    public boolean hasPiece(){
        return piece != null;
    }

    public Piece getPiece() {
        return piece;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
    }

    public int getCelNumber(){
        return celNumber;
    }

    public void setCelNumber(int nr){
        this.celNumber = nr;
    }

    public char getState() {
        return state;
    }

    public void setBlackState() {
        this.state = Black;
    }

    public void setWhiteState() {
        this.state = White;
    }

    public void setGreyState() {
        this.state = Grey;
    }
}
