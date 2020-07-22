package com.github.cc3002.citricliquid.gui.Phases;

import com.github.cc3002.citricjuice.model.GameController;

public class StartPhase extends Phase{
    public StartPhase(){
        this.canIStart=true;
        this.canIMove=false;
        this.canFight=false;
        this.recover=false;

    }



    @Override
    public void toMovingPhase() {
        changePhase(new MovingPhase());
    }

    @Override
    public void toRecoveryPhase() {
        changePhase(new RecoveryPhase());
    }

    @Override
    public String toString() {
        return "Start_Phase";
    }


}
