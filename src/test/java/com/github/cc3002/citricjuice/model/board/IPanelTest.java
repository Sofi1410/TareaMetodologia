package com.github.cc3002.citricjuice.model.board;

import com.github.cc3002.citricjuice.model.Unit.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import java.util.Random;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author <a href="mailto:ignacio.slater@ug.uchile.cl">Ignacio Slater M.</a>.
 * @version 1.0.6-rc.1
 * @since 1.0
 */
class IPanelTest {
  private final static String PLAYER_NAME = "Suguri";
  private final static int BASE_HP = 4;
  private final static int BASE_ATK = 1;
  private final static int BASE_DEF = -1;
  private final static int BASE_EVD = 2;
  private IPanel testHomePanel;
  private IPanel testNeutralPanel;
  private IPanel testBonusPanel;
  private IPanel testDropPanel;
  private IPanel testEncounterPanel;
  private IPanel testBossPanel;
  private Player Suguri;
  private long testSeed;

  @BeforeEach
  public void setUp() {
    testBonusPanel = new BonusPanel(1);
    testBossPanel = new BossPanel(3);
    testDropPanel = new DropPanel(1);
    testEncounterPanel = new EncounterPanel(2);
    testHomePanel = new HomePanel(3);
    testNeutralPanel = new NeutralPanel(1);
    testSeed = new Random().nextLong();
    Suguri = new Player(PLAYER_NAME, BASE_HP, BASE_ATK, BASE_DEF, BASE_EVD);
  }

  @Test
  public void constructorTest() {
    assertEquals(BonusPanel.class, testBonusPanel.getClass());
    assertEquals(BossPanel.class, testBossPanel.getClass());
    assertEquals(DropPanel.class, testDropPanel.getClass());
    assertEquals(EncounterPanel.class, testEncounterPanel.getClass());
    assertEquals(HomePanel.class, testHomePanel.getClass());
    assertEquals(NeutralPanel.class, testNeutralPanel.getClass());
  }

  @Test
  public void nextPanelTest() {
    assertTrue(testNeutralPanel.getNextPanels().isEmpty());
    final var expectedPanel1 = new NeutralPanel(30);
    final var expectedPanel2 = new NeutralPanel(20);

    testNeutralPanel.addNextPanel(expectedPanel1);
    assertEquals(1, testNeutralPanel.getNextPanels().size());

    testNeutralPanel.addNextPanel(expectedPanel2);
    assertEquals(2, testNeutralPanel.getNextPanels().size());

    testNeutralPanel.addNextPanel(expectedPanel2);
    assertEquals(2, testNeutralPanel.getNextPanels().size());

    assertEquals(Set.of(expectedPanel1, expectedPanel2),testNeutralPanel.getNextPanels());
  }

  @Test
  public void homePanelTest() {
    Suguri.setHomePanel(testHomePanel);
    assertEquals(Suguri.getMaxHP(), Suguri.getCurrentHP());
    testHomePanel.activateBy(Suguri);
    assertEquals(Suguri.getMaxHP(), Suguri.getCurrentHP());

    Suguri.setCurrentHP(1);
    testHomePanel.activateBy(Suguri);
    assertEquals(2, Suguri.getCurrentHP());
  }

  @Test
  public void neutralPanelTest() {
    final var expectedSuguri = Suguri.copy();
    assertEquals(expectedSuguri, Suguri);
  }

  // region : Consistency tests
  @RepeatedTest(100)
  public void bonusPanelConsistencyTest() {
    int expectedStars = 0;
    assertEquals(expectedStars, Suguri.getStars());
    final var testRandom = new Random(testSeed);
    Suguri.setSeed(testSeed);
    for (int normaLvl = 1; normaLvl <= 6; normaLvl++) {
      final int roll = testRandom.nextInt(6) + 1;
      testBonusPanel.activateBy(Suguri);
      expectedStars += roll * Math.min(3, normaLvl);
      assertEquals(expectedStars, Suguri.getStars(),
              "Test failed with seed: " + testSeed);
      Suguri.normaClear();
    }
  }

  @RepeatedTest(100)
  public void dropPanelConsistencyTest() {
    int expectedStars = 30;
    Suguri.increaseStarsBy(30);
    assertEquals(expectedStars, Suguri.getStars());
    final var testRandom = new Random(testSeed);
    Suguri.setSeed(testSeed);
    for (int normaLvl = 1; normaLvl <= 6; normaLvl++) {
      final int roll = testRandom.nextInt(6) + 1;
      testDropPanel.activateBy(Suguri);
      expectedStars = Math.max(expectedStars - roll * normaLvl, 0);
      assertEquals(expectedStars, Suguri.getStars(),
              "Test failed with seed: " + testSeed);
      Suguri.normaClear();
    }
  }
  // endregion
}