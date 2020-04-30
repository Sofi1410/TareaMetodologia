package com.github.cc3002.citricjuice.model.board;

/**

 */
public class BonusPanel extends Panel{

  private final PanelType type;
  /**
   * Reduces the player's star count by the D6 roll multiplied by the maximum between the player's
   * norma level and three.
   */
  private static void applyBonusTo(final @NotNull Player player) {
    player.increaseStarsBy(player.roll() * Math.min(player.getNormaLevel(), 3));
  }
}
