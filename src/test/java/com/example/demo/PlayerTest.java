package com.example.demo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.MockitoAnnotations.initMocks;

public class PlayerTest {

    private Player player;

    @Mock
    private String name;

    @BeforeEach
    public void setUp() {
        initMocks(this);
    }

    @Test
    public void test_ga_ik_naam_van_speler_controleren() {
        String isLeeg = null;
        player = new Player(isLeeg);
        assertNotNull(player.getName());
    }

}
