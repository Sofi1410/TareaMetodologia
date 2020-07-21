package com.github.cc3002.citricliquid.gui.Phases;

public class RecoveryPhase extends Phase {

    public RecoveryPhase(){
        this.canIStart=false;
        this.canIMove=false;
        this.canFight=false;
        this.canIStart=false;
        this.recover=true;
    }
    @Override
    public String toString() {
        return "Recovery_Phase";
    }

    @Override
    public void toStartPhase() {
        changePhase(new StartPhase());
    }
}
