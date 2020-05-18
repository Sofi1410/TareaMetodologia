package com.github.cc3002.citricjuice.model;

import java.util.Random;

public class WildUnit extends AbstractUnit{
    /**
     * Creates a new character.
     *
     * @param name the character's name.
     * @param atk  the base damage the character does.
     * @param def  the base defense of the character.
     * @param evd
     */
    public WildUnit(String name,int hp, final int atk, final int def, final int evd) {
        super(name,hp,atk, def, evd);
    }

    @Override
    //Set region
    //A BossUnit can´t change atk,evd or def
    public void setEvd(int newEvd) {

    }

    public void setAtk(int newAtk) {

    }

    public void setDef(int newDef) {

    }
    //end region

    /**
     * Add victories to the winner in a battle
     * @param unit loser
     */
    public void increaseVictoriesBy(Unit unit) {
        unit.increaseVictoriesByWild(this);

    }
    /**
     * The loser (WildUnit) add the appropriate amount of victories to the winner
     * @param wildUnit the winner
     */
    public void increaseVictoriesByWild(WildUnit wildUnit){
        wildUnit.setVictories(wildUnit.getVictories()+1);
    }
    /**
     * The loser (WildUnit) add the appropriate amount of victories to the winner
     * @param bossUnit the winner
     */
    public void increaseVictoriesByBoss(BossUnit bossUnit){
        bossUnit.setVictories(bossUnit.getVictories()+1);
    }
    /**
     * The loser (WildUnit) add the appropriate amount of victories to the winner
     * @param player the winner
     */
    public void increaseVictoriesByPlayer(Player player){
        player.setVictories(player.getVictories()+1);
    }


    /**
     * Add stars to the winner an reduce the stars to a
     * loser in a combat
     * @param unit loser
     */
    public void increaseStarsBy(Unit unit) {
        unit.increaseStarsByWild(this);
    }
    /**
     * The loser (wildUnit) add the appropriate amount of stars to the winner
     * an reduce its starts appropriately
     * @param wildUnit the winner
     */
    public void increaseStarsByWild(WildUnit wildUnit){
        wildUnit.increaseStarsBy((int) Math.floor(this.getStars()*0.5));
        this.reduceStarsBy((int) Math.ceil(this.getStars() *0.5));
    }
    /**
     * The loser (wildUnit) add the appropriate amount of stars to the winner
     * an reduce its starts appropriately
     * @param bossUnit the winner
     */
    public void increaseStarsByBoss(BossUnit bossUnit){
        bossUnit.increaseStarsBy((int) Math.floor(this.getStars()*0.5));
        this.reduceStarsBy((int) Math.ceil(this.getStars()*0.5));
    }
    /**
     * The loser (wildUnit) add the appropriate amount of stars to the winner
     * an reduce its starts appropriately
     * @param player the winner
     */
    public void increaseStarsByPlayer(Player player) {
        player.increaseStarsBy(this.getStars());
        this.reduceStarsBy (this.getStars());
    }

    public int attack() {
        return (this.roll()+this.getAtk());

    }
    /**
     * The WildUnit decide to defend
     * Always lose Hp ,at least 1
     * @param attack made by the opponent
     */
    public void defend(int attack) {
        this.setCurrentHP(this.getCurrentHP()-Math.max(1, attack - (this.roll() + this.getDef())));
    }
    /**
     * The WildUnit decide to avoid
     * if the attack is bigger than evd reduce its HP
     * @param attack made by the opponent
     */
    public void avoid(int attack) {
        int evd=(this.getEvd() + this.roll());
        int a =Math.max(0,evd-attack);
        int b =Math.min(-attack,-evd);
        int c=Math.max(a,b);
        this.setCurrentHP(Math.max(this.getCurrentHP()+c,0));
    }



    @Override
    public WildUnit copy() {
        return new WildUnit(this.getName(), this.getMaxHP(), this.getAtk(), this.getDef(), this.getEvd());
    }
}
