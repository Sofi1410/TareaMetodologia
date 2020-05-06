package main.java.com.github.cc3002.citricjuice.model.board;
/**
import com.github.cc3002.citricjuice.model.Player;
import org.jetbrains.annotations.NotNull;
i*/
import main.java.com.github.cc3002.citricjuice.model.Player;
import org.jetbrains.annotations.NotNull;

import java.util.HashSet;
import java.util.Set;

/**
 * Class that represents a panel in the board of the game.
 *
 * @author <a href="mailto:ignacio.slater@ug.uchile.cl">Ignacio Slater
 *     Muñoz</a>.
 * @version 1.0.6-rc.2
 * @since 1.0
 */
public abstract class AbstractPanel implements Panel{
  private final int a;
  private final int b;
  private final Set<AbstractPanel> nextPanels = new HashSet<>();

  /**
   * Creates a new panel.
   *
   * @param a
   * @param b
   *     the coordinates of the panel.
   */
  public AbstractPanel(int a, int b) {
    this.a = a;
    this.b = b;
  }

  /**
<<<<<<< Updated upstream
   * Restores a player's HP in 1.
   */
  public static void applyHealTo(final @NotNull Player player) {
    player.setCurrentHP(player.getCurrentHP() + 1);
  }

  /**
   * Reduces the player's star count by the D6 roll multiplied by the player's norma level.
   */
  public static void applyDropTo(final @NotNull Player player) {
    player.reduceStarsBy(player.roll() * player.getNormaLevel());
  }

  /**
   * Reduces the player's star count by the D6 roll multiplied by the maximum between the player's
   * norma level and three.
   */
  public static void applyBonusTo(final @NotNull Player player) {
  }

  /**
>>>>>>> Stashed changes;


  /**
   * Returns a copy of this panel's next ones.
   */
  public Set<AbstractPanel> getNextPanels() {
    return Set.copyOf(nextPanels);
  }

  /**
   * Adds a new adjacent panel to this one.
   *
   * @param panel
   *     the panel to be added.
   */
  public void addNextPanel(final AbstractPanel panel) {
    nextPanels.add(panel);
  }

  /**
   * Executes the appropriate action to the player according to this panel's type.

  public void activatedBy(final Player player) {
    switch (type) {
      case BONUS:
        applyBonusTo(player);
        break;
      case DROP:
        applyDropTo(player);
        break;
      case HOME:
        applyHealTo(player);
        break;
      case NEUTRAL:
        break;
    }
  }
   */
}
