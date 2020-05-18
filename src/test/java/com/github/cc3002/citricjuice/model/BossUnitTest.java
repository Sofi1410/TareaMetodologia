package com.github.cc3002.citricjuice.model;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

public class BossUnitTest {
    private final static String PLAYER_NAME = "Suguri";
    private Player suguri;
    private final static String BOSS_NAME = "Shifu Robot";
    private BossUnit shifuRobot;
    private final static String WILD_NAME = "Chicken";
    private WildUnit chicken;
    private final static String BOSS_2_NAME = "Polar";
    private BossUnit polar;
    @BeforeEach
    public void setUp() {
        suguri = new Player(PLAYER_NAME, 4, 1, -1, -2);
        shifuRobot = new BossUnit(BOSS_NAME, 6, 5, -2, 4);
        chicken = new WildUnit(WILD_NAME, 5, 3, -1, 4);
        polar = new BossUnit(BOSS_2_NAME, 5, 3, -1, 1);
    }
    @RepeatedTest(100)
    // Assert that the int attack return by BossUnit.attack()
    //equals the expected
    public void attackTest(){
        long seed= new Random().nextLong();
        int r= new Random(seed).nextInt(6)+1;
        shifuRobot.setSeed(seed);
        int expectedattack= shifuRobot.getAtk()+r;
        assertEquals(expectedattack,shifuRobot.attack());
    }
    @RepeatedTest(100)
    //Assert the HP in a BossUnit after being attack and
    //decided to defend
    public void defendTest(){
        long seed= new Random().nextLong();
        int r= new Random(seed).nextInt(6)+1;
        chicken.setSeed(seed);
        shifuRobot.setSeed(seed);
        int atk=chicken.attack();
        int defend=Math.max(atk-shifuRobot.getDef()-r,1);
        int expectedHP=Math.max(0,shifuRobot.getCurrentHP()-defend);
        shifuRobot.defend(atk);
        assertEquals(expectedHP,shifuRobot.getCurrentHP());

    }
    @RepeatedTest(100)
    // Assert the HP in a BossUnit after being attack and
    // decided to avoid
    public void avoidTest(){
        long seed= new Random().nextLong();
        long seed2= new Random().nextLong();
        int r= new Random(seed).nextInt(6)+1;
        shifuRobot.setSeed(seed);
        chicken.setSeed(seed2);
        int suguriHP= shifuRobot.getCurrentHP();
        int atk=chicken.attack();
        int evd=shifuRobot.getEvd()+r;
        shifuRobot.avoid(atk);
        if(atk >= evd){

            int expectedHP=Math.max(0,(suguriHP-atk));
            assertEquals(expectedHP,shifuRobot.getCurrentHP());}
        else {
            assertEquals(suguriHP,shifuRobot.getCurrentHP());
        }

    }
    @Test
    //Test that assert the correct stars in a Unit when
    // a Unit wins a battle against a BossUnit
    public void increaseStartsByTest(){
        shifuRobot.increaseStarsBy(11);
        suguri.increaseStarsBy(15);
        chicken.increaseStarsBy(9);
        polar.increaseStarsBy(3);

        //case when a WildUnit wins a battle against a BossUnit
        int chickenStars = chicken.getStars();
        int polarStars= polar.getStars();
        int expectedChickenStars= (int) (chickenStars+Math.floor(polarStars*0.5));
        int expectedPolarStars= (int) (polarStars-Math.ceil(polarStars*0.5));
        chicken.increaseStarsBy(polar);
        assertEquals(expectedChickenStars,chicken.getStars());
        assertEquals(expectedPolarStars,polar.getStars());

        //case when a BossUnit wins a battle against a BossUnit
        int shifuRobotStars = shifuRobot.getStars();
        int polarStars2= polar.getStars();
        int expectedShifuRobotStars= (int) (shifuRobotStars+Math.floor(polarStars2*0.5));
        int expectedPolarStars2= (int) (polarStars2-Math.ceil(polarStars2*0.5));
        shifuRobot.increaseStarsBy(polar);
        assertEquals(expectedShifuRobotStars,shifuRobot.getStars());
        assertEquals(expectedPolarStars2,polar.getStars());

        //case when a Player wins a battle against a BossUnit
        int polarStars3 = polar.getStars();
        int suguriStars= suguri.getStars();
        suguri.increaseStarsBy(polar);
        int expectedsuguriStars3= (suguriStars+polarStars3);
        int expectedPolarStars3=0;
        assertEquals(expectedPolarStars3,polar.getStars());
        assertEquals(expectedsuguriStars3,suguri.getStars());
    }
    @Test
    //Test that assert the correct victories in a Unit when
    // a Unit wins a battle against a Boss Unit
    public void increaseVictoriesByTest(){
        //case when a Wild Unit wins a battle against a Boss Unit
        int chickenVic = chicken.getVictories();
        int expectedpolarVic= polar.getVictories();
        int expectedChickenVic= chickenVic+3;
        chicken.increaseVictoriesBy(polar);
        assertEquals(expectedChickenVic,chicken.getVictories());
        assertEquals(expectedpolarVic,polar.getVictories());

        //case when a Boss Unit wins a battle against a Boss Unit
        int shifuVic = shifuRobot.getVictories();
        int expectedpolarVic2= polar.getVictories();
        int expectedShifuVic= shifuVic+3;
        shifuRobot.increaseVictoriesBy(polar);
        assertEquals(expectedShifuVic,shifuRobot.getVictories());
        assertEquals(expectedpolarVic2,polar.getVictories());

        //case when a Player wins a battle against a Boss Unit
        int expectedpolarVic3 = polar.getVictories();
        int suguriVic= suguri.getVictories();
        int expectedSuguriVic= suguriVic+3;
        suguri.increaseVictoriesBy(polar);
        assertEquals(expectedpolarVic3,polar.getVictories());
        assertEquals(expectedSuguriVic,suguri.getVictories());

    }

    @Test
    public void copyTest() {
        final var expectedShifuRobot =new BossUnit(BOSS_NAME, 6, 5, -2, 4);
        shifuRobot.increaseStarsBy(2);
        assert shifuRobot.getStars()!=0;
        final var actualShifuRobot = shifuRobot.copy();
        assertEquals(0,actualShifuRobot.getStars());
        // Checks that the copied player have the same parameters as the original
        assertEquals(expectedShifuRobot, actualShifuRobot);
        // Checks that the copied player doesn't reference the same object
        assertNotSame(expectedShifuRobot, actualShifuRobot);

    }
    @Test
    /*
     *This test assert that you can change the parameters
     * atk,edv,def in a BossUnit
     */
    public void parametersTest(){
        final var expectedAtk= shifuRobot.getAtk();
        final var expectedEvd=shifuRobot.getEvd();
        final var expectedDef=shifuRobot.getDef();
        shifuRobot.setAtk(4);
        shifuRobot.setDef(5);
        shifuRobot.setEvd(-10);
        assertEquals(expectedAtk,shifuRobot.getAtk());
        assertEquals(expectedDef,shifuRobot.getDef());
        assertEquals(expectedEvd,shifuRobot.getEvd());
    }


}

