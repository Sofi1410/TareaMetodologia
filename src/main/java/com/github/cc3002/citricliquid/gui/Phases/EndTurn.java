package com.github.cc3002.citricliquid.gui.Phases;

public class EndTurn extends Phase{
    public EndTurn(){
        this.canIStart=false;
        this.canIMove=false;
        this.canFight=false;
        this.recover=false;
        this.stayAtHome=false;
        this.canIfinish=true;

    }

    @Override
    public void endTurn() throws InvalidMovementException {
       super.endTurn();
       toStartPhase();
    }
    @Override
    public void toStartPhase(){
        changePhase(new StartPhase());
    }
}
