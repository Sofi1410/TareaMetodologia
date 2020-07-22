package com.github.cc3002.citricliquid.gui.Phases;

public class MovingPhase extends Phase{
    public MovingPhase(){
        this.canIStart=false;
        this.canIMove=true;
        this.canFight=false;
        this.recover=false;


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
    public void toWaitFigthPhase() {
        changePhase(new WaitFight());
    }
}
