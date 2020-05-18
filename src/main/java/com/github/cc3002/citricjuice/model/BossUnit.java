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
        unit.increaseVictoriesByBoss(this);
    }
    /**
     * The loser (BossUnit) add the appropriate amount of victories to the winner
     * @param wildUnit the winner
     */
    public void increaseVictoriesByWild(WildUnit wildUnit){
        wildUnit.setVictories(wildUnit.getVictories()+3);
    }
    /**
     * The loser (BossUnit) add the appropriate amount of victories to the winner
     * @param bossUnit the winner
     */
    public void increaseVictoriesByBoss(BossUnit bossUnit){
        bossUnit.setVictories(bossUnit.getVictories()+3);
    }
    /**
     * The loser (BossUnit) add the appropriate amount of victories to the winner
     * @param player the winner
     */
    public void increaseVictoriesByPlayer(Player player){
        player.setVictories(player.getVictories()+3);
    }



    /**
     * Add stars to the winner an reduce the stars to a
     * loser in a combat
     * @param unit loser
     */
    public void increaseStarsBy(Unit unit) {
        unit.increaseStarsByBoss(this);
    }
    /**
     * The loser (BossUnit) add the appropriate amount of stars to the winner
     * an reduce its starts appropriately
     * @param wildUnit the winner
     */
    public void increaseStarsByWild(WildUnit wildUnit){
        wildUnit.increaseStarsBy((int) Math.floor(this.getStars()*0.5));
        this.reduceStarsBy((int) Math.ceil(this.getStars() *0.5));
    }
    /**
     * The loser (BossUnit) add the appropriate amount of stars to the winner
     * an reduce its starts appropriately
     * @param bossUnit the winner
     */
    public void increaseStarsByBoss(BossUnit bossUnit){
        bossUnit.increaseStarsBy((int) Math.floor(this.getStars()*0.5));
        this.reduceStarsBy((int) Math.ceil(this.getStars()*0.5));
    }
    /**
     * The loser (BossUnit) add the appropriate amount of stars to the winner
     * an reduce its starts appropriately
     * @param player the winner
     */
    public void increaseStarsByPlayer(Player player) {
        player.increaseStarsBy(this.getStars());
        this.reduceStarsBy (this.getStars());
    }


    public int attack() {
        assert this.getCurrentHP()!=0;
        return this.roll()+this.getAtk();

    }

    /**
     * The BossUnit decide to defend
     * Always lose Hp ,at least 1
     * @param attack made by the opponent
     */
    public void defend(int attack) {
        int def= this.roll() + this.getDef();
        int HP=this.getCurrentHP();
        this.setCurrentHP(Math.max(HP-Math.max(1, attack - def),0));
    }

    /**
     * The BossUnit decide to avoid
     * if the attack is bigger than evd reduce its HP
     * @param attack made by the opponent
     */
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
