package com.github.cc3002.citricjuice.model.board;


import com.github.cc3002.citricjuice.model.Unit.*;
import org.jetbrains.annotations.NotNull;

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
  private  IPanel left;
  private  IPanel right;
  private  IPanel up;
  private  IPanel down;



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
    return nextPanels;
  }

  /**
   * Adds a new adjacent panel to this one.
   *
   * @param panel the panel to be added.
   */
  public void addNextPanel(IPanel panel) {
    if (this.getId()!=panel.getId() ) {
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
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof AbstractPanel)) return false;
    AbstractPanel that = (AbstractPanel) o;
    return id == that.id &&
            Objects.equals(nextPanels, that.nextPanels) &&
            Objects.equals(Players, that.Players) &&
            Objects.equals(left, that.left) &&
            Objects.equals(right, that.right) &&
            Objects.equals(up, that.up) &&
            Objects.equals(down, that.down);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }

  /**
   * AUMENTA the player's star count by the D6 roll multiplied by the maximum between the player's
   * norma level and three.
   */
  public abstract void activateBy(final @NotNull Player player);



  public List<Player> getPlayers() {
    return Players;
  }



  public IPanel getLeft() {
    return left;
  }
  public IPanel getRight() {
    return right;
  }
  public IPanel getUp() {
    return up;
  }
  public IPanel getDown() {
    return down;
  }


  public void setLeft(IPanel left) {
    this.left = left;
  }

  public void setRight(IPanel right) {
    this.right = right;
  }

  public void setUp(IPanel up) {
    this.up = up;
  }

  public void setDown(IPanel down) {
    this.down = down;
  }
}


