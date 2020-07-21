package com.github.cc3002.citricjuice.Phases;

import com.github.cc3002.citricjuice.model.BossUnit;
import com.github.cc3002.citricjuice.model.GameController;
import com.github.cc3002.citricjuice.model.Player;
import com.github.cc3002.citricjuice.model.WildUnit;
import com.github.cc3002.citricjuice.model.board.*;
import com.github.cc3002.citricliquid.gui.Phases.InvalidMovementException;
import com.github.cc3002.citricliquid.gui.Phases.RecoveryPhase;
import com.github.cc3002.citricliquid.gui.Phases.StartPhase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PhasesTest {
    private final static String PLAYER_NAME = "Suguri";
    private final static String PLAYER_NAME_2 = "PANDA";
    private final static String PLAYER_NAME_3 = "POLAR";
    private final static String PLAYER_NAME_4 = "PARDO";
    private final static String BOSS_NAME = "Shifu Robot";
    private Player shifuRobot;
    private final static String WILD_NAME = "Suguri";
    private Player chicken;
    private final List<Player> listOfPlayers= new ArrayList<>();
    private Player suguricontroller;
    private Player pandacontroller;
    private Player pardocontroller;
    private Player polarcontroller;



    private IPanel testDropPanel;
    private GameController controller;

    private long testSeed;
    @BeforeEach
    public void setUp() {
        HomePanel testHomePanel3 = new HomePanel(22);

        Player suguri = new Player(PLAYER_NAME, 4, 1, -1, 2);

        Player panda = new Player(PLAYER_NAME_2, 6, 5, -2, 4);

        Player polar = new Player(PLAYER_NAME_3, 5, 3, -1, 1);

        Player pardo = new Player(PLAYER_NAME_4, 5, 6, 7, 8);

        IPanel testBonusPanel = new BonusPanel(1);
        IPanel testBossPanel = new BossPanel(3);
        testDropPanel = new DropPanel(5);
        IPanel testEncounterPanel = new EncounterPanel(21);
        HomePanel testHomePanel = new HomePanel(32);
        IPanel testNeutralPanel = new NeutralPanel(13);
        HomePanel testHomePanel2 = new HomePanel(42);
        testHomePanel =new HomePanel(11);

        HomePanel testHomePanel4 = new HomePanel(430);
        testSeed = new Random().nextLong();
        controller= new GameController();
        suguricontroller = controller.addPlayer(suguri.getName(), suguri.getMaxHP(),
                suguri.getAtk(), suguri.getDef(), suguri.getEvd(), testHomePanel);
        polarcontroller = controller.addPlayer(polar.getName(), polar.getMaxHP(),
                polar.getAtk(), polar.getDef(), polar.getEvd(), testHomePanel2);
        pandacontroller = controller.addPlayer(panda.getName(), panda.getMaxHP(),
                panda.getAtk(), panda.getDef(), panda.getEvd(), testHomePanel3);
        pardocontroller = controller.addPlayer(pardo.getName(), pardo.getMaxHP(),
                pardo.getAtk(), pardo.getDef(), pardo.getEvd(), testHomePanel4);


        listOfPlayers.add(suguricontroller);
        listOfPlayers.add(polarcontroller);
        listOfPlayers.add(pandacontroller);
        listOfPlayers.add(pardocontroller);
    }

    @Test
    public void begintheTurnTest(){


    }

    //Phases(listOfPlayers,owner);
    //HERE I SUPOUSSED TO  make an enviroment in order to test the phases
    // conect the aproppiate panels, and make situaciones so we cant wait the players response
    @Test
    public void StartPhaseTest() throws InvalidMovementException {
        controller.setPhase(new StartPhase());
        assertEquals(controller.getOwner(),suguricontroller);
        controller.getOwner().setCurrentHP(-20);
        assertTrue(controller.getOwner().itsK_O());
        controller.getPhase().start();
        assertEquals("Recovery_Phase",controller.getCurrentPhase());
        controller.getPhase().tryToRecover();
        controller.getCurrentPhase();

    }

    @Test
    public void recoveryTest(){

    }
}
