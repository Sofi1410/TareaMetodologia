package com.github.cc3002.citricliquid.gui.Phases;

import com.github.cc3002.citricjuice.model.GameController;

public class Phase {

    protected GameController controller ;

    public void setController(GameController controller){
        this.controller=controller;
    }

    protected void changePhase(Phase phase){
        controller.setPhase(phase);
    }

}
