<<<<<<< Updated upstream
package main.java.com.github.cc3002.citricjuice.model.board;

import main.java.com.github.cc3002.citricjuice.model.Player;
=======
package com.github.cc3002.citricjuice.model.board;
import com.github.cc3002.citricjuice.model.Player;
>>>>>>> Stashed changes
import org.jetbrains.annotations.NotNull;

/**

 */
<<<<<<< Updated upstream
public class BonusPanel extends AbstractPanel{

  public BonusPanel(final int c, final int d) {
    super(c,d);
  }
=======
public class BonusPanel extends AbstractPanel {
>>>>>>> Stashed changes
  /**
   * Creates a new panel.
   *
   * @param a
   * @param b the coordinates of the panel.
   */
  public BonusPanel(int a, int b) {
    super(a, b);
  }

  /**
   * AUMENTA the player's star count by the D6 roll multiplied by the maximum between the player's
   * norma level and three.
   */
<<<<<<< Updated upstream
  public static void applyBonusTo(final @NotNull Player player) {
=======
  @Override
  public void activateBy(@NotNull Player player) {
>>>>>>> Stashed changes
    player.increaseStarsBy(player.roll() * Math.min(player.getNormaLevel(), 3));
  }

}
