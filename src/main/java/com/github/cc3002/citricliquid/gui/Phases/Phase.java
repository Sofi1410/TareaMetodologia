package com.github.cc3002.citricliquid.gui.Phases;

import com.github.cc3002.citricliquid.gui.GameController;
import com.github.cc3002.citricjuice.model.Unit.IUnit;

public class Phase {

    protected GameController controller ;
    public boolean canFight;
    public boolean stayAtHome;
    public boolean canIStart;
    public boolean canIMove;
    public boolean recover;
    public boolean canIfinish;
    public boolean WaitAtHome;
    public boolean WaitTOFigth;
    public boolean WaitToPath;
    public boolean Battle;


    public void setController(GameController controller){
        this.controller=controller;
    }

    protected void changePhase(Phase phase){
        controller.setPhase(phase);
    }

    /**
     * Method that
     * @return the name of the actual Phase
     */
    public String toString(){
        return "Phase";
    }


    //Methods Zone

    /**
     * metodo para empezar el turno
     * a recover phase si el jugador esta KO
     * a movePhase para qque este se mueva
     * @throws InvalidMovementException para el movimiento
     * @throws InvalidTransitionException para la transicion
     */
    public void start() throws InvalidMovementException, InvalidTransitionException {
        if(!canIStart&& !controller.itsK_O()){
            throw new InvalidMovementException("You can't start at" + toString());
        }
        else if(canIStart && controller.itsK_O()){
            toRecoveryPhase();
            controller.tryToRecover();
        }
        else{
            toMovingPhase();
            controller.trydotheFirstMove();
        }
    }
    /**
     * recupera al jugador
     * @throws InvalidMovementException para el movimiento
     * @throws InvalidTransitionException para la transicion
     */
    public void recover() throws InvalidMovementException, InvalidTransitionException {
        if(!recover){
            throw new InvalidMovementException("You can't recover at" + toString());
        }
        controller.recover();

    }

    /**
     * Realiza el primer movimiento (se tiran dados)
     * @throws InvalidMovementException si no se puede realizar la acción
     */
    public void firstMove() throws InvalidMovementException{
        if (!canIMove) {
            throw new InvalidMovementException("You can't move at" + toString());
        }
        controller.move();
    }

    /**
     * Meetodo que mueve al jugador
     * @throws InvalidMovementException si no se puede
     */
    public void move() throws  InvalidMovementException{
        if (!canIMove) {
            throw new InvalidMovementException("You can't move now.");
        }
        controller.movePlayer();
    }

    /**
     * Metodo para seguir avazando
     * @throws InvalidMovementException
     * @throws InvalidTransitionException
     */
    public void keepMoving() throws InvalidMovementException, InvalidTransitionException {
        if(!WaitAtHome &&!WaitTOFigth && !WaitToPath&&!canIMove){
            throw new InvalidMovementException("You can't keep moving at: "+toString());
        }

        controller.tryToMove();
    }

    /**
     * para subir
     * @throws InvalidMovementException
     * @throws InvalidTransitionException
     */
    public void up() throws InvalidMovementException, InvalidTransitionException {
        throw new InvalidMovementException("You can´t chooce Path at :"+ toString());
    }

    /**
     * para bajar
     * @throws InvalidMovementException
     * @throws InvalidTransitionException
     */
    public void down() throws InvalidMovementException, InvalidTransitionException {
        throw new InvalidMovementException("You can´t chooce Path at :"+ toString());
    }

    /**
     * para tomar el camino e la izquierda
     * @throws InvalidMovementException
     * @throws InvalidTransitionException
     */
    public void left() throws InvalidMovementException, InvalidTransitionException {
        throw new InvalidMovementException("You can´t chooce Path at :"+ toString());
    }

    /**
     * para tomar el camino de la deerecha
     * @throws InvalidMovementException
     * @throws InvalidTransitionException
     */
    public void right() throws InvalidMovementException, InvalidTransitionException {
        throw new InvalidMovementException("You can´t chooce Path at :"+ toString());
    }

    /**
     * cuando se desea terminar el turno
     * @throws InvalidMovementException
     */
    public void endTurn() throws InvalidMovementException {
        if(!canIfinish){
            throw new InvalidMovementException("Can't end turn on "
                    + this.toString() + ".");
        }
        controller.finishTurn();
    }

