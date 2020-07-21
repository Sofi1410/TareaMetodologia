package com.github.cc3002.citricliquid.gui.Phases;

import com.github.cc3002.citricjuice.model.GameController;
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

    public void move() throws InvalidMovementException {
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

    public void toMovingPhase() {
    }

    public void toRecoveryPhase() {
    }
    public void toBattlePhase() {
    }

    public void toEndTurnPhase() {
    }


    public void toWaithFigthPhase() {
    }

    public void toWaithPathPhase() {
    }

    public void toWaithHomePhase() {
    }
    public void toStartPhase(){

    }
}
