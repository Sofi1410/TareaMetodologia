package com.github.cc3002.citricjuice.model.board;
import com.github.cc3002.citricjuice.model.AbstractUnit;
import com.github.cc3002.citricjuice.model.Player;
import org.jetbrains.annotations.NotNull;

/**

 */
public class BonusPanel extends AbstractPanel {
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
  @Override
  public void activateBy(@NotNull Player player) {
    player.increaseStarsBy(player.roll() * Math.min(player.getNormaLevel(), 3));
  }

}
