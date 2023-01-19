package com.example.demo.Othello;

import javafx.fxml.FXML;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import static com.example.demo.Othello.OthelloUI.TILE_SIZE;

public class Tile extends Pane {

    private int celNumber;
    private int row;
    private int col;
    private char state;
    static final private char Black = 'B';
    static final private char White = 'W';
    static final private char Grey = 'G';

    private Piece piece;

    public Tile(int i, int j) {
        this.row = i;
        this.col = j;
        setWidth(OthelloUI.TILE_SIZE);
        setHeight(OthelloUI.TILE_SIZE);
//        setFill(Color.BLUE);
//        setStroke(Color.BLACK);
//        setStrokeWidth(TILE_SIZE * 0.03);
        relocate(i * OthelloUI.TILE_SIZE, j * OthelloUI.TILE_SIZE);

    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
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
