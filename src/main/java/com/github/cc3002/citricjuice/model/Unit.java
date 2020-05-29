package com.github.cc3002.citricjuice.model;

public interface Unit {
    void increaseVictoriesByPlayer(Player player);

    void increaseStarsByPlayer(Player player);

    public Unit copy();

    void increaseVictoriesByWild(WildUnit wildUnit);

    void increaseVictoriesByBoss(BossUnit bossUnit);

    void increaseStarsByWild(WildUnit wildUnit);

    void increaseStarsByBoss(BossUnit bossUnit);
}
