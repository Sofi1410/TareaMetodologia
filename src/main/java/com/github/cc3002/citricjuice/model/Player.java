package com.github.cc3002.citricjuice.model;

import com.github.cc3002.citricjuice.model.board.IPanel;
import com.github.cc3002.citricliquid.gui.NormaGoal;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.List;

public class Player extends AbstractUnit implements IUnit {
    private IPanel actualPanel;
    private NormaGoal normaGoal;
    private int normaLevel;
    private IPanel homePanel;
    private PropertyChangeSupport normaLevelnotification= new PropertyChangeSupport(this);


    /**
     * Creates a new character.
     *
     * @param name the character's name.
     * @param hp   the initial (and max) hit points of the character.
     * @param atk  the base damage the character does.
     * @param def  the base defense of the character.
     * @param evd
     */

    public Player( String name, int hp, int atk, int def, int evd) {
        super(name,hp,atk, def, evd);
        this.actualPanel =null;
        normaLevel = 1;
        this.homePanel=null;
        this.normaGoal=NormaGoal.STARS;

    }


    /**
     * Returns the current norma level
     */
    public int getNormaLevel() {
        return normaLevel;
    }

    /**
     *
     * @return the normaGoal choosen by the player
     */
    public NormaGoal getNormaGoal(){
        return normaGoal;

    }
    /**
     *Sets the NormaGoal of the player
     */
    public void setNormaGoal(NormaGoal newNorma){
        normaGoal=newNorma;

    }
    /**
     * Returns the current panel
     * @return
     */
    public IPanel getPanel() {
        return actualPanel;
    }
    /**
     * updates de panel where the player is
     * @param newPanel new panel
     */
    public void setActualPanel(IPanel newPanel){
        actualPanel=newPanel;

    }
    /**
     * updates the home panel
     * for a player
     * @param newHomePanel new Home panel
     */
    public void setHomePanel(IPanel newHomePanel){
        homePanel=newHomePanel;

    }
    /**
     * Returns the player current HomePanel
     */
    public IPanel getHomePanel(){
        return homePanel;
    }

    /**
     * Performs a norma clear action; the {@code norma} counter increases in 1.
     * has a listener that advice when normaLevel of the player change
     */
    public void normaClear() {
        normaLevel++;
        normaLevelnotification.firePropertyChange("New_Normalevel",normaLevel-1,normaLevel);
    }


    /**
     * Decides if the goal selected by the player
     * was achieve or not
     */
    public void normaCheck(){
        if(normaGoal==NormaGoal.STARS){
            List<Integer> starGoal= List.of(10,30, 70, 120, 200);
            int stars=this.getStars();
            if(stars >= starGoal.get(normaLevel-1)){
                normaClear();
            }
        }
        if(normaGoal==NormaGoal.WINS){
            List<Integer> winsGoal= List.of(0,2, 5, 9, 14);
            int victories=getVictories();
            if(victories>= winsGoal.get(normaLevel-1)){
                normaClear();
            }

        }
    }


    /**
     * updates the evasion points
     * @param newEvd new Evd value
     */
    public void setEvd(int newEvd) {
        evd=newEvd;
    }

    /**
     * updates the attack points
     * @param newAtk new Atk value
     */
    public void setAtk(int newAtk) {
        atk=newAtk;
    }

    /**
     * updates the defend points
     * @param newDef new Def value
     */
    public void setDef(int newDef) {
        def=newDef;
    }


    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AbstractUnit)) {
            return false;
        }
        final Player player = (Player) o;
        return getMaxHP() == player.getMaxHP() &&
                getAtk() == player.getAtk() &&
                getDef() == player.getDef() &&
                getEvd() == player.getEvd() &&
                getNormaLevel() == player.getNormaLevel() &&
                getStars() == player.getStars() &&
                getCurrentHP() == player.getCurrentHP() &&
                getName().equals(player.getName());
    }

    /**
     * Add victories to the winner in a battle
     * @param IUnit loser
     */
    public void increaseVictoriesBy(IUnit IUnit) {
        IUnit.increaseVictoriesByPlayer(this);
    }

    /**
     * The loser add the appropriate amount of victories to the winner
     * @param wildUnit the winner
     */
    public void increaseVictoriesByWild(WildUnit wildUnit){
        wildUnit.setVictories(wildUnit.getVictories()+2);
    }
    /**
     * The loser add the appropriate amount of victories to the winner
     * @param bossUnit the winner
     */
    public void increaseVictoriesByBoss(BossUnit bossUnit){
        bossUnit.setVictories(bossUnit.getVictories()+2);
    }

    /**
     * The loser (Player) add the appropriate amount of victories to the winner
     * @param player the winner
     */
    public void increaseVictoriesByPlayer(Player player){
        player.setVictories(player.getVictories()+2);
    }

    /**
     * Add stars to the winner an reduce the stars to a
     * loser in a combat
     * @param IUnit loser
     */
    public void increaseStarsBy(IUnit IUnit) {
        IUnit.increaseStarsByPlayer(this);
    }

    /**
     * The loser (Player) add the appropriate amount of stars to the winner
     * an reduce it appropriately
     * @param wildUnit the winner
     */
    public void increaseStarsByWild(WildUnit wildUnit){
        wildUnit.increaseStarsBy((int) Math.floor(this.getStars()*0.5));
        this.reduceStarsBy((int) Math.ceil(this.getStars()*0.5));
    }

    /**
     * The loser (Player) add the appropriate amount of stars to the winner
     * an reduce it appropriately
     * @param bossUnit the winner
     */
    public void increaseStarsByBoss(BossUnit bossUnit){
        bossUnit.increaseStarsBy((int) Math.floor(this.getStars()*0.5));
        this.reduceStarsBy((int) Math.ceil(this.getStars()*0.5));
    }

    /**
     * The loser (Player) add the appropriate amount of stars to the winner
     * an reduce it appropriately
     * @param player the winner
     */
    public void increaseStarsByPlayer(Player player){
        player.increaseStarsBy((int) Math.floor(this.getStars()*0.5));
        this.reduceStarsBy((int) Math.ceil(this.getStars()*0.5));
    }

    /**
     * Method that adss a listener in the unit
     * @param Listener new Listener
     */
    public void addNormaLevelListener(PropertyChangeListener Listener){
        normaLevelnotification.addPropertyChangeListener(Listener);
    }


    /**
     *
     * @return a copy of the player
     */
    public Player copy() {
        return new Player(this.getName(), this.getMaxHP(), this.getAtk(), this.getDef(), this.getEvd());
    }

}
