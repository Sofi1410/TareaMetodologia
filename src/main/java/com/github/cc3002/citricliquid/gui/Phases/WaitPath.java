package com.github.cc3002.citricliquid.gui.Phases;

public class WaitPath extends Phase{
    public  WaitPath(){
        this.canIStart=true;
        this.canIMove=false;
        this.canFight=false;
        this.recover=false;
        this.stayAtHome=false;
        this.canIfinish=false;
        this.WaitAtHome=false;
        this.WaitTOFigth=false;
        this.WaitToPath=true;
        this.Battle=false;

    }

    @Override
    public String toString() {
        return "WaitPath_Phase";
    }
    @Override
    public void toMovingPhase(){
        changePhase(new MovingPhase());
    }

    @Override
    public void left() throws InvalidTransitionException {
        controller.left();

    }
    @Override
    public void right() throws InvalidTransitionException {
        controller.right();

    }
    @Override
    public void up() throws InvalidTransitionException {
        controller.up();

    }
    @Override
    public void down() throws InvalidTransitionException {
        controller.down();

    }



}
