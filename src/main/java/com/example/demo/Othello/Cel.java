package com.example.demo.Othello;

import javafx.scene.layout.Pane;

public class Cel extends Pane{
    private int celNumber;
    private char state;
    static final private char Black = 'B';
    static final private char White = 'W';
    static final private char Grey = 'G';

    public Cel(int number) {
        number = this.celNumber;
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
