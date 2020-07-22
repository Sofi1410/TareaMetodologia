package com.github.cc3002.citricjuice.Phases;

import com.github.cc3002.citricjuice.model.BossUnit;
import com.github.cc3002.citricjuice.model.GameController;
import com.github.cc3002.citricjuice.model.Player;
import com.github.cc3002.citricjuice.model.WildUnit;
import com.github.cc3002.citricjuice.model.board.*;
import com.github.cc3002.citricliquid.gui.Phases.InvalidMovementException;
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
    private BossUnit shifuRobot;
    private final static String WILD_NAME = "Suguri";
    private WildUnit chicken;
    private final List<Player> listOfPlayers= new ArrayList<>();
    private Player suguri;
    private Player panda;
    private Player pardo;
    private Player polar;

    //paneles en juego
    // 4 HomePanel , one for each player
    private HomePanel HP1;
    private HomePanel HP2;
    private HomePanel HP3;
    private HomePanel HP4;
    //3 DropPanel
    private DropPanel DropP1;
    private DropPanel DropP2;
    private DropPanel DropP3;
    // 3 EncounterPanel
    private EncounterPanel EP1;
    private EncounterPanel EP2;
    private EncounterPanel EP3;

    // 3 BossPanel
    private BossPanel BossP1;
    private BossPanel BossP2;
    private BossPanel BossP3;
    // 4 BonusPanel
    private BonusPanel BonusP1;
    private BonusPanel BonusP2;
    private BonusPanel BonusP3;
    private BonusPanel BonusP4;


    //12 NeutralPanel
    private NeutralPanel NP1;
    private NeutralPanel NP2;
    private NeutralPanel NP3;
    private NeutralPanel NP4;
    private NeutralPanel NP5;
    private NeutralPanel NP6;
    private NeutralPanel NP7;
    private NeutralPanel NP8;
    private NeutralPanel NP9;
    private NeutralPanel NP10;
    private NeutralPanel NP11;
    private NeutralPanel NP12;






    private IPanel testDropPanel;
    private GameController controller;

    private long testSeed;
    @BeforeEach
    public void setUp() {
        controller= new GameController();
        HP1 = controller.addHomePanel(11);
        HP2 = controller.addHomePanel(12);
        HP3 = controller.addHomePanel(13);
        HP4 = controller.addHomePanel(14);

        suguri = controller.addPlayer(PLAYER_NAME, 4, 1, -1, 2,HP1);
        polar = controller.addPlayer(PLAYER_NAME_3, 5, 3, -1, 1,HP2);
        panda = controller.addPlayer(PLAYER_NAME_2, 6, 5, -2, 4,HP3);
        pardo = controller.addPlayer(PLAYER_NAME_4, 5, 6, 7, 8,HP4);

        NP1 =controller.addNeutralPanel(21);
        NP2 =controller.addNeutralPanel(22);
        NP3 =controller.addNeutralPanel(23);
        NP4 =controller.addNeutralPanel(24);
        NP5 =controller.addNeutralPanel(25);

        DropP1= controller.addDropPanel(31);

        BonusP1=controller.addBonusPanel(41);

        listOfPlayers.add(suguri);
        listOfPlayers.add(polar);
        listOfPlayers.add(panda);
        listOfPlayers.add(pardo);

       // controller.setNextPanel(NP1,HP1);
        /**controller.setNextPanel(HP1,NP2);
        controller.setNextPanel(DropP1,NP1);
        controller.setNextPanel(NP3,DropP1);
        controller.setNextPanel(NP2,NP4);
        controller.setNextPanel(NP2,NP3);
        controller.setNextPanel(NP4,HP2);
        controller.setNextPanel(NP5,NP4);
        controller.setNextPanel(BonusP1,NP3);
        controller.setNextPanel(BonusP1,NP5);
         */




    }

    @Test
    public void begintheTurnTest(){
        assertTrue(HP1.getPlayers().contains(suguri));
        assertTrue(HP2.getPlayers().contains(polar));
        assertTrue(HP3.getPlayers().contains(panda));
        assertTrue(HP4.getPlayers().contains(pardo));
        assertFalse(HP3.getPlayers().contains(pardo));


    }

    //Phases(listOfPlayers,owner);
    //HERE I SUPOUSSED TO  make an enviroment in order to test the phases
    // conect the aproppiate panels, and make situaciones so we cant wait the players response
    @RepeatedTest(50)
    public void StartandRecoveryPhaseTest() throws InvalidMovementException {
        assertEquals("Start_Phase",controller.getCurrentPhase());
        assertEquals(controller.getOwner(),suguri);
        controller.getOwner().setCurrentHP(-20);
        assertTrue(controller.getOwner().itsK_O());
        controller.getPhase().start();
        assertEquals("Recovery_Phase",controller.getCurrentPhase());
        controller.getOwner().setSeed(21);
        int dice= new Random(21).nextInt(6)+1;

        controller.getPhase().tryToRecover();
        if (dice>=controller.getChapter()){
            assertEquals("Start_Phase",controller.getCurrentPhase());
            assertEquals(suguri.getMaxHP(),suguri.getCurrentHP());
            assertEquals(suguri,controller.getOwner());
        }
        else{
            assertEquals(polar,controller.getOwner());
            assertEquals("Start_Phase",controller.getCurrentPhase());
            assertEquals(0,suguri.getCurrentHP());

        }

    }

    @Test
    public void movingPhaseTest() throws InvalidMovementException {


        assertEquals("Start_Phase",controller.getCurrentPhase());
        assertEquals(controller.getOwner(),suguri);
        assertEquals(suguri.getMaxHP(),suguri.getCurrentHP());
        controller.getPhase().start();
        assertNotEquals("Recovery_Phase",controller.getCurrentPhase());
        assertEquals("Moving_Phase",controller.getCurrentPhase());

    }
    @Test
    public void WaitPathTest() throws InvalidMovementException {
        controller.setNextPanel(HP1,NP2);
        controller.setNextPanel(DropP1,NP1);
        controller.setNextPanel(NP2,NP4);
        controller.setNextPanel(NP2,NP3);

        assertEquals("Start_Phase",controller.getCurrentPhase());
        assertEquals(controller.getOwner(),suguri);
        assertEquals(suguri.getMaxHP(),suguri.getCurrentHP());
        controller.getPhase().start();
        assertNotEquals("Recovery_Phase",controller.getCurrentPhase());
        assertEquals("Moving_Phase",controller.getCurrentPhase());
        controller.getOwner().setSeed(21);
        int dice= new Random(21).nextInt(6)+1;
        controller.getPhase().firstMove();
        assertEquals("WaitPath_Phase",controller.getCurrentPhase());

    }
    @RepeatedTest(200)
    public void WaitFightTest() throws InvalidMovementException {
        controller.setNextPanel(HP1,NP2);
        controller.setNextPanel(DropP1,NP1);
        controller.setNextPanel(NP2,NP4);
        controller.setNextPanel(NP4,NP5);
        controller.setPlayerPanel(pardo,NP4);

        assertEquals("Start_Phase",controller.getCurrentPhase());
        assertEquals(controller.getOwner(),suguri);
        assertEquals(suguri.getMaxHP(),suguri.getCurrentHP());
        controller.getPhase().start();
        assertNotEquals("Recovery_Phase",controller.getCurrentPhase());
        assertEquals("Moving_Phase",controller.getCurrentPhase());
        controller.getOwner().setSeed(21);
        int dice= new Random(21).nextInt(6)+1;
        controller.getPhase().firstMove();
        assertEquals("WaitFight_Phase",controller.getCurrentPhase());
        assertEquals(NP4,controller.getOwner().getPanel());
        controller.getPhase().iAmGoingToFigth();
    }

    @Test
    public void figthPhase() throws InvalidMovementException {
        controller.setNextPanel(HP1,NP2);
        controller.setNextPanel(DropP1,NP1);
        controller.setNextPanel(NP2,NP4);
        controller.setNextPanel(NP4,NP5);
        controller.setPlayerPanel(pardo,NP4);

        assertEquals("Start_Phase",controller.getCurrentPhase());
        assertEquals(controller.getOwner(),suguri);
        assertEquals(suguri.getMaxHP(),suguri.getCurrentHP());
        controller.getPhase().start();
        controller.getOwner().setSeed(21);
        int dice= new Random(21).nextInt(6)+1;
        controller.getPhase().firstMove();
        assertEquals("WaitFight_Phase",controller.getCurrentPhase());
        assertEquals(NP4,controller.getOwner().getPanel());
        controller.getPhase().iAmGoingToFigth();
        assertEquals("OpponentChoice_Phase",controller.getCurrentPhase());

    }

    @Test
    public void WaitHomeTest() throws InvalidMovementException {
        controller.setNextPanel(HP1,NP2);
        controller.setNextPanel(DropP1,NP1);
        controller.setNextPanel(NP2,HP1);
        controller.setNextPanel(NP4,NP5);
        controller.setPlayerPanel(suguri,NP2);

        assertEquals("Start_Phase",controller.getCurrentPhase());
        assertEquals(controller.getOwner(),suguri);
        assertEquals(suguri.getMaxHP(),suguri.getCurrentHP());
        controller.getPhase().start();
        assertNotEquals("Recovery_Phase",controller.getCurrentPhase());
        assertEquals("Moving_Phase",controller.getCurrentPhase());
        controller.getOwner().setSeed(21);
        int dice= new Random(21).nextInt(6)+1;
        controller.getPhase().firstMove();
        assertEquals("WaitHome_Phase",controller.getCurrentPhase());
        assertEquals(HP1,controller.getOwner().getPanel());
    }
    @RepeatedTest(100)
    public void stayAtHomeTest() throws InvalidMovementException {
        controller.setNextPanel(HP1,NP4);
        controller.setNextPanel(DropP1,NP1);
        controller.setNextPanel(NP2,HP1);
        controller.setNextPanel(NP4,NP5);
        controller.setPlayerPanel(suguri,NP2);

        assertEquals("Start_Phase",controller.getCurrentPhase());
        assertEquals(controller.getOwner(),suguri);
        assertEquals(suguri.getMaxHP(),suguri.getCurrentHP());
        controller.getPhase().start();
        assertNotEquals("Recovery_Phase",controller.getCurrentPhase());
        assertEquals("Moving_Phase",controller.getCurrentPhase());
        controller.getOwner().setSeed(21);
        int dice= new Random(21).nextInt(6)+1;
        controller.getPhase().firstMove();
        assertEquals("WaitHome_Phase",controller.getCurrentPhase());
        assertEquals(HP1,controller.getOwner().getPanel());
        controller.getPhase().stayAtHome();
        assertEquals(0,controller.getSteps());
    }

    @RepeatedTest(100)
    public void keepMovingTest() throws InvalidMovementException {
        controller.setNextPanel(HP1, NP2);
        controller.setNextPanel(NP2, HP1);
        controller.setPlayerPanel(suguri, NP2);

        assertEquals("Start_Phase", controller.getCurrentPhase());
        assertEquals(controller.getOwner(), suguri);
        assertEquals(suguri.getMaxHP(), suguri.getCurrentHP());
        controller.getPhase().start();
        assertNotEquals("Recovery_Phase", controller.getCurrentPhase());
        assertEquals("Moving_Phase", controller.getCurrentPhase());
        long seed= new Random().nextLong();
        controller.getOwner().setSeed(seed);
        int dice = new Random(seed).nextInt(6) + 1;
        controller.getPhase().firstMove();
        assertEquals("WaitHome_Phase", controller.getCurrentPhase());
        assertEquals(HP1, controller.getOwner().getPanel());
        assertEquals(dice-1, controller.getSteps());
        controller.getPhase().keepMoving();
        if (dice > 2) {
            assertEquals("WaitHome_Phase", controller.getCurrentPhase());

        }
    }


}
