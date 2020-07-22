package com.github.cc3002.citricjuice.model.controller;

import com.github.cc3002.citricjuice.model.BossUnit;
import com.github.cc3002.citricjuice.model.Player;
import com.github.cc3002.citricjuice.model.WildUnit;
import com.github.cc3002.citricjuice.model.board.*;
import com.github.cc3002.citricjuice.model.GameController;
import com.github.cc3002.citricliquid.gui.NormaGoal;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class controllerTest {
    private final static String PLAYER_NAME = "Suguri";
    private Player suguri;
    private final static String PLAYER_NAME_2 = "PANDA";
    private Player panda;
    private final static String PLAYER_NAME_3 = "POLAR";
    private Player polar;
    private final static String PLAYER_NAME_4 = "PARDO";
    private Player pardo;
    private Player suguricontroller;
    private Player pandacontroller;
    private Player polarcontroller;
    private Player pardocontroller;
    private List<Player> listOfPlayers= new ArrayList<>();
    private List<IPanel> listOfPanels= new ArrayList<>();

    private HomePanel testHomePanel;
    private HomePanel testHomePanel2;
    private HomePanel testHomePanel3;
    private HomePanel testHomePanel4;
    private NeutralPanel testNeutralPanel;
    private BonusPanel testBonusPanel;
    private DropPanel testDropPanel;
    private EncounterPanel testEncounterPanel;
    private BossPanel testBossPanel;
    private BonusPanel testBonusPanel2;
    private BonusPanel testBonusPanel3;
    private BonusPanel testBonusPanel4;
    private GameController controller;

    private long testSeed;
    @BeforeEach
    public void setUp() {
        suguri = new Player(PLAYER_NAME, 4, 1, -1, 2);

        panda = new Player(PLAYER_NAME_2, 6, 5, -2, 4);

        polar = new Player(PLAYER_NAME_3, 5, 3, -1, 1);

        pardo= new Player(PLAYER_NAME_4,5,6,7,8);

        testBonusPanel = new BonusPanel(1);
        testBossPanel = new BossPanel(3);
        testDropPanel = new DropPanel(5);
        testEncounterPanel = new EncounterPanel(21);
        testHomePanel = new HomePanel(32);
        testNeutralPanel = new NeutralPanel(13);
        testHomePanel2 = new HomePanel(42);
        testHomePanel=new HomePanel(11);
        testHomePanel3 = new HomePanel(22);
        testHomePanel4=new HomePanel(430);
        testBonusPanel2 = new BonusPanel(15);
        testBonusPanel3 = new BonusPanel(18);
        testBonusPanel4 = new BonusPanel(19);
        testSeed = new Random().nextLong();
        controller= new GameController();
        suguricontroller=controller.addPlayer(suguri.getName(),suguri.getMaxHP(),
                suguri.getAtk(),suguri.getDef(),suguri.getEvd(),testHomePanel);
        polarcontroller = controller.addPlayer(polar.getName(),polar.getMaxHP(),
                polar.getAtk(),polar.getDef(),polar.getEvd(),testHomePanel2);
        pandacontroller= controller.addPlayer(panda.getName(),panda.getMaxHP(),
                panda.getAtk(),panda.getDef(),panda.getEvd(),testHomePanel3);
        pardocontroller =controller.addPlayer(pardo.getName(),pardo.getMaxHP(),
                pardo.getAtk(),pardo.getDef(),pardo.getEvd(),testHomePanel4);


        listOfPlayers.add(suguricontroller);
        listOfPlayers.add(polarcontroller);
        listOfPlayers.add(pandacontroller);
        listOfPlayers.add(pardocontroller);


        BonusPanel controllerBP=controller.addBonusPanel(301);
        BonusPanel controllerBP2=controller.addBonusPanel(302);
        BonusPanel controllerBP3=controller.addBonusPanel(303);
        BonusPanel controllerBP4=controller.addBonusPanel(304);
        listOfPanels.add(controllerBP);
        listOfPanels.add(controllerBP2);
        listOfPanels.add(controllerBP3);
        listOfPanels.add(controllerBP4);
    }


    @Test
    public void createUnit(){
        assertEquals(pardo,pardocontroller);
        assertEquals(panda,pandacontroller);
        assertEquals(polar,polarcontroller);
        assertEquals(suguri,suguricontroller);

        BossUnit Shifu= controller.addBossUnit("Shifu_ROBOT",4,5,6,7);
        BossUnit expected= new BossUnit("Shifu_ROBOT",4,5,6,7);
        assertEquals(Shifu,expected);

        WildUnit chicken= controller.addWildUnit("chicken",4,5,6,7);
        WildUnit expectedchicken= new WildUnit("chicken",4,5,6,7);
        assertEquals(Shifu,expected);
    }
    @Test
    public void createPanel(){
        var HP=new HomePanel(200);
        var expectedHP= controller.addHomePanel(200);
        assertEquals(HP,expectedHP);

        var BP=new BonusPanel(201);
        var expectedBP= controller.addBonusPanel(201);
        assertEquals(BP,expectedBP);

        var BossP=new BossPanel(202);
        var expectedBossP= controller.addBossPanel(202);
        assertEquals(BossP,expectedBossP);


        var DropP=new DropPanel(204);
        var expectedDropP= controller.addDropPanel(204);
        assertEquals(DropP,expectedDropP);

        var EP=new EncounterPanel(205);
        var expectedEP= controller.addEncounterPanel(205);
        assertEquals(EP,expectedEP);

        var NP=new NeutralPanel(206);
        var expectedNP= controller.addNeutralPanel(206);
        assertEquals(NP,expectedNP);


    }
    @Test
    public void NormaGoal(){
        assertEquals(suguri.getNormaGoal(),suguricontroller.getNormaGoal());
        assertEquals(NormaGoal.STARS,controller.getOwner().getNormaGoal());
        controller.setNormaGoal(NormaGoal.WINS);
        assertEquals(NormaGoal.WINS,controller.getOwner().getNormaGoal());
        controller.setNormaGoal(NormaGoal.STARS);
        assertEquals(NormaGoal.STARS,controller.getOwner().getNormaGoal());
    }
    @Test
    public void listOfPlayer(){
        assertEquals(listOfPlayers,controller.getListOfPlayers());
    }



    @Test
    public void turnOwnerTest(){
        assertEquals(suguri,controller.getOwner());
        controller.finishTurn();
        assertEquals(polar,controller.getOwner());
        controller.finishTurn();
        assertEquals(panda,controller.getOwner());
        controller.finishTurn();
        assertEquals(pardo,controller.getOwner());
        controller.finishTurn();
        assertEquals(suguri,controller.getOwner());
    }

    @RepeatedTest(100)
    public void movePlayerTest(){
        //El jugador de turno suguri parte en bonusPanel , el panel siguiente
        //corresponde a su HomePanel, por ello sin importar los dados deberá parar
        controller.getOwner().getPanel().removePlayer(controller.getOwner());
        controller.getOwner().setActualPanel(testBonusPanel);
        controller.setNextPanel(testHomePanel,testBonusPanel);
        controller.setNextPanel(testBonusPanel,testHomePanel);
        controller.move();
        assertTrue(testHomePanel.getPlayers().contains(controller.getOwner()));
        controller.finishTurn();
        //El jugador en turno polar está en su home panel
        // el cual tiene dos paneles siguientes por ello no se mueve de casa
        controller.setNextPanel(testBonusPanel2,testHomePanel3);
        controller.setNextPanel(testBonusPanel2,testHomePanel2);
        assertTrue(testHomePanel2.getPlayers().contains(controller.getOwner()));
    }







    @RepeatedTest(100)
    public void winnerTest(){
        controller.setNextPanel(testHomePanel,testBonusPanel);
        controller.setNextPanel(testBonusPanel,testHomePanel);
        controller.setNextPanel(testHomePanel2,testHomePanel3);
        controller.setNextPanel(testHomePanel3,testHomePanel4);
        controller.setNextPanel(testHomePanel4,testBonusPanel4);
        controller.setNextPanel(testBonusPanel4,testHomePanel4);
        while (controller.getWinner()==null) {
            controller.move();
            controller.finishTurn();
        }
        assertEquals(controller.getOwner(),controller.getWinner());

    }



}


