package com.github.cc3002.citricjuice.model.board;

import com.github.cc3002.citricjuice.model.Player;
import org.jetbrains.annotations.NotNull;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.*;

/**
 * Class that represents a panel in the board of the game.
 *
 * @author <a href="mailto:ignacio.slater@ug.uchile.cl">Ignacio Slater
 *     Muñoz</a>.
 * @version 1.0.6-rc.2
 * @since 1.0
 */
public abstract class AbstractPanel implements IPanel {
  private final int id;
  private final Set<IPanel> nextPanels;
  private final List<Player> Players;



  /**
   * Creates a new panel.
   *
   * @param id the unique code for the panel
   */
  public AbstractPanel(int id) {
    nextPanels = new HashSet<>();
    Players = new ArrayList<>();
    this.id = id;
  }

  public int getId() {
    return id;
  }

  /**
   * >>>>>>> Stashed changes;
   * <p>
   * <p>
   * /**
   * Returns a copy of this panel's next ones.
   *
   * @return Set<IPanel>
   */
  public Set<IPanel> getNextPanels() {
    return Set.copyOf(nextPanels);
  }

  /**
   * Adds a new adjacent panel to this one.
   *
   * @param panel the panel to be added.
   */
  public void addNextPanel(IPanel panel) {
    if (!this.equals(panel) ) {
      nextPanels.add(panel);

    }


  }



  /**
   * Adds a new player in the panel
   *
   * @param player the player to be added.
   */
  public void addPlayer(Player player) {
    Players.add(player);

  }
  /**
   * Removes a player in the panel
   *
   * @param player the player to be remove.
   */
  public void removePlayer(Player player) {
    Players.remove(player);
  }





  @Override
  public boolean equals(final Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof AbstractPanel)) {
      return false;
    }
    final AbstractPanel that = (AbstractPanel) o;
    boolean result = equalNextPanels(1);
    result = result && id == that.id &&
            Objects.equals(getPlayers(), that.getPlayers());
    return result;
  }

  @Override
  public boolean equalNextPanels(final int i) {
    boolean result = true;
    for (IPanel panel :
            nextPanels) {
      result = result && panel.equalNextPanels(i - 1);
    }
    return result;
  }

  @Override
  public int hashCode() {
    return 31 * Objects.hash(id, getPlayers()) + nextPanelsHash(1);
  }

  @Override
  public int nextPanelsHash(int acc) {
    int result = 1;
    if (acc == 0) {
      return result;
    }
    for (IPanel element : nextPanels)
      result = 31 * result + (element == null ? 0 : element.nextPanelsHash(acc - 1));
    return result;
  }

  /**
   * AUMENTA the player's star count by the D6 roll multiplied by the maximum between the player's
   * norma level and three.
   */
  public abstract void activateBy(final @NotNull Player player);




  public List<Player> getPlayers() {
    return Players;
  }

}


