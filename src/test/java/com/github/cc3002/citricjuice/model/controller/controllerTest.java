package com.github.cc3002.citricjuice.model.controller;

import com.github.cc3002.citricjuice.model.Player;
import com.github.cc3002.citricjuice.model.board.*;
import com.github.cc3002.citricliquid.gui.GameController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class controllerTest {
    private final static String PLAYER_NAME = "Suguri";
    private Player suguri;
    private final static String PLAYER_NAME_2 = "PANDA";
    private Player panda;
    private final static String PLAYER_NAME_3 = "POLAR";
    private Player polar;
    private final static String PLAYER_NAME_4 = "PARDO";
    private Player pardo;
    private List<Player> listOfPlayers= new ArrayList<>();
    private List<IPanel> listOfPanels= new ArrayList<>();

    private IPanel testHomePanel;
    private IPanel testHomePanel2;
    private IPanel testHomePanel3;
    private IPanel testHomePanel4;
    private IPanel testNeutralPanel;
    private IPanel testBonusPanel;
    private IPanel testDropPanel;
    private IPanel testEncounterPanel;
    private IPanel testBossPanel;
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
        testSeed = new Random().nextLong();
        controller= new GameController();
        controller.addPlayer(suguri.getName(),suguri.getMaxHP(),
                suguri.getAtk(),suguri.getDef(),suguri.getEvd(),testHomePanel);
        controller.addPlayer(polar.getName(),polar.getMaxHP(),
                polar.getAtk(),polar.getDef(),polar.getEvd(),testHomePanel2);
        controller.addPlayer(panda.getName(),panda.getMaxHP(),
                panda.getAtk(),panda.getDef(),panda.getEvd(),testHomePanel3);
        controller.addPlayer(pardo.getName(),pardo.getMaxHP(),
                pardo.getAtk(),pardo.getDef(),pardo.getEvd(),testHomePanel4);
        listOfPanels.add(testBonusPanel);
        listOfPanels.add(testHomePanel);
        listOfPanels.add(testHomePanel2);
        listOfPanels.add(testHomePanel3);
        listOfPanels.add(testHomePanel4);
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

    @Test
    public void movePlayerTest(){

    }

    @Test
    public void HomePanelTest(){

    }



}


