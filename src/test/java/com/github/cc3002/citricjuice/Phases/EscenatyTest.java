package com.github.cc3002.citricjuice.Phases;

import com.github.cc3002.citricjuice.model.GameController;
import com.github.cc3002.citricliquid.gui.Phases.InvalidMovementException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EscenatyTest {
    private GameController controller=new GameController();

    @Test
    public void escenarioeTest() throws InvalidMovementException {
        controller.escenario();

        controller.tryToStart();
        assertEquals("WaitPath_Phase",controller.getCurrentPhase());

    }
}
