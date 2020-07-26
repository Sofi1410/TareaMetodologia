package com.github.cc3002.citricliquid.gui;

import com.github.cc3002.citricjuice.model.Handler.AtHomePanelHandler;
import com.github.cc3002.citricjuice.model.Handler.MoreThanOnePathHandler;
import com.github.cc3002.citricjuice.model.Handler.MoreThanOnePlayerHandler;
import com.github.cc3002.citricjuice.model.Handler.NormaLevelObserver;
import com.github.cc3002.citricjuice.model.Unit.*;
import com.github.cc3002.citricjuice.model.board.*;
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
    private final MoreThanOnePlayerHandler moreTanOnePlayernotification=  new MoreThanOnePlayerHandler(this);
    private final MoreThanOnePathHandler moreTanOnePathnotification=  new MoreThanOnePathHandler(this);
    private final AtHomePanelHandler atHomePanelNotification=  new AtHomePanelHandler(this);

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


    /**
     * Setea los pasos del jugador actual
     * @param newValue cantidad nueva de pasos
     */
    public void setSteps(int newValue){
        steps=newValue;
    }

    /**
     * camba el opponente del juego
     * en caso de que no sea etapa de combate este no tiene sentido
     * para que lo tenga la label de la fase
     * debe ser WaitFightPhase
     * @param oponnent nuevo opponente
     */
    public void setOponnent(IUnit oponnent) {
        this.oponnent = oponnent;
    }

    /**
     * Entrega el oponente del juego
     * @return el oponente
     */
    public IUnit getOponnent(){
        return oponnent;
    }

    /**
     * Entrega a quien está tomando una desición
     * puede no ser el dueño del turno
     * en caso de pelear
     * @param unit nuevoa unidad eligiendo
     */
    public void setActualUnit(IUnit unit){
        this.actualUnit=unit;
    }

    /**
     * entega la unidad que está tomando una decision
     * @return la unidad
     */
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

    /**
     * Cambia la phase en el controlador
     * @param phase nueva phase
     */
    public void setPhase(Phase phase) {
        this.phase = phase;
        phase.setController(this);
    }

    /**
     * delueve el nombre de la fase actual
     * @return nombre
     */
    public String getCurrentPhase(){
        return phase.toString();
    }

    /**
     * Entrega la phase actual
     * @return phase actual
     */
    public Phase getPhase(){
        return phase;
    }


    /**
     * determina si el juego del turno está muerto
     * @return true esta muerto,false vivo
     */
    public boolean itsK_O(){
        return getOwner().isK_O();
    }

    /**
     * calcula el ataque efectuado por la unidad
     * @param unit Unidad que ataca
     * @return cantidad e ataque
     */
    public int attack(IUnit unit){
        return unit.attack();
    }


    /**
     * Metodo evadir, efectua la evasión de una unidad
     * @param attacker quien atacó
     * @param victim quien recibe el atque y decide evadir
     * @throws InvalidTransitionException en caso de no hacer una transicion correcta
     */
    public void evade(IUnit attacker,IUnit victim) throws InvalidTransitionException {
        int attack=attack(attacker);
        victim.evade(attack);
        afterEvadeOrDefend(attacker,victim);
    }

    /**
     * Metodo que efectua la efensa de una unidad
     * @param attacker quien ataca
     * @param victim quien decide defende
     * @throws InvalidTransitionException en caso de na transición incorrecta
     */
    public void defend(IUnit attacker, IUnit victim) throws InvalidTransitionException {
        int attack=attack(attacker);
        victim.defend(attack);
        afterEvadeOrDefend(attacker,victim);
    }

    /**
     * Luego de evadir o defender el oponente contrataca
     * y el dueño del turno termina su turno
     * @param attacker quien ataca
     * @param victim quien recibe el ataque
     * @throws InvalidTransitionException
     */
    public void afterEvadeOrDefend(IUnit attacker, IUnit victim) throws InvalidTransitionException {
        if(victim.isK_O()){
            setActualUnit(getOwner());
            setOponnent(nullPLayer);
            attacker.increaseStarsBy(victim);
            attacker.increaseVictoriesBy(victim);
            phase.toMovingPhase();
        }
        else if(!victim.equals(getOwner())){
            counterattack(victim,attacker);
        }
    }

    /**
     * Se efectua el contrataque del oponente
     * @param attacker quien decide contratacar
     * @param victim el dueño del turno
     */
    public void counterattack(IUnit attacker, IUnit victim){
        try {
            phase.toWaitFigthPhase(attacker,victim);
        } catch (InvalidTransitionException e) {
            e.printStackTrace();
        }
        tryToFight();
    }

    /**
     * efectua el movimiento decidido por
     * el jugador si está en la fase correcta
     * @throws InvalidTransitionException si no se pidio la acció
     * en una fase correcta
     */
    public void left() throws InvalidTransitionException {
        setSteps(getSteps()-1);
        phase.toMovingPhase();
        setCanIMove(true);
        setPlayerPanel(getOwner(),getOwner().getPanel().getLeft());
        if(steps==0){
            stopMoving();
        }

    }
    /**
     * efectua el movimiento decidido por
     * el jugador si está en la fase correcta
     * @throws InvalidTransitionException si no se pidio la acció
     * en una fase correcta
     */
    public void right() throws InvalidTransitionException {
        setSteps(getSteps()-1);
        phase.toMovingPhase();
        setCanIMove(true);
        setPlayerPanel(getOwner(),getOwner().getPanel().getRight());
        if(steps==0){
            stopMoving();
        }
    }
    /**
     * efectua el movimiento decidido por
     * el jugador si está en la fase correcta
     * @throws InvalidTransitionException si no se pidio la acció
     * en una fase correcta
     */
    public void up() throws InvalidTransitionException {
        setSteps(getSteps()-1);
        phase.toMovingPhase();
        setCanIMove(true);
        setPlayerPanel(getOwner(),getOwner().getPanel().getUp());
        if(steps==0){
            stopMoving();
        }

    }
    /**
     * efectua el movimiento decidido por
     * el jugador si está en la fase correcta
     * @throws InvalidTransitionException si no se pidio la acció
     * en una fase correcta
     */
    public void down() throws InvalidTransitionException {
        setSteps(getSteps()-1);
        phase.toMovingPhase();
        setCanIMove(true);
        setPlayerPanel(getOwner(),getOwner().getPanel().getDown());
        if(steps==0){
            stopMoving();
        }
    }

    /**
     * cuando se acaban los pasos y ya no se puede
     * seguir movimiento
     * @throws InvalidTransitionException
     */
    public void stopMoving() throws InvalidTransitionException {
        phase.toEndTurnPhase();
    }


    /**
     * setea la variable que perite al jgador moverse
     * @param newValue nuevo booleano
     */
    public void setCanIMove(boolean newValue){
        getOwner().setCanImove(newValue);
    }




    // Inicio del juego
    //intento iniciar

    /**
     * Jugador intenta iniciar
     * solo se efectuara si está en la fase apropiada
     */
    public void tryToStart(){
        try {
            this.setActualUnit(getOwner());
            phase.start();
        } catch (InvalidMovementException | InvalidTransitionException e) {
            e.printStackTrace();
        }

    }
    /**
     * Por las condiciones se intenta recuperar al jugador
     * se asevera de que esté en la función correcta
     * y extensible a si esto se considerra una opción
     * solo se efectuara si está en la fase apropiada
     */
    public void tryToRecover(){
        try {
            phase.recover();
        } catch (InvalidMovementException | InvalidTransitionException e) {
            e.printStackTrace();
        }
    }
    /**
     * Jugador inicia el juego y con ello
     * el primer movimiento
     * solo se efectuara si está en la fase apropiada
     * se diferencia de trytomove ya que en este caso se tiran los dados
     */
    public void trydotheFirstMove(){
        try {
            phase.firstMove();
        } catch (InvalidMovementException | InvalidTransitionException e) {
            e.printStackTrace();
        }
    }

    /**
     * La fase intenta mover luego de haber tirado los dados
     * o de haberse detenido
     */
    public void tryToMove(){
        try {
            phase.move();
        } catch (InvalidMovementException e) {
            e.printStackTrace();
        }
    }

    /**
     * El jugador escoge sseguir moviendose luego de tomar una decisión
     * ejemplo me quedo en casa o me sigo moviendo
     */
    public void tryToKeepMoving(){
        try {
            phase.toMovingPhase();
            phase.keepMoving();
        } catch (InvalidTransitionException | InvalidMovementException e) {
            e.printStackTrace();
        }

    }

    /**
     * Jugador intenta decidir quedarse en casa
     */
    public void tryToStayAtHome(){
        try {
            phase.stayAtHome();
        } catch (InvalidMovementException e) {
            e.printStackTrace();
        }
    }
    /**
     * Jugador decide quedarse a pelear
     * solo se efectuara si está en la fase apropiada
     */
    public void tryToFight(){
        try {
            phase.iAmGoingToFigth();
        } catch (InvalidMovementException e) {
            e.printStackTrace();
        }
    }

    /**
     * Jugador intenta evadir el ataque
     */
    public void tryToevade(){
        try{
            phase.evade();
        }catch (InvalidMovementException | InvalidTransitionException e){
            e.printStackTrace();
        }
    }

    /**
     * Usuario intenta defenderse del ataque
     */
    public void tryToDefend(){
        try{
            phase.defend();
        }catch (InvalidMovementException | InvalidTransitionException e){
            e.printStackTrace();
        }
    }

    /**
     * Jugador decide ir por el camino izquierdo
     */
    public void tryToGoLeft(){
        try{
            phase.left();
        }catch (InvalidMovementException | InvalidTransitionException e){
            e.printStackTrace();
        }
    }

    /**
     * Jugador decide ir por el camino a la derecha
     */
    public void tryToGoRight(){
        try{
            phase.right();
        }catch (InvalidMovementException | InvalidTransitionException e){
            e.printStackTrace();
        }
    }

    /**
     * Jugador decide ir or el camino de arriba
     */
    public void tryToGoRUp(){
        try{
            phase.up();
        }catch (InvalidMovementException | InvalidTransitionException e){
            e.printStackTrace();
        }
    }

    /**
     * Jugador decide bajar
     */
    public void tryToGoDown(){
        try{
            phase.down();
        }catch (InvalidMovementException | InvalidTransitionException e){
            e.printStackTrace();
        }
    }


    /**
     * Jugador intenta terminar su turno
     */
    public void tryToEndTurn(){
        try {
            phase.endTurn();
        } catch (InvalidMovementException e) {
            e.printStackTrace();
        }
    }


    /**
     * Metodo para recuperar al jugador
     * Solo si el dado lo permite
     * @throws InvalidTransitionException si la transición
     * es en una fase no apropiada
     */
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


    /**
     * El jugador se mueve al iniciar el turno
     */
    public void move() {
        getOwner().increaseStarsBy((int) (Math.floor(getChapter()/5)+1));
        owner.addAtHomePanelnotification(atHomePanelNotification);
        owner.addAmountOfPlayerListener(moreTanOnePlayernotification);
        owner.addMoreTanOnePathnotification(moreTanOnePathnotification);


        steps=dice();
        if(!getOwner().getCanImove()&&steps>0){
            try {
                phase.toWaitPathPhase();
            } catch (InvalidTransitionException e) {
                e.printStackTrace();
            }
        }
        if(steps<0){
            try {
                phase.toEndTurnPhase();
            } catch (InvalidTransitionException e) {
                e.printStackTrace();
            }
        }
        else {
            while (steps>0 && getOwner().getCanImove()){
                IPanel nextPanel= getOwner().getPanel().getNextPanels().iterator().next();
                setPlayerPanel(getOwner(),nextPanel);

            }
        }

    }

    /**
     * method that represent the move of
     * a Player after rolling the dice
     * steps is the number of moves according to the dice
     * at the end the if the player did not had to stop
     * activates the power of the panel where it is.
     */
    public void movePlayer(){

        if(!getOwner().getCanImove()& steps>0){
            try {
                phase.toWaitPathPhase();
            } catch (InvalidTransitionException e) {
                e.printStackTrace();
            }
        }

        else{
            try {
                phase.toEndTurnPhase();
            } catch (InvalidTransitionException e) {
                e.printStackTrace();
            }

        }
        }


    /**
     * Metodo que aciva el efecto de un panel
     * @param player jugador en el panel
     * @param  panel del jugador
     */
    public void activatePanel(Player player, IPanel panel){
        panel.activateBy(player);
    }


    /**
     * Metodo del observer cuando hay un camino
     * con mas de un jugador
     * @param newValue lo que aroja el overser
     */
    public void onMoreThanOnePlayer(boolean newValue)  {
        if(!newValue) {
            setCanIMove(false);
            List<Player> opponents = new ArrayList<>();
            opponents.addAll(getOwner().getPanel().getPlayers());
            opponents.remove(getOwner());
            numberOfOpponents = opponents.size();
            setOponnent(opponents.get(0));
            try {
                phase.toWaitFigthPhase(getOwner(), getOponnent());
            } catch (InvalidTransitionException e) {
                e.printStackTrace();
            }
        }





    }

    /**
     * Metodo del observer cuando hay mas de un camino
     * @param newValue
     */
    public void onMoreThanOnePath(boolean newValue)  {
        if(!newValue) {
            setCanIMove(false);
            try{
            phase.toWaitPathPhase();}
            catch (InvalidTransitionException e) {
                e.printStackTrace();
            }
        }


    }

    /**
     * Cuando esta en su home panel
     * @param panel nuevo boleano
     */
    public void onHomePanel(boolean panel){
        setCanIMove(false);
            try {
                phase.toWaitHomePhase();
            } catch (InvalidTransitionException e) {
                e.printStackTrace();
            }


    }

    /**
     * Retorna los pasos
     * @return pasos
     */
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

    /**
     * Retorna el nombre de una unidad
     * @param unit la unidad consltada
     * @return el nombre
     */
    public String getUnitName(IUnit unit){
        return unit.getName();
    }

    /**
     * retorna el hp de una unidad
     * @param unit consultad
     * @return hp
     */
    public int getUnitHP(IUnit unit) {
        return unit.getCurrentHP();
    }

    /**
     * retorna el hp de una unidad
     * @return victorias
     */
    public int getOwnerVictories() {
        return getOwner().getVictories();
    }
    /**
     * retorna el hp de una unidad
     * @return las estrellas
     */
    public int getOwnerStars() {
        return getOwner().getStars();
    }
    /**
     * retorna el hp de una unidad
     * @return la norma goal
     */
    public NormaGoal getOwnerNormaGoal(){
        return getOwner().getNormaGoal();
    }

    public void escenario(){
        GameController controller=this;
        String PLAYER_NAME = "NOM-NOM";
        String PLAYER_NAME_2 = "PANDA";
        String PLAYER_NAME_3 = "POLAR";
        String PLAYER_NAME_4 = "PARDO";


        //paneles en juego
        // 4 HomePanel , one for each player
        HomePanel HP1 = controller.addHomePanel(11);
        HomePanel HP2 = controller.addHomePanel(12);
        HomePanel HP3 = controller.addHomePanel(13);
        HomePanel HP4 = controller.addHomePanel(14);

        //3 DropPanel
        DropPanel DropP1;
        DropPanel DropP2;
        DropPanel DropP3;
        // 3 EncounterPanel
        EncounterPanel EP1;
        EncounterPanel EP2;
        EncounterPanel EP3;

        // 3 BossPanel
        BossPanel BossP1;
        BossPanel BossP2;
        BossPanel BossP3;
        // 4 BonusPanel
        BonusPanel BonusP1;
        BonusPanel BonusP2;
        BonusPanel BonusP3;
        BonusPanel BonusP4;


        //12 NeutralPanel
        NeutralPanel NP1;
        NeutralPanel NP2;
        NeutralPanel NP3;
        NeutralPanel NP4;
        NeutralPanel NP5;
        NeutralPanel NP6;
        NeutralPanel NP7;
        NeutralPanel NP8;
        NeutralPanel NP9;
        NeutralPanel NP10;
        NeutralPanel NP11;
        NeutralPanel NP12;




        DropP1=controller.addDropPanel(31);
        DropP2=controller.addDropPanel(32);
        DropP3=controller.addDropPanel(33);

        EP1=controller.addEncounterPanel(41);
        EP2=controller.addEncounterPanel(42);
        EP3=controller.addEncounterPanel(44);

        BossP1=controller.addBossPanel(51);
        BossP2=controller.addBossPanel(52);
        BossP3=controller.addBossPanel(53);

        BonusP1=controller.addBonusPanel(61);
        BonusP2=controller.addBonusPanel(62);
        BonusP3=controller.addBonusPanel(63);
        BonusP4=controller.addBonusPanel(64);


        NP1 =controller.addNeutralPanel(71);
        NP2 =controller.addNeutralPanel(72);
        NP3 =controller.addNeutralPanel(73);
        NP4 =controller.addNeutralPanel(74);
        NP5 =controller.addNeutralPanel(75);
        NP6 =controller.addNeutralPanel(76);
        NP7 =controller.addNeutralPanel(77);
        NP8 =controller.addNeutralPanel(78);
        NP9 =controller.addNeutralPanel(79);
        NP10 =controller.addNeutralPanel(80);
        NP11 =controller.addNeutralPanel(81);
        NP12 =controller.addNeutralPanel(82);


        //columna 1
        controller.setNextPanel(HP1,NP2);
        controller.setNextPanel(HP1,NP1);
        HP1.setRight(NP2);
        HP1.setUp(NP1);

        controller.setNextPanel(NP1,HP1);
        controller.setNextPanel(NP1,DropP1);
        NP1.setUp(DropP1);
        NP1.setDown(HP1);

        controller.setNextPanel(DropP1,NP3);
        controller.setNextPanel(DropP1,NP1);
        DropP1.setRight(NP3);
        DropP1.setDown(NP1);

        //columna 2
        controller.setNextPanel(NP2,HP1);
        controller.setNextPanel(NP2,NP4);
        NP2.setRight(NP4);
        NP2.setLeft(HP1);

        controller.setNextPanel(NP3,DropP1);
        controller.setNextPanel(NP3,BonusP1);
        NP3.setRight(BonusP1);
        NP3.setLeft(DropP1);

        //columna 3
        controller.setNextPanel(NP4,HP2);
        controller.setNextPanel(NP4,NP5);
        controller.setNextPanel(NP4,NP2);
        NP4.setRight(HP2);
        NP4.setLeft(NP2);
        NP4.setUp(NP5);

        controller.setNextPanel(NP5,NP4);
        controller.setNextPanel(NP5,BonusP1);
        NP5.setUp(BonusP1);
        NP5.setDown(NP4);

        controller.setNextPanel(BonusP1,NP6);
        controller.setNextPanel(BonusP1,NP5);
        controller.setNextPanel(BonusP1,NP3);
        BonusP1.setLeft(NP3);
        BonusP1.setDown(NP5);
        BonusP1.setUp(NP6);

        controller.setNextPanel(NP6,BonusP1);
        controller.setNextPanel(NP6,BossP1);
        NP6.setUp(BossP1);
        NP6.setDown(BonusP1);

        controller.setNextPanel(BossP1,BonusP2);
        controller.setNextPanel(BossP1,NP6);
        BossP1.setUp(BonusP2);
        BossP1.setDown(NP6);

        controller.setNextPanel(BonusP2,BossP1);
        controller.setNextPanel(BonusP2,EP1);
        BonusP2.setRight(EP1);
        BonusP2.setDown(BossP1);

        //Columna 4

        controller.setNextPanel(HP2,EP2);
        controller.setNextPanel(HP2,NP4);
        HP2.setRight(EP2);
        HP2.setLeft(NP4);

        controller.setNextPanel(EP1,NP7);
        controller.setNextPanel(EP1,BonusP2);
        EP1.setRight(NP7);
        EP1.setLeft(BonusP2);

        //Columna 5

        controller.setNextPanel(EP2,BossP2);
        controller.setNextPanel(EP2,HP2);
        EP2.setRight(BossP2);
        EP2.setLeft(HP2);

        controller.setNextPanel(NP7,HP3);
        controller.setNextPanel(NP7,HP1);
        NP7.setRight(HP3);
        NP7.setLeft(EP1);

        //Columna 6
        controller.setNextPanel(BossP2,NP9);
        controller.setNextPanel(BossP2,EP2);
        BossP2.setRight(NP9);
        BossP2.setLeft(EP2);

        controller.setNextPanel(DropP2,NP10);
        controller.setNextPanel(DropP2,BossP3);
        DropP2.setRight(NP10);
        DropP2.setUp(BossP3);

        controller.setNextPanel(BossP3,NP8);
        controller.setNextPanel(BossP3,DropP2);
        BossP3.setDown(DropP2);
        BossP3.setUp(NP8);

        controller.setNextPanel(NP8,BossP3);
        controller.setNextPanel(NP8,HP3);
        NP8.setUp(HP3);
        NP8.setDown(BossP3);


        controller.setNextPanel(HP3,NP7);
        controller.setNextPanel(HP3,NP8);
        controller.setNextPanel(HP3,DropP3);
        HP3.setLeft(NP7);
        HP3.setDown(NP8);
        HP3.setRight(DropP3);

        //columna 7
        controller.setNextPanel(NP9,BossP2);
        controller.setNextPanel(NP9,BonusP3);
        NP9.setRight(BonusP3);
        NP9.setLeft(BossP2);

        controller.setNextPanel(NP10,NP11);
        controller.setNextPanel(NP10,DropP2);
        NP10.setRight(NP11);
        NP10.setLeft(DropP2);

        controller.setNextPanel(DropP3,BonusP4);
        controller.setNextPanel(DropP3,HP3);
        DropP3.setRight(BonusP4);
        DropP3.setLeft(HP3);

        //columna 8
        controller.setNextPanel(BonusP3,NP9);
        controller.setNextPanel(BonusP3,EP3);
        BonusP3.setUp(EP3);
        BonusP3.setLeft(NP9);

        controller.setNextPanel(EP3,BonusP3);
        controller.setNextPanel(EP3,NP11);
        EP3.setUp(NP11);
        EP3.setDown(BonusP3);

        controller.setNextPanel(NP11,BossP3);
        controller.setNextPanel(NP11,EP3);
        controller.setNextPanel(NP11,NP10);
        NP11.setUp(NP12);
        NP11.setLeft(NP10);
        NP11.setDown(EP3);

        controller.setNextPanel(NP12,NP11);
        controller.setNextPanel(NP12,HP4);
        NP12.setUp(HP4);
        NP12.setDown(NP11);

        controller.setNextPanel(HP4,BonusP4);
        controller.setNextPanel(HP4,NP12);
        HP4.setUp(BonusP4);
        HP4.setDown(NP12);

        controller.setNextPanel(BonusP4,HP4);
        controller.setNextPanel(BonusP4,DropP3);
        BonusP4.setDown(HP4);
        BonusP4.setLeft(DropP3);

        controller.addPlayer(PLAYER_NAME, 4, 1, -1, 2,HP1);
        controller.addPlayer(PLAYER_NAME_3, 5, 3, -1, 1,HP2);
        controller.addPlayer(PLAYER_NAME_2, 6, 5, -2, 4,HP3);
        controller.addPlayer(PLAYER_NAME_4, 5, 6, 7, 8,HP4);


    }

}

