package com.github.cc3002.citricliquid.gui.Phases;

import com.github.cc3002.citricjuice.model.GameController;
import com.github.cc3002.citricjuice.model.IUnit;
import org.jetbrains.annotations.NotNull;
public class Phase {

    protected GameController controller ;
    public boolean canFight;
    public boolean stayAtHome;
    public boolean canIStart;
    public boolean canIMove;
    public boolean recover;


    public void setController(GameController controller){
        this.controller=controller;
    }

    protected void changePhase(Phase phase){
        controller.setPhase(phase);
    }

    public void endTurn() throws InvalidMovementException {
        throw new InvalidMovementException("Can't end turn on "
                + this.toString() + ".");
    }
    public String toString(){
        return "Phase";
    }

    public void firstMove() throws InvalidMovementException {
        if (!canIMove) {
            throw new InvalidMovementException("You can't move now.");
        }
        controller.move();
    }
    public void move() throws  InvalidMovementException{
        if (!canIMove) {
            throw new InvalidMovementException("You can't move now.");
        }
        controller.movePlayer();
    }

    public void start() throws InvalidMovementException {
        if(!canIStart&& !controller.itsK_O()){
            throw new InvalidMovementException("You can't start now.");
        }
        else if(canIStart && controller.itsK_O()){
            toRecoveryPhase();
        }
        else{toMovingPhase();}
    }

    public void tryToRecover() throws InvalidMovementException {
        if(!recover){
            throw new InvalidMovementException("You can't recover now.");
        }
        else {
            controller.recover();
        }
    }
    public void tryToattack() throws InvalidMovementException {
        if (!canFight) {
            throw new InvalidMovementException("You can't move now.");
        }
    }

    public void toMovingPhase() {
    }

    public void toRecoveryPhase() {
    }
    public void toBattlePhase() throws InvalidMovementException {
    }

    public void toEndTurnPhase() {}


    public void toWaitFigthPhase() {}

    public void toWaitPathPhase() {}
    public void toWaitHomePhase() {}
    public void toStartPhase(){}
    public void stayAtHome(){}
    public void keepMoving() throws InvalidMovementException {
        controller.setCanIMove(true);
        toMovingPhase();
        controller.getPhase().move();
    }
    public void iAmGoingToFigth() throws InvalidMovementException {}
    public void iAmNotGoingToFigth() throws InvalidMovementException {}

    public void evade(){}


    public void toOpponentChoicePhase(IUnit attacker,IUnit victim) {
    }
}
