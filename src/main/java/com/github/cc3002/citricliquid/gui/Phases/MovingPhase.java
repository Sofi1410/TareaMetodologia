package com.github.cc3002.citricliquid.gui.Phases;

import com.github.cc3002.citricjuice.model.Unit.IUnit;

public class MovingPhase extends Phase{
    public MovingPhase(){
        this.canIStart=false;
        this.canIMove=true;
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
        return "Moving_Phase";
    }

    @Override
    public void toWaitPathPhase() {
        changePhase(new WaitPath());
    }

    @Override
    public void toWaitHomePhase() {
        changePhase(new WaitHome());
    }

    @Override
    public void toWaitFigthPhase(IUnit attacker, IUnit victim) {
        changePhase(new WaitFight(attacker, victim));
    }

    @Override
    public void toEndTurnPhase(){
        changePhase(new EndTurn());
    }
}
