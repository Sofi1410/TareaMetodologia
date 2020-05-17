package com.github.cc3002.citricjuice.model;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test suite for the players of the game.
 *
 * @author <a href="mailto:ignacio.slater@ug.uchile.cl">Ignacio Slater M.</a>.
 * @version 1.0.6-rc.1
 * @since 1.0
 */
public class PlayerTest {
  private final static String PLAYER_NAME = "Suguri";
  private Player suguri;
  private final static String BOSS_NAME = "Shifu Robot";
  private BossUnit shifuRobot;
  private final static String WILD_NAME = "Chicken";
  private WildUnit chicken;
  private long testSeed;
  private final static String PLAYER_2_NAME = "Pando";
  private Player pando;

  @BeforeEach
  public void setUp() {
    suguri = new Player(PLAYER_NAME, 4, 1, -1, 2);
    shifuRobot = new BossUnit(BOSS_NAME, 6, 5, -2, 4);
    chicken = new WildUnit(WILD_NAME, 5, 2, -1, 1);
    testSeed = new Random().nextLong();
    pando=new Player(PLAYER_2_NAME, 7, 4, 5, 3);
  }



  @RepeatedTest(100)
  public void attackTest(){
    long seed= new Random().nextLong();
    int r= new Random(seed).nextInt(6)+1;
    suguri.setSeed(seed);
    int expectedattack= suguri.getAtk()+r;
    assertEquals(expectedattack,suguri.attack());
  }
  @RepeatedTest(100)
  public void avoidTest(){
    long seed= new Random().nextLong();
    long seed2= new Random().nextLong();
    int r= new Random(seed).nextInt(6)+1;
    suguri.setSeed(seed);
    chicken.setSeed(seed2);
    int suguriHP= suguri.getCurrentHP();
    int atk=chicken.attack();
    int evd=suguri.getEvd()+r;
    suguri.avoid(atk);
    if(atk >= evd){

      int expectedHP=Math.max(0,(suguriHP-atk));
      assertEquals(expectedHP,suguri.getCurrentHP());}
    else {
      assertEquals(suguriHP,suguri.getCurrentHP());
    }

  }
  @RepeatedTest(100)
  public void defendTest(){
    long seed= new Random().nextLong();
    int r= new Random(seed).nextInt(6)+1;
    suguri.setSeed(seed);
    shifuRobot.setSeed(seed);
    int atk=shifuRobot.attack();
    suguri.defend(atk);
    int defend=Math.max(atk-suguri.getDef()-r,1);
    int expectedHP=Math.max(0,suguri.getCurrentHP()-defend);
    assertEquals(expectedHP,suguri.getCurrentHP());

  }

  @Test
  public void increaseStartsByTest(){
    shifuRobot.increaseStarsBy(10);
    suguri.increaseStarsBy(7);
    chicken.increaseStarsBy(9);
    pando.increaseStarsBy(2);

    //case when a Wild Unit wins a battle against a Player
    int chickenStars = chicken.getStars();
    int suguriStars= suguri.getStars();
    int expectedChickenStars= (int) (chickenStars+Math.floor(suguriStars*0.5));
    int expectedsuguriStars= (int) (suguriStars-Math.ceil(suguriStars*0.5));
    chicken.increaseStarsBy(suguri);
    assertEquals(expectedChickenStars,chicken.getStars());
    assertEquals(expectedsuguriStars,suguri.getStars());

    //case when a Boss Unit wins a battle against a Player
    int shifuRobotStars = shifuRobot.getStars();
    int suguriStars2= suguri.getStars();
    int expectedShifuRobotStars= (int) (shifuRobotStars+Math.floor(suguriStars2*0.5));
    int expectedsuguriStars2= (int) (suguriStars2-Math.ceil(suguriStars2*0.5));
    shifuRobot.increaseStarsBy(suguri);
    assertEquals(expectedShifuRobotStars,shifuRobot.getStars());
    assertEquals(expectedsuguriStars2,suguri.getStars());

    //case when a Player wins a battle against a Player
    int pandoStars = pando.getStars();
    int suguriStars3= suguri.getStars();
    pando.increaseStarsBy(suguri);
    int expectedPandoStars= (int) (pandoStars+Math.floor(suguriStars3*0.5));
    int expectedsuguriStars3= (int) (suguriStars3-Math.ceil(suguriStars3*0.5));
    assertEquals(expectedPandoStars,pando.getStars());
    assertEquals(expectedsuguriStars3,suguri.getStars());
  }
  @Test
  public void increaseVictoriesByTest(){
    //case when a Wild Unit wins a battle against a Player
    int chickenVictories = chicken.getVictories();
    int suguriVictories= suguri.getVictories();
    int expectedChickenVic= chickenVictories+2;
    int expectedSuguriVic= suguriVictories;
    chicken.increaseVictoriesBy(suguri);
    assertEquals(expectedChickenVic,chicken.getVictories());
    assertEquals(expectedSuguriVic,suguri.getVictories());

    //case when a Boss Unit wins a battle against a Player
    int shifuVic = shifuRobot.getVictories();
    int suguriVic= suguri.getVictories();
    int expectedShifuVic= shifuVic+2;
    int expectedSuguriVic2= suguriVic;
    shifuRobot.increaseVictoriesBy(suguri);
    assertEquals(expectedShifuVic,shifuRobot.getVictories());
    assertEquals(expectedSuguriVic2,suguri.getVictories());

    //case when a Player wins a battle against a Player
    int pandoVic = pando.getVictories();
    int suguriVic3= suguri.getVictories();
    int expectedPandoVic= pandoVic+2;
    int expectedSuguriVic3= suguriVic3;
    pando.increaseVictoriesBy(suguri);
    assertEquals(expectedPandoVic,pando.getVictories());
    assertEquals(expectedSuguriVic3,suguri.getVictories());

  }

  @Test
  public void copyTest() {
    final var expectedSuguri = new Player(PLAYER_NAME, 4, 1, -1, 2);
    final var actualSuguri = suguri.copy();
    // Checks that the copied player have the same parameters as the original
    assertEquals(expectedSuguri, actualSuguri);
    // Checks that the copied player doesn't reference the same object
    assertNotSame(expectedSuguri, actualSuguri);
  }
  @Test
  public void normaClearTest() {
    suguri.normaClear();
    assertEquals(2, suguri.getNormaLevel());
  }
  @Test
  public void parametersTest(){
    final var expectedAtk= 4;
    final var expectedEvd=3;
    final var expectedDef=-20;
    suguri.setAtk(expectedAtk);
    suguri.setDef(expectedDef);
    suguri.setEvd(expectedEvd);
    assertEquals(expectedAtk,suguri.getAtk());
    assertEquals(expectedDef,suguri.getDef());
    assertEquals(expectedEvd,suguri.getEvd());
  }
  // region : consistency tests

  @RepeatedTest(100)
  public void normaClearConsistencyTest() {
    final long testSeed = new Random().nextLong();
    // We're gonna test for 0 to 5 norma clears
    final int iterations = Math.abs(new Random(testSeed).nextInt(6));
    final int expectedNorma = suguri.getNormaLevel() + iterations;
    for (int it = 0; it < iterations; it++) {
      suguri.normaClear();
    }
    assertEquals(expectedNorma, suguri.getNormaLevel(),
                 "Test failed with random seed: " + testSeed);
  }
  //end region


}
