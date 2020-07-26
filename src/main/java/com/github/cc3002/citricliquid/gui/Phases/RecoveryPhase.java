package com.github.cc3002.citricliquid.gui.Phases;

public class RecoveryPhase extends Phase {

    public RecoveryPhase(){
        this.canIStart=true;
        this.canIMove=false;
        this.canFight=false;
        this.recover=true;
        this.stayAtHome=false;
        this.canIfinish=false;
        this.WaitAtHome=false;
        this.WaitTOFigth=false;
        this.WaitToPath=false;
        this.Battle=false;
    }
    @Override
    public String toString() {
        return "Recovery_Phase";
    }

    /**
     * Allows to change the phase if
     * the actual phase is RecoveryPhase
     */
    @Override
    public void toStartPhase() {
        changePhase(new StartPhase());
    }

}
