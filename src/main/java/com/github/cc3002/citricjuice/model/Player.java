package com.github.cc3002.citricjuice.model;
import java.util.Random;

public class Player extends AbstractUnit implements Unit {
    private int normaLevel;

    /**
     * Creates a new character.
     *
     * @param name the character's name.
     * @param hp   the initial (and max) hit points of the character.
     * @param atk  the base damage the character does.
     * @param def  the base defense of the character.
     * @param evd
     */

    public Player(String name, int hp, int atk, int def, int evd) {
        super(name,hp,atk, def, evd);

        normaLevel = 1;
    }

    /**
     * Returns the current norma level
     */
    public int getNormaLevel() {
        return normaLevel;
    }

    /**
     * Performs a norma clear action; the {@code norma} counter increases in 1.
     */
    public void normaClear() {
        normaLevel++;
    }

    @Override
    public void setEvd(int newEvd) {
        evd=newEvd;
    }

    public void setAtk(int newAtk) {
        atk=newAtk;
    }

    public void setDef(int newDef) {
        def=newDef;
    }


    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AbstractUnit)) {
            return false;
        }
        final Player player = (Player) o;
        return getMaxHP() == player.getMaxHP() &&
                getAtk() == player.getAtk() &&
                getDef() == player.getDef() &&
                getEvd() == player.getEvd() &&
                getNormaLevel() == player.getNormaLevel() &&
                getStars() == player.getStars() &&
                getCurrentHP() == player.getCurrentHP() &&
                getName().equals(player.getName());
    }


    public void increaseVictoriesBy(Unit unit) {
        unit.increaseVictoriesByPlayer(this);
    }

    public void increaseVictoriesByWild(WildUnit wildUnit){
        wildUnit.setVictories(wildUnit.getVictories()+2);
    }
    public void increaseVictoriesByBoss(BossUnit bossUnit){
        bossUnit.setVictories(bossUnit.getVictories()+2);
    }

    public void increaseVictoriesByPlayer(Player player){
        player.setVictories(player.getVictories()+2);
    }


    public void increaseStarsBy(Unit unit) {
        unit.increaseStarsByPlayer(this);
    }

    public void increaseStarsByWild(WildUnit wildUnit){
        wildUnit.increaseStarsBy((int) Math.floor(this.getStars()*0.5));
        this.reduceStarsBy((int) Math.ceil(this.getStars()*0.5));
    }
    public void increaseStarsByBoss(BossUnit bossUnit){
        bossUnit.increaseStarsBy((int) Math.floor(this.getStars()*0.5));
        this.reduceStarsBy((int) Math.ceil(this.getStars()*0.5));
    }
    public void increaseStarsByPlayer(Player player){
        player.increaseStarsBy((int) Math.floor(this.getStars()*0.5));
        this.reduceStarsBy((int) Math.ceil(this.getStars()*0.5));
    }



    public int attack() {
        assert this.getCurrentHP()!=0;
        return this.roll()+this.getAtk();

    }
    public void defend(int attack) {
       int damage=Math.max(1, attack - (this.roll() + this.getDef()));
       this.setCurrentHP(this.getCurrentHP()-damage);
    }
    public void avoid(int attack) {
        int evd=(this.getEvd() + this.roll());
        int HP=this.getCurrentHP();
        int actual = Math.max(((evd > attack)? HP:HP-attack),0);
        this.setCurrentHP(actual);
    }


    public Player copy() {
        return new Player(this.getName(), this.getMaxHP(), this.getAtk(), this.getDef(), this.getEvd());
    }

}
