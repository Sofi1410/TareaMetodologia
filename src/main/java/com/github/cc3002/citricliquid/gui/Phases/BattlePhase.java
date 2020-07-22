package com.github.cc3002.citricliquid.gui.Phases;

import com.github.cc3002.citricjuice.model.IUnit;

public class BattlePhase extends Phase {
    IUnit unit;
    IUnit opponent;
    public BattlePhase(IUnit unit, IUnit victim){
        this.canIStart=false;
        this.canIMove=false;
        this.canFight=true;
        this.canIStart=false;
        this.unit=unit;
        this.opponent=victim;
    }

    @Override
    public String toString() {
        return "Battle_Phase";
    }

    @Override
    public void tryToattack() throws InvalidMovementException {
        if (!canFight) {
            throw new InvalidMovementException("You can't move now.");
        }
        controller.battle(unit,opponent);
    }

    @Override
    public void toOpponentChoicePhase(IUnit attacker, IUnit victim) {
        changePhase(new OpponentChoicePhase(attacker,victim));
    }
}
