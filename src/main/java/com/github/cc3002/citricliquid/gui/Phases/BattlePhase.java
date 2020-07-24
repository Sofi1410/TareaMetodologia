package com.github.cc3002.citricliquid.gui.Phases;

import com.github.cc3002.citricjuice.model.IUnit;

public class BattlePhase extends Phase {
    IUnit attacker;
    IUnit opponent;
    public BattlePhase(IUnit attacker,IUnit victim){
        this.canIStart=false;
        this.canIMove=false;
        this.canFight=true;
        this.canIStart=false;
        this.attacker=attacker;
        this.opponent=victim;
    }

    @Override
    public String toString() {
        return "Battle_Phase";
    }

    @Override
    public void attack(){
        toWaitFigthPhase(attacker,opponent);
    }


    @Override
    public void toWaitFigthPhase(IUnit attacker, IUnit victim) {
        changePhase(new WaitFight(attacker,victim));
    }
}
