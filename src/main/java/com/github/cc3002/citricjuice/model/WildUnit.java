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
    public void setEvd(int newEvd) {

    }

    @Override
    public void setAtk(int newAtk) {

    }

    @Override
    public void setDef(int newDef) {

    }

    @Override
    public void increaseVictoriesBy(Unit unit) {
        unit.increaseVictoriesByWild(this);

    }

    public void increaseVictoriesByWild(WildUnit wildUnit){
        wildUnit.setVictories(wildUnit.getVictories()+1);
    }
    public void increaseVictoriesByBoss(BossUnit bossUnit){
        bossUnit.setVictories(bossUnit.getVictories()+1);
    }
    @Override
    public void increaseVictoriesByPlayer(Player player){
        player.setVictories(player.getVictories()+1);
    }


    @Override
    public void increaseStarsBy(Unit unit) {
        unit.increaseStarsByWild(this);
    }
    public void increaseStarsByWild(WildUnit wildUnit){
        wildUnit.increaseStarsBy((int) Math.floor(this.getStars()*0.5));
        this.reduceStarsBy((int) Math.ceil(this.getStars() *0.5));
    }
    public void increaseStarsByBoss(BossUnit bossUnit){
        bossUnit.increaseStarsBy((int) Math.floor(this.getStars()*0.5));
        this.reduceStarsBy((int) Math.ceil(this.getStars()*0.5));
    }
    public void increaseStarsByPlayer(Player player) {
        player.increaseStarsBy(this.getStars());
        this.reduceStarsBy (this.getStars());
    }

    @Override
    public int attack() {
        return (this.roll()+this.getAtk());

    }
    @Override
    public void defend(int attack) {
        this.setCurrentHP(this.getCurrentHP()-Math.max(1, attack - (this.roll() + this.getDef())));
    }
    @Override
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
