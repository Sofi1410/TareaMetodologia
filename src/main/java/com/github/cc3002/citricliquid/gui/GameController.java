package com.github.cc3002.citricliquid.gui;

import com.github.cc3002.citricjuice.model.BossUnit;
import com.github.cc3002.citricjuice.model.Player;
import com.github.cc3002.citricjuice.model.WildUnit;
import com.github.cc3002.citricjuice.model.board.*;
import org.jetbrains.annotations.NotNull;
import java.util.Random;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class GameController {
    private final List<Player> listOfPlayers;
    private final List<IPanel> Panels;
    private Player owner;
    private int turn;
    int chapter;
    private Random random;
    private final Player winner = new Player("paloblanco", 2, 4, 5, 6);

    public GameController() {
        listOfPlayers = new ArrayList<>();
        Panels = new ArrayList();
        turn = 1;
        chapter = 1;
    }


    /**
     * Method that add a player
     * adds the player in the listOfPlayer
     * sets the panel where the playeer is
     * set the Home Panel
     *
     * @param name  the name
     * @param hp    HitPoints
     * @param atk   atack points
     * @param def   defense points
     * @param evd   evasion point
     * @param panel where the player is
     * @return the new Player
     */
    public Player addPlayer(String name, int hp, int atk, int def, int evd, @NotNull IPanel panel) {
        Player player = new Player(name, hp, atk, def, evd);
        listOfPlayers.add(player);
        panel.addPlayer(player);
        player.setActualPanel(panel);
        player.setHomePanel(panel);

        return player;
    }
    public void setHomePanel(Player player, HomePanel newHome) {
        player.setHomePanel(newHome);
    }

    /**
     * Set the Panel where the player is
     * remove the player from the panel where it was
     * and add it to the new panel
     *
     * @param player the player
     * @param panel  the new panel
     */
    public void setPlayerPanel(Player player, IPanel panel) {
        player.getPanel().removePlayer(player);
        player.setActualPanel(panel);
        panel.addPlayer(player);
    }

    /**
     * Create a BossUnit
     *
     * @param name name of the BossUnit
     * @param hp   HP points
     * @param atk  atk points
     * @param def  defense points
     * @param evd  evasion points
     * @return BossUnit the created Unit
     */
    public BossUnit addBossUnit(String name, int hp, int atk, int def, int evd) {
        return new BossUnit(name, hp, atk, def, evd);
    }

    /**
     * Create a WildUnit
     *
     * @param name name of the BossUnit
     * @param hp   HP points
     * @param atk  atk points
     * @param def  defense points
     * @param evd  evasion points
     * @return WildUnit the created Unit
     */
    public WildUnit addWildUnit(String name, int hp, int atk, int def, int evd) {
        return new WildUnit(name, hp, atk, def, evd);
    }

    /**
     * Method that return the
     * list with all the player
     *
     * @return listOfPlayers
     */
    public List<Player> getListOfPlayers() {
        return listOfPlayers;
    }

    //Creating new Panels with their own coordinates

    /**
     * Method that create a new Bonus Panel
     * and add it to the list of All the Panel
     *
     * @param id the id of the new Panel
     * @return new Bonus Panel
     */
    public BonusPanel addBonusPanel(int id) {
        BonusPanel NP = new BonusPanel(id);
        Panels.add(NP);
        return NP;
    }

    /**
     * Method that create a new Boss Panel
     * and add it to the list of All the Panels
     *
     * @param id the id of the new Panel
     * @return new Boss Panel
     */
    public BossPanel addBossPanel(int id) {
        BossPanel NP = new BossPanel(id);
        Panels.add(NP);
        return NP;
    }

    /**
     * Method that create a new Draw Panel
     * and add it to the list of All the Panel
     *
     * @param id the id of the new Panel
     * @return new Draw Panel
     */
    public DrawPanel addDrawPanel(int id) {
        DrawPanel NP = new DrawPanel(id);
        Panels.add(NP);
        return NP;
    }

    /**
     * Method that create a new Drop Panel
     * and add it to the list of All the Panels
     *
     * @param id the id of the new Panel
     * @return new Drop Panel
     */
    public DropPanel addDropPanel(int id) {
        DropPanel NP = new DropPanel(id);
        Panels.add(NP);
        return NP;
    }

    /**
     * Method that create a new Encounter Panel
     * and add it to the list of All the Panel
     *
     * @param id the id of the new Panel
     * @return new Encounter Panel
     */
    public EncounterPanel addEncounterPanel(int id) {
        EncounterPanel NP = new EncounterPanel(id);
        Panels.add(NP);
        return NP;
    }

    /**
     * Method that create a new Bonus Panel
     * and add it to the list of All the Panel
     *
     * @param id the id of the new Panel
     * @return new Home Panel
     */
    public HomePanel addHomePanel(int id) {
        HomePanel NP = new HomePanel(id);
        Panels.add(NP);
        return NP;
    }

    /**
     * Method that create a new NeutralPanel
     * and add it to the list of All the Panel
     *
     * @param id the id of the new Panel
     * @return new Neutral Panel
     */
    public NeutralPanel addNeutraLPanel(int id) {
        NeutralPanel NP = new NeutralPanel(id);
        Panels.add(NP);
        return NP;
    }

    /**
     * Return a list with all the panel
     * next to actual
     *
     * @param actual the panel
     * @return list with the next panel
     * of actual
     */
    public Set<IPanel> getNextPanels(IPanel actual) {
        return actual.getNextPanels();
    }

    /**
     * Method that set that newpanel is
     * next to actual
     *
     * @param actual   actual panel
     * @param newpanel panel next to actual
     */
    public void setNextPanel(IPanel actual, IPanel newpanel) {
        actual.addNextPanel(newpanel);
    }


    public void activadedBy() {

    }


    /**
     *
     */
    private void changeTurn() {
    }

    /**
     * @return
     */
    public Set<IPanel> getPanels() {
        return Set.copyOf(Panels);
    }

    /**
     * @return
     */
    public Player getWinner() {
        return winner;
    }

    public Player getOwner() {
        owner = listOfPlayers.get((turn - 1) % 4);
        return owner;
    }


    /**
     *
     */
    public void finishTurn() {
        if (turn % 4 == 0) {
            chapter++;
        }
        setTurn(turn + 1);
    }

    public void setTurn(int turn) {
        this.turn = turn;

    }

    public int getNormaLevel() {

        return getOwner().getNormaLevel();
    }

    public int getChapter() {
        return chapter;
    }

    public void setNormaGoal(NormaGoal goal) {
        getOwner().setNormaGoal(goal);
    }

    public int dice() {
        int dice = getOwner().roll();
        return dice;
    }
    public void movePlayer(){
        int steps = dice();
        while (steps>0) {
            if (step(getOwner(), getOwner().getPanel())) {
                break;
            }
            else {
                steps = steps - 1;
            }

        }
        getOwner().getPanel().activateBy(getOwner());
    }
    public boolean step(Player owner,IPanel actualPanel){
        int size = actualPanel.getNextPanels().size();
        if (size > 1) {
            return true;
        }
        else {
            IPanel nextPanel =  actualPanel.getNextPanels().iterator().next();
            setPlayerPanel(getOwner(),nextPanel);
            if(nextPanel.getId()==owner.getHomePanel().getId()){
                return true;
            }
            else if(nextPanel.getPlayers().size()>1){
                return true;
            }
            else{
                return false;
            }
        }
    }
    public void uwu(){
        
    }

}

