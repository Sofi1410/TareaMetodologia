package com.github.cc3002.citricjuice.model.Unit;


public class WildUnit extends AbstractUnit {
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



    /**
     * Add victories to the winner in a battle
     * @param IUnit loser
     */
    public void increaseVictoriesBy(IUnit IUnit) {
        IUnit.increaseVictoriesByWild(this);

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
     * @param IUnit loser
     */
    public void increaseStarsBy(IUnit IUnit) {
        IUnit.increaseStarsByWild(this);
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




    @Override
    public WildUnit copy() {
        return new WildUnit(this.getName(), this.getMaxHP(), this.getAtk(), this.getDef(), this.getEvd());
    }
}
