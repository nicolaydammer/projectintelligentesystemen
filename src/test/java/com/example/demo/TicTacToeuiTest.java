package com.example.demo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TicTacToeuiTest {

    TicTacToeUI controller;

    @BeforeEach
    void setUp() {
        controller = new TicTacToeUI();
        assertNotNull(controller);
    }

//    @Test
//    public void ga_ik_controler_initialiseren() {
//        controller.initialize();
//    }
}
