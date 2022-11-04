package com.example.demo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TicTacToeControllerTest {

    TicTacToeController controller;

    @BeforeEach
    void setUp() {
        controller = new TicTacToeController();
        assertNotNull(controller);
    }

    @Test
    public void ga_ik_controler_initialiseren() {
        controller.initialize();
    }
}
