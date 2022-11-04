package com.example.demo;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class GamePickerControllerTest {

    private GamePickerController controller;

    @Mock
    private ActionEvent actionEventMock;

    @Mock
    private Node nodeMock;

    @BeforeEach
    void setUp() {
        initMocks(this);
        controller = new GamePickerController();
    }

    @Test
    public void test_ga_ik_start_scherm_controleren() {

        when(actionEventMock.getSource()).thenReturn(nodeMock);

        controller.initialize(null, null);
        controller.onSubmit(actionEventMock);

    }
}
