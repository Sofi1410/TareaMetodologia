package com.github.cc3002.citricjuice.model;

public class BossUnit extends AbstractUnit {
    /**
     * Creates a new character.
     *
     * @param name the character's name.
     * @param hp   the initial (and max) hit points of the character.
     * @param atk  the base damage the character does.
     * @param def  the base defense of the character.
     * @param evd
     */
    public BossUnit(String name, int hp, final int atk, final int def,final int evd) {
        super(name, hp,atk, def, evd);
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
        unit.increaseVictoriesByBoss(this);
    }
    public void increaseVictoriesByWild(WildUnit wildUnit){
        wildUnit.setVictories(wildUnit.getVictories()+3);
    }
    public void increaseVictoriesByBoss(BossUnit bossUnit){
        bossUnit.setVictories(bossUnit.getVictories()+3);
    }
    public void increaseVictoriesByPlayer(Player player){
        player.setVictories(player.getVictories()+3);
    }

    @Override
    public void increaseStarsBy(Unit unit) {
        unit.increaseStarsByBoss(this);
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
        assert this.getCurrentHP()!=0;
        return this.roll()+this.getAtk();

    }

    @Override
    public void defend(int attack) {
        int def= this.roll() + this.getDef();
        int HP=this.getCurrentHP();
        this.setCurrentHP(Math.max(HP-Math.max(1, attack - def),0));
    }
    @Override
    public void avoid(int attack) {
        int evd=(this.getEvd() + this.roll());
        int HP=this.getCurrentHP();
        int actual = Math.max(((evd > attack)? HP:HP-attack),0);
        this.setCurrentHP(actual);
    }


    @Override
    public BossUnit copy() {
        return new BossUnit(this.getName(), this.getMaxHP(), this.getAtk(), this.getDef(), this.getEvd());
    }
}
