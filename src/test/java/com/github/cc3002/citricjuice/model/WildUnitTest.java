package com.github.cc3002.citricjuice.model;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

public class WildUnitTest {
    private final static String PLAYER_NAME = "Suguri";
    private Player suguri;
    private final static String BOSS_NAME = "Shifu Robot";
    private BossUnit shifuRobot;
    private final static String WILD_NAME = "Chicken";
    private WildUnit chicken;
    private final static String WILD_2_NAME = "Pardo";
    private WildUnit pardo;

    @BeforeEach
    public void setUp() {
        suguri = new Player(PLAYER_NAME, 4, 1, -1, -2);
        shifuRobot = new BossUnit(BOSS_NAME, 6, 5, -2, 4);
        chicken = new WildUnit(WILD_NAME, 5, 3, -1, 4);

        pardo = new WildUnit(WILD_2_NAME, 5, 3, -1, 1);
    }
    @RepeatedTest(100)
    // Assert that the int attack return by WildUnit.attack()
    //equals the expected
    public void attackTest(){
        long seed= new Random().nextLong();
        int r= new Random(seed).nextInt(6)+1;
        chicken.setSeed(seed);
        int expectedattack= chicken.getAtk()+r;
        assertEquals(expectedattack,chicken.attack());
    }
    @RepeatedTest(100)
    // Assert the HP in a WildUnit after being attack and
    // decided to avoid
    public void avoidTest(){
        //Test with the case is : Player attacks, Wild Unit
        //decided to avoid
        long seed= new Random().nextLong();
        int r= new Random(seed).nextInt(6)+1;
        chicken.setSeed(seed);
        suguri.setSeed(seed);
        int chickenHp= chicken.getCurrentHP();
        int atk=suguri.attack();
        chicken.avoid(atk);
        if(atk>(chicken.getEvd()+r)){
            int expectedHP=Math.max(0,chickenHp-atk);
            assertEquals(expectedHP,chicken.getCurrentHP());}
        else {
            assertEquals(chickenHp,chicken.getCurrentHP());
        }

    }

    @RepeatedTest(100)
    //Assert the HP in a WildUnit after being attack and
    //decided to defend
    public void defendTest(){
        long seed= new Random().nextLong();
        int r= new Random(seed).nextInt(6)+1;
        chicken.setSeed(seed);
        shifuRobot.setSeed(seed);
        int atk=shifuRobot.attack();
        int defend=Math.max(atk-chicken.getDef()-r,1);
        int expectedHP=Math.max(0,chicken.getCurrentHP()-defend);
        chicken.defend(atk);
        assertEquals(expectedHP,chicken.getCurrentHP());

    }
    @Test
    //Test that assert the correct stars in a Unit when
    // a Unit wins a battle against a WildUnit
    public void increaseStartsByTest(){
        shifuRobot.increaseStarsBy(11);
        suguri.increaseStarsBy(15);
        chicken.increaseStarsBy(9);
        pardo.increaseStarsBy(3);

        //case when a WildUnit wins a battle against a WildUnit
        int chickenStars = chicken.getStars();
        int pardoStars= pardo.getStars();
        int expectedChickenStars= (int) (chickenStars+Math.floor(pardoStars*0.5));
        int expectedPardoStars= (int) (pardoStars-Math.ceil(pardoStars*0.5));
        chicken.increaseStarsBy(pardo);
        assertEquals(expectedChickenStars,chicken.getStars());
        assertEquals(expectedPardoStars,pardo.getStars());

        //case when a Boss Unit wins a battle against a WildUnit
        int shifuRobotStars = shifuRobot.getStars();
        int pardoStars2= pardo.getStars();
        int expectedShifuRobotStars= (int) (shifuRobotStars+Math.floor(pardoStars2*0.5));
        int expectedPardoStars2= (int) (pardoStars2-Math.ceil(pardoStars2*0.5));
        shifuRobot.increaseStarsBy(pardo);
        assertEquals(expectedShifuRobotStars,shifuRobot.getStars());
        assertEquals(expectedPardoStars2,pardo.getStars());

        //case when a Player wins a battle against a Player
        int pardoStars3 = pardo.getStars();
        int suguriStars= suguri.getStars();
        suguri.increaseStarsBy(pardo);
        int expectedsuguriStars3= (suguriStars+pardoStars3);
        int expectedPardoStars3=0;
        assertEquals(expectedPardoStars3,pardo.getStars());
        assertEquals(expectedsuguriStars3,suguri.getStars());
    }
    @Test
    //Test that assert the correct victories in a Unit when
    // a Unit wins a battle against a WildUnit
    public void increaseVictoriesByTest(){
        //case when a Wild Unit wins a battle against a Wild Unit
        int chickenVic = chicken.getVictories();
        int pardoVic= pardo.getVictories();
        int expectedChickenVic= chickenVic+1;
        chicken.increaseVictoriesBy(pardo);
        assertEquals(expectedChickenVic,chicken.getVictories());
        assertEquals(pardoVic,pardo.getVictories());

        //case when a Boss Unit wins a battle against a Wild Unit
        int shifuVic = shifuRobot.getVictories();
        int pardoVic2= pardo.getVictories();
        int expectedShifuVic= shifuVic+1;
        shifuRobot.increaseVictoriesBy(pardo);
        assertEquals(expectedShifuVic,shifuRobot.getVictories());
        assertEquals(pardoVic2,pardo.getVictories());

        //case when a Player wins a battle against a Wild Unit
        int pardoVic3 = pardo.getVictories();
        int suguriVic= suguri.getVictories();
        int expectedSuguriVic= suguriVic+1;
        suguri.increaseVictoriesBy(pardo);
        assertEquals(pardoVic3,pardo.getVictories());
        assertEquals(expectedSuguriVic,suguri.getVictories());

    }

    @Test
    public void copyTest() {
        final var expectedChicken =new WildUnit(WILD_NAME, 5, 3, -1, 4);
        chicken.increaseStarsBy(2);
        assert chicken.getStars()!=0;
        final var actualChicken = chicken.copy();
        assertEquals(0,actualChicken.getStars());
        // Checks that the copied player have the same parameters as the original
        assertEquals(expectedChicken, actualChicken);
        // Checks that the copied player doesn't reference the same object
        assertNotSame(expectedChicken, actualChicken);

    }

    @Test
    /*
     *This test assert that you can`t change the parameters
     * atk,edv,def in a WildUnit
     */
    public void parametersTest(){
        final var expectedAtk= chicken.getAtk();
        final var expectedEvd=chicken.getEvd();
        final var expectedDef=chicken.getDef();
        chicken.setAtk(4);
        chicken.setDef(5);
        chicken.setEvd(-10);
        assertEquals(expectedAtk,chicken.getAtk());
        assertEquals(expectedDef,chicken.getDef());
        assertEquals(expectedEvd,chicken.getEvd());
    }


}
