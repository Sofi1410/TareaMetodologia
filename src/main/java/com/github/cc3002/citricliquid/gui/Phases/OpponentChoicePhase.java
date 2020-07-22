package com.github.cc3002.citricliquid.gui.Phases;

import com.github.cc3002.citricjuice.model.IUnit;

public class OpponentChoicePhase extends Phase{
    IUnit victim;
    IUnit attacker;
    public OpponentChoicePhase(IUnit attacker,IUnit victim){
        this.canIStart=false;
        this.canIMove=false;
        this.canFight=false;
        this.canIStart=false;
        this.victim=victim;
        this.attacker=attacker;

    }

    @Override
    public String toString() {
        return "OpponentChoice_Phase";
    }

    @Override
    public void evade(){
        controller.evade(attacker,victim);
    }

    @Override
    public void tryToattack(){}
}


