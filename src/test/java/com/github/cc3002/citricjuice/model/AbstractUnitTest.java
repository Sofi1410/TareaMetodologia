package com.github.cc3002.citricjuice.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AbstractUnitTest {
    private final static String PLAYER_NAME = "Suguri";
    private Player suguri;
    private final static String BOSS_NAME = "Shifu Robot";
    private BossUnit shifuRobot;
    private final static String WILD_NAME = "Suguri";
    private WildUnit chicken;

    @BeforeEach
    public void setUp() {
        suguri = new Player(PLAYER_NAME, 4, 1, -1, 2);
        shifuRobot = new BossUnit(BOSS_NAME, 6, 5, -2, 4);
        chicken = new WildUnit(WILD_NAME, 5, 3, -1, 1);
    }

    @Test
    public void constructorTest() {
        final var expectedSuguri = new Player(PLAYER_NAME, 4, 1, -1, 2);
        assertEquals(expectedSuguri, suguri);
        assertEquals(suguri.getStars(),0);
        assertEquals(suguri.getVictories(),0);
        final var expectedShifuRobot = new BossUnit(BOSS_NAME, 6, 5, -2, 4);
        assertEquals(expectedShifuRobot, shifuRobot);
        assertEquals(shifuRobot.getStars(),0);
        assertEquals(shifuRobot.getVictories(),0);
        final var expectedChicken = new WildUnit(WILD_NAME, 5, 3, -1, 1);
        assertEquals(expectedChicken, chicken);
        assertEquals(chicken.getStars(),0);
        assertEquals(chicken.getVictories(),0);
    }

    @Test
    public void testEquals() {
        final var o = new Object();
        assertNotEquals(suguri, o);
        assertEquals(suguri, suguri);
        final var expectedSuguri = new Player(PLAYER_NAME, 4, 1, -1, 2);
        assertEquals(expectedSuguri, suguri);

        final var p = new Object();
        assertNotEquals(shifuRobot, p);
        assertEquals(shifuRobot, shifuRobot);
        final var expectedShifuRobot = new BossUnit(BOSS_NAME, 6, 5, -2, 4);
        assertEquals(expectedShifuRobot, shifuRobot);

        final var q = new Object();
        assertNotEquals(chicken, o);
        assertEquals(chicken, chicken);
        final var expectedchicken = new WildUnit(WILD_NAME, 5, 3, -1, 1);
        assertEquals(expectedchicken, chicken);
    }

    @Test
    public void hitPointsTest() {
        assertEquals(suguri.getMaxHP(), suguri.getCurrentHP());
        suguri.setCurrentHP(2);
        assertEquals(2, suguri.getCurrentHP());
        suguri.setCurrentHP(-1);
        assertEquals(0, suguri.getCurrentHP());
        suguri.setCurrentHP(5);
        assertEquals(4, suguri.getCurrentHP());

        assertEquals(chicken.getMaxHP(), chicken.getCurrentHP());
        chicken.setCurrentHP(3);
        assertEquals(3, chicken.getCurrentHP());
        chicken.setCurrentHP(-4);
        assertEquals(0, chicken.getCurrentHP());
        //Case when we try to sethp with a int bigger than maxhp
        chicken.setCurrentHP(6);
        assertEquals(chicken.getMaxHP(), chicken.getCurrentHP());


        assertEquals(shifuRobot.getMaxHP(), shifuRobot.getCurrentHP());
        shifuRobot.setCurrentHP(2);
        assertEquals(2, shifuRobot.getCurrentHP());
        shifuRobot.setCurrentHP(-1);
        assertEquals(0, shifuRobot.getCurrentHP());
        shifuRobot.setCurrentHP(20);
        assertEquals(shifuRobot.getMaxHP(), shifuRobot.getCurrentHP());
    }


    // region : consistency tests
    @RepeatedTest(100)
    public void hitPointsConsistencyTest() {
        final long testSeed = new Random().nextLong();
        // We're gonna try and set random hit points in [-maxHP * 2, maxHP * 2]
        final int testHP = new Random(testSeed).nextInt(4 * suguri.getMaxHP() + 1)
                - 2 * suguri.getMaxHP();
        suguri.setCurrentHP(testHP);
        assertTrue(0 <= suguri.getCurrentHP()
                        && suguri.getCurrentHP() <= suguri.getMaxHP(),
                suguri.getCurrentHP() + "is not a valid HP value"
                        + System.lineSeparator() + "Test failed with random seed: "
                        + testSeed);

        final long testSeed2 = new Random().nextLong();
        // We're gonna try and set random hit points in [-maxHP * 2, maxHP * 2]
        final int testHP2 = new Random(testSeed).nextInt(4 * chicken.getMaxHP() + 1)
                - 2 * chicken.getMaxHP();
        chicken.setCurrentHP(testHP2);
        assertTrue(0 <= chicken.getCurrentHP()
                        && chicken.getCurrentHP() <= chicken.getMaxHP(),
                chicken.getCurrentHP() + "is not a valid HP value"
                        + System.lineSeparator() + "Test failed with random seed: "
                        + testSeed2);
        final long testSeed3 = new Random().nextLong();
        // We're gonna try and set random hit points in [-maxHP * 2, maxHP * 2]
        final int testHP3 = new Random(testSeed).nextInt(4 * shifuRobot.getMaxHP() + 1)
                - 2 * shifuRobot.getMaxHP();
        shifuRobot.setCurrentHP(testHP3);
        assertTrue(0 <= shifuRobot.getCurrentHP()
                        && shifuRobot.getCurrentHP() <= shifuRobot.getMaxHP(),
                shifuRobot.getCurrentHP() + "is not a valid HP value"
                        + System.lineSeparator() + "Test failed with random seed: "
                        + testSeed3);
    }
    @RepeatedTest(100)
    public void rollConsistencyTest() {
        final long testSeed = new Random().nextLong();
        suguri.setSeed(testSeed);
        final int roll = suguri.roll();
        assertTrue(roll >= 1 && roll <= 6,
                roll + "is not in [1, 6]" + System.lineSeparator()
                        + "Test failed with random seed: " + testSeed);
        final long testSeed2 = new Random().nextLong();
        chicken.setSeed(testSeed2);
        final int roll2 = chicken.roll();
        assertTrue(roll2 >= 1 && roll2 <= 6,
                roll2 + "is not in [1, 6]" + System.lineSeparator()
                        + "Test failed with random seed: " + testSeed2);
        final long testSeed3 = new Random().nextLong();
        shifuRobot.setSeed(testSeed3);
        final int roll3 = shifuRobot.roll();
        assertTrue(roll3 >= 1 && roll3 <= 6,
                roll3 + "is not in [1, 6]" + System.lineSeparator()
                        + "Test failed with random seed: " + testSeed3);
    }
    // end region


}
