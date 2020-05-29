package com.github.cc3002.citricjuice.model.board;

import com.github.cc3002.citricjuice.model.Player;
import org.jetbrains.annotations.NotNull;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Class that represents a panel in the board of the game.
 *
 * @author <a href="mailto:ignacio.slater@ug.uchile.cl">Ignacio Slater
 *     Muñoz</a>.
 * @version 1.0.6-rc.2
 * @since 1.0
 */
public abstract class AbstractPanel implements Panel {
  private final int a;
  private final int b;
  private final Set<Panel> nextPanels = new HashSet<>();

  /**
   * Creates a new panel.
   *
   * @param a coordinate of the panel
   * @param b the coordinates of the panel.
   */
  public AbstractPanel(int a, int b) {
    this.a = a;
    this.b = b;
  }

  /**
   * >>>>>>> Stashed changes;
   * <p>
   * <p>
   * /**
   * Returns a copy of this panel's next ones.
   *
   * @return
   */
  public Set<Panel> getNextPanels() {
    return Set.copyOf(nextPanels);
  }

  /**
   * Adds a new adjacent panel to this one.
   *
   * @param panel the panel to be added.
   */
  public void addNextPanel(Panel panel) {
    nextPanels.add(panel);
  }

  @Override
  public  boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    AbstractPanel that = (AbstractPanel) o;
    return a == that.a &&
            b == that.b &&
            Objects.equals(nextPanels, that.nextPanels);
  }

  @Override
  public int hashCode() {
    return Objects.hash(a, b, nextPanels);
  }

  /**
   * AUMENTA the player's star count by the D6 roll multiplied by the maximum between the player's
   * norma level and three.
   */
  public abstract void activateBy(final @NotNull Player player);





}


