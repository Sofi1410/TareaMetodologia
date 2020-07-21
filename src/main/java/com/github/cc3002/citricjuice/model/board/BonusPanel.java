package com.github.cc3002.citricjuice.model.board;


import com.github.cc3002.citricjuice.model.Player;
import org.jetbrains.annotations.NotNull;

/**
SubClass of AbstractPanel
 The IUnit inhere gets a bonus in its HP
 */
public class BonusPanel extends AbstractPanel {


  /**
   * Creates a new panel.
   *
   * @param id the unique code for the panel
   */
  public BonusPanel(int id) {
    super(id);
  }

  /**
   * AUMENTA the player's star count by the D6 roll multiplied by the maximum between the player's
   * norma level and three.
   */
  @Override
  public void activateBy(@NotNull Player player) {
    player.increaseStarsBy(player.roll() * Math.min(player.getNormaLevel(), 3));
  }



}
