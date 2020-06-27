package com.github.cc3002.citricliquid.gui.Phases;
import com.github.cc3002.citricjuice.model.BossUnit;
import com.github.cc3002.citricjuice.model.IUnit;
import com.github.cc3002.citricjuice.model.Player;
import com.github.cc3002.citricjuice.model.WildUnit;
import com.github.cc3002.citricjuice.model.board.IPanel;

import java.util.Iterator;
import java.util.List;
import java.util.Random;
/**
public class Phases {
     * The player(owner of the turn) is in a panel with another player
     * has to decided if it wants to attack or not
    private boolean decision;
    private Player owner;
    private List<Player> listOfPlayers;
    public Phases(List<Player> listOfPlayers, Player owner){
        this.owner=owner;
        this.listOfPlayers=listOfPlayers;
    }

    public void setOwner(Player owner) {
        this.owner = owner;
    }

    public void begintheTurnPhases() {
        int dice= owner.roll();
        /*
          tire los dados , eso e slo primero
          después si está en K.O llevarlo al recovery phase
          si los dados lo salva que se setea , ya no está K.O y
          vuelve acá
        if(owner.isK_O()){
            recoveryPhase(dice,dice);
        }
        else {
            movingPhase(dice);
        }
    }
    public void movingPhase(int steps){
        int count=steps;
        while (count>=1) {
            IPanel pastPanel=owner.getPanel();
            pastPanel.removePlayer(owner);
            int id=nextMoveWaitPhase();
            IPanel actualPanel=null;
            for (Iterator<IPanel> it = pastPanel.getNextPanels().iterator(); it.hasNext(); ) {
                IPanel f = it.next();
                if (f.getId()==id) {
                    actualPanel=f;
                }
            }
            owner.setActualPanel(actualPanel);
            steps-=1;
            analizePanelPhase(steps);
            if(count==0){
                activePanelPhase();
                endTurn();
            }
        }



    }

    private int nextMoveWaitPhase() {
        int id = 0;
        return id;
    }

    public void analizePanelPhase(int steps){
        //si hay mas de un panel siguiente decide que hacer
        //stepWaitPhase(LEFT,RIGHT,BACK,)
        //si hay otro jugador decidir si pelear o no
        //combatWaitPhase
        //Si llega a su home panel decidir se quiere quedar ahí o seguir
        //stayAtHomeWaitPhase(si sigue avanzando se mantiene el movig Phase hasta que no queden mas pasos)
        //si no pasa nada de esto llega a un panel y ahí
        //activadePanelPhase
        if(owner.getPanel().getPlayers().size() >1){
            if(combatWaitPhase()){
                combatPhase();
            }
            else{
                movingPhase(steps);
            }
        }
        if(owner.getPanel().equals(owner.getHomePanel())){
            if(stayAtHomeWaitPhase()){
                homePanelPhase();
            }
            else{
                movingPhase(steps);
            }
        }
    }

    private void combatPhase() {
    }

    private boolean combatWaitPhase() {
        return true;

    }

    public boolean stayAtHomeWaitPhase(){
        // decidir si sigue avanzando o no
        //si sigue avazando movingPhase con los pasos restante
        // si se queda en el homePanel
        //HomePanelPhase
        return false;
    }
    public void homePanelPhase(){
        //recuperar puntos de salud
        //normacheck
    }
    public void activePanelPhase(){
        owner.getPanel().activateBy(owner);
    }


    public void setDecision(boolean decision) {
        this.decision = decision;
    }
    public void CombatPhase(){
        if(decision){

        }
    }
    public void recoveryPhase(int dice,int chapter){
        int recovery=7-chapter;
        if (recovery <= dice){
            owner.setEvd(owner.copy().getEvd());
            owner.setAtk(owner.copy().getAtk());
            owner.setEvd(owner.copy().getEvd());
            owner.setCurrentHP(owner.copy().getMaxHP());
        }
        else{
            endTurn();
        }
    }

    private void endTurn() {
    }


}
*/
