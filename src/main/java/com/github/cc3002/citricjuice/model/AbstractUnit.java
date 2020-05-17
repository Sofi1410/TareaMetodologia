package com.github.cc3002.citricjuice.model;

import java.util.Random;

/**
 * This class represents a player in the game 99.7% Citric Liquid.
 *
 * @author <a href="mailto:ignacio.slater@ug.uchile.cl">Ignacio Slater
 *     Muñoz</a>.
 * @version 1.0.6-rc.3
 * @since 1.0
 */
public abstract class AbstractUnit implements Unit{
    private final Random random;
    private final String name;
    protected int atk;
    protected int def;
    protected int evd;
    private final int maxHP;
    private int stars;
    private int currentHP;
    private int victories;


  /**
   * Creates a new character.
   *
   * @param name
   *     the character's name.
   * @param atk
   *     the base damage the character does.
   * @param def
   *     the base defense of the character.
   * @param evd
   *     the base evasion of the character.
   */
  public AbstractUnit(String name, int hp, int atk, int def, int evd) {

        this.name = name;
        this.maxHP = currentHP = hp;
        this.atk = atk;
        this.def = def;
        this.evd = evd;
        stars=0;
        random = new Random();
    }



  /**
   * Returns this player's star count.
   */
  public int getStars() {
    return stars;
  }

  /**
   * Set's the seed for this player's random number generator.
   * <p>
   * The random number generator is used for taking non-deterministic decisions, this method is
   * declared to avoid non-deterministic behaviour while testing the code.
   */
  public void setSeed(final long seed) {
    random.setSeed(seed);
  }

    public abstract void setEvd(final int newEvd);

    public abstract void setAtk(final int newAtk);

    public abstract void setDef(final int newDef);

    /**
   * Returns a uniformly distributed random value in [1, 6]
   */
  public int roll() {
    return random.nextInt(6) + 1;
  }

    /**
    * Returns the character's name.
    */
    public String getName() {
    return name;
    }


    /**
    * Returns the current character's attack points.
    */
    public int getAtk() {
    return atk;
    }

    /**
    * Returns the current character's defense points.
    */
    public int getDef() {
    return def;
    }

    /**
    * Returns the current character's evasion points.
    */
    public int getEvd() {
    return evd;
    }




        /**
        * Reduces this player's star count by a given amount.
        * <p>
        * The star count will must always be greater or equal to 0
        */
    public void reduceStarsBy(final int amount) {
    stars = Math.max(0, stars - amount);
    }

    /**
     * Returns the current hit points of the character.
     */
    public int getCurrentHP() {
        return currentHP;
    }

    public void setCurrentHP(final int newHP) {
        this.currentHP = Math.max(Math.min(newHP, maxHP), 0);
    }

    /**
     * Returns the character's max hit points.
     */
    public int getMaxHP() {
        return maxHP;
    }

    /**
     * Returns the character's victories points.
     */
    public int getVictories() {
        return victories;
    }

    public void setVictories(int number){
        this.victories= number;
    }

    @Override
    public boolean equals(final Object o) {
    if (this == o) {
    return true;
    }
    if (!(o instanceof AbstractUnit)) {
    return false;
    }
    final AbstractUnit unit = (AbstractUnit) o;
    return getAtk() == unit.getAtk() &&
            getDef() == unit.getDef() &&
            getEvd() == unit.getEvd() &&
            getStars() == unit.getStars() &&
            getVictories()==unit.getVictories()&&
            getCurrentHP() == unit.getCurrentHP() &&
            getName().equals(unit.getName());
    }

    /**
     * Increases this player's Victory´s count by an amount.
     * @return
     */
    public abstract void   increaseVictoriesBy(Unit unit);
    /**
     * Increases this player's star count by an amount.
     */
    public abstract void increaseStarsBy(Unit unit);
    public void increaseStarsBy(final int amount) {
        stars += amount;
    }
    public abstract int attack();
    public abstract void defend(int attack);
    public abstract void avoid(int attack);

  /**
   * Returns a copy of this character.
   * @return
   */
  public abstract Unit copy();
}
