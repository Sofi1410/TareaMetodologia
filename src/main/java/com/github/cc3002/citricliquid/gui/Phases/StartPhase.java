package com.github.cc3002.citricliquid.gui.Phases;

import com.github.cc3002.citricjuice.model.GameController;

public class StartPhase extends Phase{
    public StartPhase(){
        this.canIStart=true;
        this.canIMove=false;
        this.canFight=false;
        this.recover=false;
        this.stayAtHome=false;
        this.canIfinish=false;
        this.WaitAtHome=false;
        this.WaitTOFigth=false;
        this.WaitToPath=false;
        this.Battle=false;

    }





    @Override
    public String toString() {
        return "Start_Phase";
    }


    /**
     * Allows to change the phase if
     * your actual phase is Start Phase, WaitHome, WaitPath
     * or WaitFigth
     */
    @Override
    public void toMovingPhase() {
        changePhase(new MovingPhase());
    }

    /**
     * Change the phase to Recovery Phase if
     * the actual phase is StartPhase
     */
    @Override
    public void toRecoveryPhase(){
        changePhase(new RecoveryPhase());
    }




}
