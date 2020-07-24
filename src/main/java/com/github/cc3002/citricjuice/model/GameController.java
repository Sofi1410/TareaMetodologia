package com.github.cc3002.citricjuice.model;

import com.github.cc3002.citricjuice.model.board.*;
import com.github.cc3002.citricliquid.gui.*;
import com.github.cc3002.citricliquid.gui.Phases.InvalidMovementException;
import com.github.cc3002.citricliquid.gui.Phases.InvalidTransitionException;
import com.github.cc3002.citricliquid.gui.Phases.Phase;
import com.github.cc3002.citricliquid.gui.Phases.StartPhase;
import org.jetbrains.annotations.NotNull;

import java.util.*;

public class GameController {
    private final List<Player> listOfPlayers;
    private final List<IPanel> Panels;
    private Player owner;
    private Player nullPLayer=new Player("No one yet",0,0,0,0);
    private IUnit oponnent= nullPLayer;
    private IUnit actualUnit=nullPLayer;
    private int numberOfOpponents;
    private int turn;
    private boolean iMakeADecision;
    int chapter;
    int steps;
    private final NormaLevelObserver NormaLevelnotification = new NormaLevelObserver(this);
    private final MoreThan1playerObserver moreTanOnePlayernotification=  new MoreThan1playerObserver(this);
    private final MoreThanOnePathObserver moreTanOnePathnotification=  new MoreThanOnePathObserver(this);
    private final AtHomePanelObserver atHomePanelNotification=  new AtHomePanelObserver(this);

    private Player winner= nullPLayer;
    private Phase phase;

    /**
     * Constructor for the controller
     * It has a list with all the players created in the controller
     * a list with all the panels created in the controller
     * var turn that represents the turn of the players
     * chapter it means how many rounds had been
     */
    public GameController() {
        listOfPlayers = new ArrayList<>();
        Panels = new ArrayList();
        turn = 1;
        chapter = 1;
        phase= new Phase();
        setPhase(new StartPhase());

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
        player.setHomePanel(panel);
        player.setActualPanel(panel);


        return player;
    }

