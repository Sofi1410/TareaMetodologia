package com.github.cc3002.citricjuice.model.board;

<<<<<<< HEAD
/**

 */
public class BonusPanel extends Panel{
  /**
   * Reduces the player's star count by the D6 roll multiplied by the maximum between the player's
   * norma level and three.
   */
  private static void applyBonusTo(final @NotNull Player player) {
    player.increaseStarsBy(player.roll() * Math.min(player.getNormaLevel(), 3));
  }
=======
import com.github.cc3002.citricjuice.model.Player;
import org.jetbrains.annotations.NotNull;

/**
SubClass of AbstractPanel
 The Unit inhere gets a bonus in its HP
 */
public class BonusPanel extends AbstractPanel {
  /**
   * Creates a new panel.
   *
   * @param a
   * @param b
   * the coordinates of the panel.
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

>>>>>>> tarea1-entrega
}
