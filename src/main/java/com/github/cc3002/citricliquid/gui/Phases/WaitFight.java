package com.github.cc3002.citricliquid.gui.Phases;

public class WaitFight extends Phase{

    public void WaithFight(){
        this.canIStart=false;
        this.canIMove=true;
        this.canFight=false;
        this.canIStart=false;
    }

    @Override
    public String toString() {
        return "WaitFight_Phase";
    }


    @Override
    public void iAmGoingToFigth() throws InvalidMovementException {
        toBattlePhase();
        controller.getPhase().tryToattack();
    }

    @Override
    public void iAmNotGoingToFigth() throws InvalidMovementException {
        keepMoving();
    }

    @Override
    public void toMovingPhase() {
        changePhase(new MovingPhase());

    }
    @Override
    public void toBattlePhase() {
        changePhase(new BattlePhase(controller.getOwner(),controller.getOponnent()));

    }



}