    /**
     * Mthod that sets the HomePanel in a player
     * @param player the player
     * @param newHome new HomePanel
     */
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
        player.getPanel().getPlayers().remove(player);
        panel.addPlayer(player);
        player.setActualPanel(panel);


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
    public NeutralPanel addNeutralPanel(int id) {
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
    public void setNextPanel(@NotNull IPanel actual, IPanel newpanel) {
        actual.addNextPanel(newpanel);
    }




    /**
     * Method that returns a list with all the panels created in
     * the controller
     * @return todos los paneles en el juego
     */
    public Set<IPanel> getPanels() {
        return Set.copyOf(Panels);
    }

    /**
     * @return
     */

    /**
     * These method is use when the observer advices that a player
     * has NormaLevel=6
     * in that case this player will be the owner of the turn , thats why returns
     * the owner
     * @return the winner of the game
     */
    public Player getWinner() {
        return winner;
    }

    /**
     * Acording to the var turn in the class
     * this method will extract the current player(owner
     * of the turn) from the list of Players
     * @return player owner of the turn
     */
    public Player getOwner() {
        owner = listOfPlayers.get((turn - 1) % listOfPlayers.size());

        return owner;
    }


    /**
     *When a player cant do anithing alse
     * finishturn() sets the value of turn
     * in order to change the ower of the turn
     */
    public void finishTurn() {
        if (turn % listOfPlayers.size() == 0) {
            chapter++;
        }
        setSteps(0);
        activatePanel(getOwner(),getOwner().getPanel());
        owner.addNormaLevelListener(NormaLevelnotification);
        setTurn(turn + 1);

    }

    /**
     * when is no longer the turn of  player
     * we use this method to change the value of turn
     * @param turn new value
     */
    public void setTurn(int turn) {
        this.turn = turn;
        actualUnit=getOwner();
    }


    /**
     *
     * @return the current chapter
     */
    public int getChapter() {
        return chapter;
    }

    /**
     * When we want to change the NormaGoal for the owner
     * we use setNormaGoal
     * @param goal new NormaGoal
     */
    public void setNormaGoal(NormaGoal goal) {
        getOwner().setNormaGoal(goal);
    }

    /**
     * Method that
     * @return the value of the dice
     * play by the owner
     */
    public int dice() {
        int dice = getOwner().roll();
        return dice;
    }


    public void setSteps(int newValue){
        steps=newValue;
    }

    public void setOponnent(IUnit oponnent) {
        this.oponnent = oponnent;
    }
    public IUnit getOponnent(){
        return oponnent;
    }
    public void setActualUnit(IUnit unit){
        this.actualUnit=unit;
    }
    public IUnit getActualUnit(){
        return actualUnit;
    }

    /**
     * Method that decided what to do with the observer advice
     * in this case when the norma check of the player sets NormaLevel=6
     * this method advice that someone win the game
     * @param newValue new normaLevel for the player playing
     */
    public void onNewNormaLevel(int newValue) {
        if (newValue==6){
            winner=getOwner();
        }
    }

    public void setPhase(Phase phase) {
        this.phase = phase;
        phase.setController(this);
    }

    public String getCurrentPhase(){
        return phase.toString();
    }
    public Phase getPhase(){
        return phase;
    }


    public boolean itsK_O(){
        return getOwner().isK_O();
    }

    public int attack(IUnit unit){
        return unit.attack();
    }



    public void evade(IUnit attacker,IUnit victim) throws InvalidTransitionException {
        int attack=attack(attacker);
        victim.evade(attack);
        if(!victim.isK_O()) {
            counterattack(victim, attacker);
        }
        else{
            phase.toMovingPhase();
        }
    }
    public void defend(IUnit attacker, IUnit victim) throws InvalidTransitionException {
        int attack=attack(attacker);
        victim.defend(attack);
        if(!victim.isK_O()) {
            counterattack(victim, attacker);
        }
        else{
            phase.toMovingPhase();
        }
    }
    public void counterattack(IUnit attacker, IUnit victim){
        if(!attacker.isK_O()){
            try {

                phase.toWaitFigthPhase(attacker,victim);
            } catch (InvalidTransitionException e) {
                e.printStackTrace();
            }
            tryToFight();
        }
        else{
            tryToEndTurn();
        }

    }


    public void setCanIMove(boolean newValue){
        getOwner().setCanImove(newValue);
    }

    public void moreThanOnePlayer(){

    }


    // Inicio del juego
    //intento iniciar
    public void tryToStart(){
        try {
            this.setActualUnit(getOwner());
            phase.start();
        } catch (InvalidMovementException | InvalidTransitionException e) {
            e.printStackTrace();
        }

    }

    public void tryToRecover(){
        try {
            phase.recover();
        } catch (InvalidMovementException | InvalidTransitionException e) {
            e.printStackTrace();
        }
    }
    public void trydotheFirstMove(){
        try {
            phase.firstMove();
        } catch (InvalidMovementException e) {
            e.printStackTrace();
        }
    }
    public void tryToMove(){
        try {
            phase.move();
        } catch (InvalidMovementException e) {
            e.printStackTrace();
        }
    }
    public void tryToKeepMoving(){
        try {
            phase.keepMoving();
        } catch (InvalidTransitionException | InvalidMovementException e) {
            e.printStackTrace();
        }

    }
    public void tryToStayAtHome(){
        try {
            phase.stayAtHome();
        } catch (InvalidMovementException e) {
            e.printStackTrace();
        }
    }
    public void tryToFight(){
        try {
            phase.iAmGoingToFigth();
        } catch (InvalidMovementException e) {
            e.printStackTrace();
        }
    }
    public void tryToevade(){
        try{
            phase.evade();
        }catch (InvalidMovementException | InvalidTransitionException e){
            e.printStackTrace();
        }
    }
    public void tryToDefend(){
        try{
            phase.defend();
        }catch (InvalidMovementException | InvalidTransitionException e){
            e.printStackTrace();
        }
    }


    public void tryToEndTurn(){
        try {
            phase.endTurn();
        } catch (InvalidMovementException e) {
            e.printStackTrace();
        }
    }

    public void stopMoving(){
        setSteps(0);

    }


    public void recover() throws  InvalidTransitionException {
        int dice= dice();
        if(dice>= chapter){
            getOwner().setCurrentHP(getOwner().copy().getMaxHP());
            phase.toStartPhase();

        }
        else {
            phase.toEndTurnPhase();
            tryToEndTurn();
        }
    }



    public void move(){
        steps=dice();

        owner.addAtHomePanelnotification(atHomePanelNotification);
        owner.addAmountOfPlayerListener(moreTanOnePlayernotification);
        owner.addMoreTanOnePathnotification(moreTanOnePathnotification);
        movePlayer();
    }

    /**
     * method that represent the move of
     * a Player
     * steps is the number of moves according to the dice
     * at the end the if the player did not had to stop
     * activates the power of the panel where it is.
     */
    public void movePlayer(){
        getOwner().increaseStarsBy((int) (Math.floor(getChapter()/5)+1));

        while (steps>0 && getOwner().getCanImove()) {
            IPanel nextPanel = getNextPanels(getOwner().getPanel()).iterator().next();
            setPlayerPanel(getOwner(),nextPanel);

            steps -=1;
        }
        activatePanel(getOwner(),getOwner().getPanel());

    }

    public void activatePanel(Player player, IPanel panel){
        panel.activateBy(player);
    }




    public void onMoreThanOnePlayer(boolean newValue)  {
        setCanIMove(!newValue);
        List<Player> opponents= new ArrayList<>();
        opponents.addAll(getOwner().getPanel().getPlayers());
        opponents.remove(getOwner());
        numberOfOpponents=opponents.size();
        setOponnent(opponents.get(0));
        try{
            phase.toWaitFigthPhase(getOwner(),getOponnent());}
        catch (InvalidTransitionException e) {
            e.printStackTrace();
        }





    }

    public void onMoreThanOnePath(boolean newValue){
        if (newValue){
            setCanIMove(false);
            try{
            phase.toWaitPathPhase();}
            catch (InvalidTransitionException e) {
                e.printStackTrace();
            }
        }
    }

    public void onHomePanel(boolean atHome){
        setCanIMove(false);
        try{
            phase.toWaitHomePhase();}
        catch (InvalidTransitionException e) {
            e.printStackTrace();
        }

    }

    public int getSteps() {
        return steps;
    }
    public int getNumberOfOpponents(){
        return numberOfOpponents;
    }

    public void setNumberOfOpponents(int numberOfOpponents) {
        this.numberOfOpponents = numberOfOpponents;
    }

    public void setiMakeADecision(boolean iMakeADecision) {
        this.iMakeADecision = iMakeADecision;
    }

    public String getUnitName(IUnit unit){
        return unit.getName();
    }

    public int getUnitHP(IUnit unit) {
        return unit.getCurrentHP();
    }
}

