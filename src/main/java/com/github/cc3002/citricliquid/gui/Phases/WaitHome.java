package com.github.cc3002.citricliquid.gui.Phases;

public class WaitHome extends Phase{
    public WaitHome(){
        this.canIStart=false;
        this.canIMove=false;
        this.canFight=false;
        this.recover=false;
        this.stayAtHome=false;
        this.canIfinish=false;
        this.WaitAtHome=true;
        this.WaitTOFigth=false;
        this.WaitToPath=false;
    }
    @Override
    public String toString() {
        return "WaitHome_Phase";
    }
    @Override
    public void stayAtHome() {
        toEndTurnPhase();
        controller.tryToEndTurn();
    }




    @Override
    public void toMovingPhase() {
        changePhase(new MovingPhase());

    }

    @Override
    public void toEndTurnPhase() {
        changePhase(new EndTurn());
    }

}
