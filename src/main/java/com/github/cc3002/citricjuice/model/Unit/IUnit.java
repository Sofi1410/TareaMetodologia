package com.github.cc3002.citricjuice.model.Unit;

public interface IUnit {
    void increaseVictoriesByPlayer(Player player);

    void increaseStarsByPlayer(Player player);

    IUnit copy();

    void increaseVictoriesByWild(WildUnit wildUnit);

    void increaseVictoriesByBoss(BossUnit bossUnit);

    void increaseStarsByWild(WildUnit wildUnit);

    void increaseStarsByBoss(BossUnit bossUnit);

    String getName();

    int getMaxHP();

    int getAtk();

    int getDef();

    int getEvd();

    int attack();

    void evade(int attack);

    boolean isK_O();

    void defend(int attack);

    void setSeed(long l);

    int getCurrentHP();

    void increaseStarsBy(IUnit victim);

    void increaseVictoriesBy(IUnit victim);
}
