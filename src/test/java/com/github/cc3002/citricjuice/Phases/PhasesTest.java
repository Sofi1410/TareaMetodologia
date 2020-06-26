package com.github.cc3002.citricjuice.Phases;

import com.github.cc3002.citricjuice.model.BossUnit;
import com.github.cc3002.citricjuice.model.Player;
import com.github.cc3002.citricjuice.model.WildUnit;
import com.github.cc3002.citricjuice.model.board.*;
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
    private Player suguri;
    private final static String BOSS_NAME = "Shifu Robot";
    private Player shifuRobot;
    private final static String WILD_NAME = "Suguri";
    private Player chicken;
    private List<Player> listOfPlayers= new ArrayList<>();

    private IPanel testHomePanel;
    private IPanel testNeutralPanel;
    private IPanel testBonusPanel;
    private IPanel testDropPanel;
    private IPanel testEncounterPanel;
    private IPanel testBossPanel;

    private long testSeed;
    @BeforeEach
    public void setUp() {
        suguri = new Player(PLAYER_NAME, 4, 1, -1, 2);
        shifuRobot = new Player(BOSS_NAME, 6, 5, -2, 4);
        chicken = new Player(WILD_NAME, 5, 3, -1, 1);
        testBonusPanel = new BonusPanel(1);
        testBossPanel = new BossPanel(3);
        testDropPanel = new DropPanel(1);
        testEncounterPanel = new EncounterPanel(2);
        testHomePanel = new HomePanel(3);
        testNeutralPanel = new NeutralPanel(1);
        testSeed = new Random().nextLong();
    }

    @Test
    public void begintheTurnTest(){
        testDropPanel.addPlayer(suguri);
        testDropPanel.addPlayer(shifuRobot);
        listOfPlayers.add(suguri);
        listOfPlayers.add(shifuRobot);
        //Phases(listOfPlayers,owner);





    }
}
