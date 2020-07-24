package com.github.cc3002.citricliquid.gui.Phases;

import com.github.cc3002.citricjuice.model.IUnit;

public class WaitFight extends Phase{
    IUnit attacker;
    IUnit opponent;



    public WaitFight(IUnit attacker, IUnit victim){
        this.canIStart=false;
        this.canIMove=false;
        this.canFight=false;
        this.recover=false;
        this.stayAtHome=false;
        this.canIfinish=false;
        this.WaitAtHome=false;
        this.WaitTOFigth=true;
        this.WaitToPath=false;
        this.Battle=false;;
        this.attacker=attacker;
        this.opponent=victim;
    }

    @Override
    public String toString() {
        return "WaitFight_Phase";
    }


    @Override
    public void iAmGoingToFigth() throws InvalidMovementException {
        toBattlePhase(attacker,opponent);
        controller.setSteps(0);
        controller.setActualUnit(opponent);
        controller.getPhase().attack();
    }

    @Override
    public void iAmNotGoingToFigth() {
        controller.setiMakeADecision(true);

    }
    @Override
    public void evade() throws InvalidTransitionException {
        controller.evade(attacker,opponent);
    }
    @Override
    public void defend() throws InvalidTransitionException {
        controller.defend(attacker,opponent);
    }

    @Override
    public void toMovingPhase() {
        changePhase(new MovingPhase());


    }
    @Override
    public void toBattlePhase(IUnit attacker, IUnit victim) {
        changePhase(new BattlePhase(attacker,victim));

    }


    /**
     * Allows to go to WaithFight Phase if you
     * are at MovingPhase or BattlePhase
     *
     * @param attacker
     * @param victim
     * @throws InvalidTransitionException in case of you are
     *                                    not in the rigth phase to make the change
     */
    @Override
    public void toWaitFigthPhase(IUnit attacker, IUnit victim) {
        changePhase(new WaitFight(attacker,victim));
    }
}
