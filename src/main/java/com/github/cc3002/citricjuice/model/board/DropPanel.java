package main.java.com.github.cc3002.citricjuice.model.board;

import main.java.com.github.cc3002.citricjuice.model.Player;
import org.jetbrains.annotations.NotNull;

/**
 */
 public class DropPanel extends Panel{
    public DropPanel(final int c, final int d) {
        super(c,d);
    }
  /**
   * Reduces the player's star count by the D6 roll multiplied by the player's norma level.
   */
  private static void applyDropTo(final @NotNull Player player) {
    player.reduceStarsBy(player.roll() * player.getNormaLevel());

  }
  /**
  VER COMO AÑADIR LO DE ESTRELLAS >0
   */
}
