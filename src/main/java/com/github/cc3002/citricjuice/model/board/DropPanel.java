package main.java.com.github.cc3002.citricjuice.model.board;

import main.java.com.github.cc3002.citricjuice.model.Player;
import org.jetbrains.annotations.NotNull;

import com.github.cc3002.citricjuice.model.Player;
import org.jetbrains.annotations.NotNull;

import static java.lang.Math.max;

/**
 */
<<<<<<< Updated upstream
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
=======
 public class DropPanel extends AbstractPanel {
    /**
     * Creates a new panel.
     *
     * @param a
     * @param b the coordinates of the panel.
     */
    public DropPanel(int a, int b) {
        super(a, b);
    }

    @Override
    public void activateBy(@NotNull Player player) {
        player.reduceStarsBy(max(player.roll()*player.getNormaLevel(),0));

    }

    /**
     * Reduces the player's star count by the D6 roll multiplied by the player's norma level.
     */

>>>>>>> Stashed changes
}
