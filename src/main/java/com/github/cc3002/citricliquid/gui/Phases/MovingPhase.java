package com.github.cc3002.citricliquid.gui.Phases;

public class MovingPhase extends Phase{
    public MovingPhase(){
        this.canIStart=false;
        this.canIMove=true;
        this.canFight=false;
        this.canIStart=false;


    }

    @Override
    public void move() throws InvalidMovementException {
        super.move();
    }
}
