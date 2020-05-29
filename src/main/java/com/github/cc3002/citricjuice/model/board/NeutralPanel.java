package com.github.cc3002.citricjuice.model.board;

import com.github.cc3002.citricjuice.model.Player;
import org.jetbrains.annotations.NotNull;

/**
Subclass of Abstract Panel
 Nothing happens in here
 */
public class NeutralPanel extends AbstractPanel {
  /**
   * Creates a new panel.
   *
   * @param a
   * @param b the coordinates of the panel.
   */
  public NeutralPanel(int a, int b) {
    super(a, b);
  }

  @Override
  public void activateBy(@NotNull Player player) {

  }
}