    /**
     * cuando se intenta quedarse en casa
     * @throws InvalidMovementException
     */
    public void stayAtHome() throws InvalidMovementException{
        if (!WaitAtHome) {
            throw new InvalidMovementException("You can't chooce to stay at home at" + toString());
        }


    }


    /**
     * metodo para atacar
     * @throws InvalidMovementException
     */
    public void attack() throws InvalidMovementException {
        if (!canFight) {
            throw new InvalidMovementException("You can't move now.");
        }
    }

    /**
     * cuando se decide pelear
     * @throws InvalidMovementException
     */
    public void iAmGoingToFigth() throws InvalidMovementException {
        if(controller.itsK_O()|!WaitTOFigth){
            throw new InvalidMovementException("You can't fight at: " + toString());
        }

    }


    /**
     * cuando se decide evadir
     * @throws InvalidMovementException
     * @throws InvalidTransitionException
     */
    public void evade() throws InvalidMovementException, InvalidTransitionException {
        if(!WaitTOFigth){
            throw new InvalidMovementException("You can't evade at: " + toString());
        }
    }

    /**
     * se decide defender
     * @throws InvalidMovementException
     * @throws InvalidTransitionException
     */
    public void defend() throws InvalidMovementException, InvalidTransitionException {
        if(!WaitTOFigth){
            throw new InvalidMovementException("You can't evade at: " + toString());
        }
    }



    //Transition Zone

    /**
     * Change the phase to Recovery Phase if
     * the actual phase is StartPhase
     * @throws InvalidTransitionException in case of you
     * are not at the rigth phase
     */
    public void toRecoveryPhase() throws InvalidTransitionException {
        throw new InvalidTransitionException(
                    "Can't change from " + this.toString() + " to Recovery phase");

    }

    /**
     * Allows to change the phase if
     * tha actual phase is RecoveryPhase
     * @throws InvalidTransitionException in case of you
     * are not in the rigth phase to make the change
     */
    public void toStartPhase() throws InvalidTransitionException {
        throw new InvalidTransitionException(
                    "Can't change from " + this.toString() + " to StartPhase phase");

    }

    /**
     * Allows to change the phase if
     * your actual phase is Start Phase, WaitHome, WaitPath
     * or WaitFigth
     * @throws InvalidTransitionException in case of you are
     * not in the rigth phase to make the change
     */
    public void toMovingPhase() throws InvalidTransitionException {
        throw new InvalidTransitionException(
                    "Can't change from " + this.toString() + " to MovingPhase phase");

    }

    /**
     * Allows to go to WaithPath Phase if you
     * are at MovingPhase
     * @throws InvalidTransitionException in case of you are
     * not in the rigth phase to make the change
     */
    public void toWaitPathPhase() throws InvalidTransitionException {
        throw new InvalidTransitionException(
                "Can't change from " + this.toString() + " to WaitPath phase");
    }

    /**
     * Allows to go to WaithHome Phase if you
     * are at MovingPhase
     * @throws InvalidTransitionException in case of you are
     * not in the rigth phase to make the change
     */
    public void toWaitHomePhase() throws InvalidTransitionException {
        throw new InvalidTransitionException(
                "Can't change from " + this.toString() + " to WaitHomePhase phase");
    }
    /**
     * Allows to go to WaithFight Phase if you
     * are at MovingPhase or BattlePhase
     * @throws InvalidTransitionException in case of you are
     * not in the rigth phase to make the change
     */
    public void toWaitFigthPhase(IUnit attacker, IUnit victim) throws InvalidTransitionException {
        throw new InvalidTransitionException(
                "Can't change from " + this.toString() + " to WaitFigthPhase phase");
    }

    /**
     * Allows to go to BattlePhase if you
     * are at WaitFight Phase
     * @throws InvalidTransitionException in case of you are
     * not in the rigth phase to make the change
     */
    public void toBattlePhase(IUnit attacker,IUnit opponent) throws InvalidTransitionException {
        throw new InvalidTransitionException(
                "Can't change from " + this.toString() + " to BattlePhase phase");
    }
    /**
     * Allows to go to EndTurn Phase if you
     * are
     * @throws InvalidTransitionException in case of you are
     * not in the rigth phase to make the change
     */
    public void toEndTurnPhase() throws InvalidTransitionException {
        throw new InvalidTransitionException(
                    "Can't change from " + this.toString() + " to End Turn phase");

    }










}
