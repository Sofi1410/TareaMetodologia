package main.java.com.github.cc3002.citricjuice.model.board;

import main.java.com.github.cc3002.citricjuice.model.Player;
import org.jetbrains.annotations.NotNull;

/**

 */
public class BonusPanel extends Panel{

  public BonusPanel(final int c, final int d) {
    super(c,d);
  }
  /**
   * Reduces the player's star count by the D6 roll multiplied by the maximum between the player's
   * norma level and three.
   */
  private static void applyBonusTo(final @NotNull Player player) {
    player.increaseStarsBy(player.roll() * Math.min(player.getNormaLevel(), 3));
  }
}
