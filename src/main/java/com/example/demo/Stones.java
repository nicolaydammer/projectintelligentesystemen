package com.example.demo;
public class Stones {

    private char value;
    private int index;
    public Stones(char value){
        this.value = value;
    }
    public char getValue() {
        return value;
    }
    public void setValue(char value) {
        this.value = value;
    }

    public void setIndex(int index){ this.index = index;}
    public int getIndex(){return this.index;}
}
